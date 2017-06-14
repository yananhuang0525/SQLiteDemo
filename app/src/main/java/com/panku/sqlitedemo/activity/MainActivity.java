package com.panku.sqlitedemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.panku.sqlitedemo.R;
import com.panku.sqlitedemo.db.DBManager;
import com.panku.sqlitedemo.db.OrderInfo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_code, et_username, et_state;
    private Button btn_add, btn_delete, btn_update, btn_query, btn_one;
    private TextView tv_result;
    private DBManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        manager = new DBManager(this);
    }

    private void initView() {
        et_code = (EditText) findViewById(R.id.et_code);
        et_username = (EditText) findViewById(R.id.et_username);
        et_state = (EditText) findViewById(R.id.et_state);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_one = (Button) findViewById(R.id.btn_one);

        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_add.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_one.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        OrderInfo order = new OrderInfo();
        order.setCode(et_code.getText().toString());
        order.setUsername(et_username.getText().toString());
        order.setState(et_state.getText().toString());
        switch (v.getId()) {
            case R.id.btn_add:
                manager.add(order);
                break;
            case R.id.btn_update:
                manager.update(order);
                break;
            case R.id.btn_delete:
                manager.delete(order.getCode());
                break;
            case R.id.btn_query:
                tv_result.setText(JSON.toJSONString(manager.query()));
                break;
            case R.id.btn_one:
                tv_result.setText(JSON.toJSONString(manager.query(order.getCode())));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.colse();
    }
}
