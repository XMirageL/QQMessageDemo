package com.xl.qqmessagedemo;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xl.qqmessagedemo.adapter.ChatAdapter;
import com.xl.qqmessagedemo.po.User;

import java.util.ArrayList;
import java.util.Random;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_chat;
    private TextView tv_chat_name;
    private Button btn_send;
    private ImageView img_chat_back;
    private User userFriend;
    private ArrayList<User> mData;
    private ListView lv_messages;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        userFriend = (User) getIntent().getSerializableExtra("user");
        initViews();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_chat_back:
                finish();
                break;
            case R.id.btn_send:
                sendMessage();
                break;
            default:
                break;
        }
    }

    private void sendMessage() {
        String messageText = et_chat.getText().toString();
        et_chat.setText("");
        User user = new User(messageText, R.mipmap.top_head, 0);
        Random random  = new Random();
        String messageStr = "";
        for (int i = 0; i < messageText.length(); i++) {
            messageStr += (char) (122-random.nextInt(26));
        }
        final User user1 = new User("回复:" + messageText + "\n_____________________\n"+messageStr, userFriend.getImgId(), 1);
        mData.add(user);
        chatAdapter.notifyDataSetChanged();
        lv_messages.smoothScrollToPosition(mData.size() - 1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mData.add(user1);
                chatAdapter.notifyDataSetChanged();
                lv_messages.smoothScrollToPosition(mData.size() - 1);
            }
        }, 2000);
    }

    private void initViews() {
        mData = new ArrayList<>();
        lv_messages = findViewById(R.id.lv_messages);
        chatAdapter = new ChatAdapter(ChatActivity.this, mData);
        lv_messages.setAdapter(chatAdapter);

        tv_chat_name = findViewById(R.id.tv_chat_name);
        tv_chat_name.setText(userFriend.getUserName());

        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        img_chat_back = findViewById(R.id.img_chat_back);
        img_chat_back.setOnClickListener(this);


        et_chat = findViewById(R.id.et_chat);
        et_chat.addTextChangedListener(new TextWatcher() {
            //只要编辑框内容有变化就会调用该方法，s为编辑框变化后的内容
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = charSequence.toString();
                if (str.length() == 0) {
                    if (btn_send.isEnabled()) {
                        btn_send.setEnabled(false);
                    }
                } else {
                    if (!btn_send.isEnabled()) {
                        btn_send.setEnabled(true);
                    }
                }
            }

            //编辑框内容变化之前会调用该方法，s为编辑框内容变化之前的内容
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }


            //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}
