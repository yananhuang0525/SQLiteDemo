package com.panku.sqlitedemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Date：2017/6/13
 * Time: 12:00
 * author: huangyanan
 */

public class DBManager {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    /**
     * 添加一组数据
     *
     * @param orders
     */
    public void add(List<OrderInfo> orders) {
        for (OrderInfo order : orders) {
            ContentValues values = new ContentValues();
            values.put("code", order.getCode());
            values.put("username", order.getUsername());
            values.put("state", order.getState());
            database.insert("pay_order", null, values);
        }
    }

    /**
     * 添加一条数据
     *
     * @param order
     */
    public void add(OrderInfo order) {
        if (query(order.getCode()) == null) {
            ContentValues values = new ContentValues();
            values.put("code", order.getCode());
            values.put("username", order.getUsername());
            values.put("state", order.getState());
            database.insert("pay_order", null, values);
        } else {
            Log.e("HYN", "订单已存在，已更新");
            update(order);
        }
    }

    /**
     * 删除数据
     *
     * @param code
     */
    public void delete(String code) {
        int result = -1;//0失败1成功
        result = database.delete("pay_order", "code=?", new String[]{code});
        Log.e("HYN", "删除结果" + result);
    }

    /**
     * 更新数据
     *
     * @param order
     */
    public void update(OrderInfo order) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("code", order.getCode());
        contentValues.put("username", order.getUsername());
        contentValues.put("state", order.getState());
        database.update("pay_order", contentValues, "code=?", new String[]{order.getCode()});
    }

    /**
     * 更新某一个数据根据更新条件（更新一条订单的状态根据订单号）
     *
     * @param state
     * @param code
     */
    public void update(String state, String code) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", state);
        database.update("pay_order", contentValues, "code=?", new String[]{code});
    }

    /**
     * 查询一条数据根据某一个查询条件
     *
     * @param code
     * @return
     */
    public OrderInfo query(String code) {
        Cursor cursor = database.query("pay_order", new String[]{"code", "username", "state"}, "code=?", new String[]{code}, null, null, null);
        while (cursor.moveToNext()) {
            OrderInfo order = new OrderInfo();
            order.setCode(cursor.getString(cursor.getColumnIndex("code")));
            order.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            order.setState(cursor.getString(cursor.getColumnIndex("state")));
            return order;
        }
        return null;
    }

    /**
     * 查询表中所有数据
     *
     * @return
     */
    public List<OrderInfo> query() {
        ArrayList<OrderInfo> list = new ArrayList<>();
        Cursor cursor = database.query("pay_order", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            OrderInfo order = new OrderInfo();
            order.setCode(cursor.getString(cursor.getColumnIndex("code")));
            order.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            order.setState(cursor.getString(cursor.getColumnIndex("state")));
            list.add(order);
        }
        cursor.close();
        return list;
    }

    /**
     * 删除表
     *
     * @param table_name
     */
    public void delect_table(String table_name) {
        //删除表的SQL语句
        String sql = "DROP TABLE IF EXISTS" + table_name;
        database.execSQL(sql);
    }

    /**
     * 关闭数据库
     */
    public void colse() {
        database.close();
    }
}
