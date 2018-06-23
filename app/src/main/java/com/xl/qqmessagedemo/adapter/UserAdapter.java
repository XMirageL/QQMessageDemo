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

import java.util.LinkedList;

public class UserAdapter extends BaseAdapter{
    private LinkedList<User> data;
    private Context context;

    public UserAdapter(LinkedList<User> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
            holder = new ViewHolder();
            holder.img_head = (ImageView) view.findViewById(R.id.img_head);
            holder.tv_userName = (TextView) view.findViewById(R.id.tv_userName);
            holder.tv_userPpeak = (TextView) view.findViewById(R.id.tv_userPpeak);
            holder.tv_speakTime = (TextView) view.findViewById(R.id.tv_speakTime);
            view.setTag(holder);   //将Holder存储到convertView中
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.img_head.setImageResource(data.get(i).getImgId());
        holder.tv_userName.setText(data.get(i).getUserName());
        holder.tv_userPpeak.setText(data.get(i).getUserNSpeak());
        holder.tv_speakTime.setText(data.get(i).getSpeakTime());
        return view;
    }
    static class ViewHolder{
        ImageView img_head;
        TextView tv_userName;
        TextView tv_userPpeak;
        TextView tv_speakTime;
    }
}
