package com.zhuoxin.www.sqlitedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by lixiang on 2016/11/7.
 */

public class MyAdapter extends BaseAdapter {
    private List<User> users;
    private LayoutInflater inflater;
    private Context context;

    public MyAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TempHolder tempHolder;
        if(convertView==null){
            tempHolder = new TempHolder();
            convertView = inflater.inflate(R.layout.item,null);
            tempHolder.tv_id = (TextView) convertView.findViewById(R.id.item_id);
            tempHolder.tv_name = (TextView) convertView.findViewById(R.id.item_name);
            tempHolder.tv_age = (TextView) convertView.findViewById(R.id.item_age);
            convertView.setTag(tempHolder);
        }else {
            tempHolder = (TempHolder) convertView.getTag();
        }
        User user = users.get(position);
        tempHolder.tv_id.setText(user.getId()+"");
        tempHolder.tv_name.setText(user.getName());
        tempHolder.tv_age.setText(user.getAge()+"");
        return convertView;
    }

    private class TempHolder{
        private TextView tv_id,tv_name,tv_age;
    }

    public void noti(){
        notifyDataSetChanged();
    }


}
