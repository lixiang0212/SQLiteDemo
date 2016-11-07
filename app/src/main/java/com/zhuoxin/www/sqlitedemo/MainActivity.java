package com.zhuoxin.www.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView listView;
    private Button bt_add,bt_delete,bt_update,bt_find,bt_query;
    private SqlUtils utils;
    private EditText et_name,et_age;
    private MyAdapter adapter;
    private List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils = new SqlUtils(this);
        users = new ArrayList<>();
        users = utils.query();
        adapter = new MyAdapter(users,this);
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        bt_add = (Button) findViewById(R.id.add);
        bt_delete = (Button) findViewById(R.id.delete);
        bt_update = (Button) findViewById(R.id.update);
        bt_query = (Button) findViewById(R.id.query);
        bt_find = (Button) findViewById(R.id.find);
        bt_add.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_update.setOnClickListener(this);
        bt_find.setOnClickListener(this);
        bt_query.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_age);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                String name = et_name.getText().toString();
                int age = Integer.parseInt(et_age.getText().toString());
                if(name!=null) {
                    utils.add(new User(name, age));
                    et_name.setText("");
                    et_age.setText("");
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                    users = utils.query();
                    adapter = new MyAdapter(users,this);
                    listView.setAdapter(adapter);
                }
                break;
            case R.id.delete:
                int id= Integer.parseInt(et_age.getText().toString());
                utils.delete(id);
                et_name.setText("");
                et_age.setText("");
                Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
                users = utils.query();
                adapter = new MyAdapter(users,this);
                listView.setAdapter(adapter);
                break;
            case R.id.query:
                users = utils.query();
                adapter = new MyAdapter(users,this);
                listView.setAdapter(adapter);
                break;
            case R.id.find:
                int ids= Integer.parseInt(et_age.getText().toString());
                users=utils.find(ids);
                adapter = new MyAdapter(users,this);
                listView.setAdapter(adapter);
                break;
        }
    }
}
