package com.xl.qqmessagedemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xl.qqmessagedemo.adapter.UserAdapter;
import com.xl.qqmessagedemo.po.User;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView lv_1;
    private Context mContext;
    private LinkedList<User> mData;
    private UserAdapter mAdapter;
    private ImageView img_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置窗体为没有标题的模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initViews();
    }

    private void initViews() {
        mContext = MainActivity.this;
        lv_1 = findViewById(R.id.lv_1);
        initListViewData();
        lv_1.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                initPopWindow(view,i);
                return true;
            }
        });
        lv_1.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,ChatActivity.class);
                intent.putExtra("user",mData.get(i-1));
                startActivity(intent);
            }
        });

        img_add = findViewById(R.id.img_add);
        img_add.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.img_add:
                initPopMenu(view);
                break;
            default:break;
        }
    }

    private void initPopMenu(View v){
        View view = LayoutInflater.from(mContext).inflate(R.layout.add_pop, null, false);
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setAnimationStyle(R.anim.anim_pop);
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 0, 0);

    }

    private void initPopWindow(View v, final int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pop, null, false);
        TextView tv_zd = view.findViewById(R.id.tv_zd);
        TextView tv_del = view.findViewById(R.id.tv_del);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setAnimationStyle(R.anim.anim_pop);
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popWindow.showAtLocation(v,Gravity.NO_GRAVITY
                ,location[0]+v.getMeasuredWidth()/2-v.getMeasuredHeight()/2
                ,location[1]-v.getMeasuredHeight()/2);

        tv_zd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = mData.get(i-1);
                mData.remove(i-1);
                mData.add(0,user);
                mAdapter.notifyDataSetChanged();
                popWindow.dismiss();
            }
        });
        tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.remove(i-1);
                mAdapter.notifyDataSetChanged();
                popWindow.dismiss();
            }
        });
    }


    private void initListViewData() {
        //先运行一遍后再将这里的代码去掉注释否则listView item不能点
        //定义listVIew的表头
        View headView = LayoutInflater.from(this).inflate(R.layout.search_bar, null, false);
        //设置表头
        lv_1.addHeaderView(headView);

        //定义数据源
        mData = new LinkedList<>();
        //添加数据
        mData.add(new User("群助手"
                , "[1个群有新消息]"
                , "下午2:48", R.mipmap.h4));
        mData.add(new User("小米8开发版内测群"
                , "xx-222165: [图片]"
                , "下午2:48", R.mipmap.h3));
        mData.add(new User("蓝天准系统黑苹果研究"
                , "七号: 所以9750除非 牙膏瞬间..."
                , "下午2:44", R.mipmap.h2));
        mData.add(new User("FW俱乐部"
                , "4FW-Guer: [图片]"
                , "下午2:39", R.mipmap.h7));
        mData.add(new User("李泽林"
                , " ApplicationContext act   =new..."
                , "下午2:37", R.mipmap.h5));
        mData.add(new User("王鑫"
                , "[图片]"
                , "上午11:53", R.mipmap.h6));
        mData.add(new User("大吉大利(4)"
                , "[图片]"
                , "上午11:48", R.mipmap.h8));
        mData.add(new User("我的电脑"
                , "你已在电脑登录，可传文件到电脑"
                , "上午10:37", R.mipmap.h1));
        mData.add(new User("QQ手游"
                , "[图片]"
                , "上午10:03", R.mipmap.h10));
        mData.add(new User("QQ邮箱提醒"
                , "Rockstar Propaganda: NewTransfo..."
                , "上午9:45", R.mipmap.h11));
        mData.add(new User("QQ会员"
                , "限时！游骑兵礼包 再抢永久湮灭"
                , "上午9:38", R.mipmap.h9));
        mData.add(new User("软协2017新手村"
                , "村口卖金鳞甲的大哥: [图片]"
                , "上午9:16", R.mipmap.h12));
        mAdapter = new UserAdapter(mData, mContext);

        lv_1.setAdapter(mAdapter);
    }


}
