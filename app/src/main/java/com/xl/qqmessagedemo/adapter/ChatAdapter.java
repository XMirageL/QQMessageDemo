package com.xl.qqmessagedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xl.qqmessagedemo.R;
import com.xl.qqmessagedemo.po.User;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {

    //定义两个类别标志
    private static final int TYPE_OWN = 0;
    private static final int TYPE_FRIEND = 1;
    private Context mContext;
    private ArrayList<User> mData = null;


    public ChatAdapter(Context mContext,ArrayList<User> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //多布局的核心，通过这个判断类别
    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).getType() == TYPE_OWN) {
            return TYPE_OWN;
        } else if (mData.get(position).getType() == TYPE_FRIEND) {
            return TYPE_FRIEND;
        } else {
            return super.getItemViewType(position);
        }
    }

    //类别数目
    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        int type = getItemViewType(position);

        ViewHolder holder = null;
        if(view == null){
            holder = new ViewHolder();
            switch (type){
                case TYPE_OWN:
                    view = LayoutInflater.from(mContext).inflate(R.layout.chat_list_item_right, parent, false);
                    holder.img_head = (ImageView) view.findViewById(R.id.img_head_right);
                    holder.tv_userPpeak = (TextView) view.findViewById(R.id.tv_message_right);
                    view.setTag(R.id.Tag_OWN,holder);
                    break;
                case TYPE_FRIEND:
                    view = LayoutInflater.from(mContext).inflate(R.layout.chat_list_item_left, parent, false);
                    holder.img_head = (ImageView) view.findViewById(R.id.img_head_left);
                    holder.tv_userPpeak = (TextView) view.findViewById(R.id.tv_message_left);
                    view.setTag(R.id.Tag_FRIEND,holder);
                    break;
            }
        }else{
            switch (type){
                case TYPE_OWN:
                    holder = (ViewHolder) view.getTag(R.id.Tag_OWN);
                    break;
                case TYPE_FRIEND:
                    holder = (ViewHolder) view.getTag(R.id.Tag_FRIEND);
                    break;
            }
        }
        holder.img_head.setImageResource(mData.get(position).getImgId());
        holder.tv_userPpeak.setText(mData.get(position).getUserNSpeak());
        return view;
    }


    static class ViewHolder{
        ImageView img_head;
        TextView tv_userPpeak;
    }
}
