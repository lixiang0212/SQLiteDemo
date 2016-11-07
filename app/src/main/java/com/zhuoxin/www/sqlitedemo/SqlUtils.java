package com.zhuoxin.www.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixiang on 2016/11/7.
 */

public class SqlUtils {
    private MyOpenHelper openHelper;

    public SqlUtils(Context context) {
        openHelper = new MyOpenHelper(context);
    }

    public void add(User user){
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",user.getName());
        values.put("age",user.getAge());
        db.insert("user",null,values);
        db.close();
    }

    public void delete(int id){
        SQLiteDatabase db = openHelper.getWritableDatabase();
        db.delete("user","id=?",new String[]{id+""});
        db.close();
    }

    public void update(User user,int id){
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",user.getName());
        values.put("age",user.getAge());
        db.update("user",values,"id=?",new String[]{id+""});
        db.close();
    }

    public List<User> find(int id){
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where id=?",new String[]{id+""});
        List<User> user = new ArrayList<>();
        if(cursor!=null) {
            while (cursor.moveToNext()) {
                int userId = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                user.add(new User(userId, name, age));
            }
        }else {
            Log.i("AAA","corsor=null");
        }
            cursor.close();
            db.close();

        return user;
    }
    public List<User> query(){
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where ?",new String[]{"1"});
        List<User> users = new ArrayList<>();
        while (cursor.moveToNext()){
            int userId = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            User user = new User(userId,name,age);
            users.add(user);
        }
        return users;
    }
}
