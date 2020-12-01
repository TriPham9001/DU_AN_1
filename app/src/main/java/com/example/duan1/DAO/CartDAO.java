package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.SQLite;
import com.example.duan1.Model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private SQLiteDatabase DB;

    public CartDAO(Context context) {
        SQLite sqLite = new SQLite(context);
        DB = sqLite.getReadableDatabase();
        DB = sqLite.getWritableDatabase();
    }

    public long Insert(Cart item) {
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("images", item.getImages());
        values.put("price", item.getPrice());
        return DB.insert("Cart", null, values);
    }

    public List<Cart> get(String sql, String... selectionArgs) {
        List<Cart> list = new ArrayList<Cart>();
        Cursor c = DB.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Cart item = new Cart();
            item.setName(c.getString(c.getColumnIndex("name")));
            item.setImages(c.getString(c.getColumnIndex("images")));
            item.setPrice(c.getDouble(c.getColumnIndex("price")));
            list.add(item);
        }
        return list;
    }

    public List<Cart> getAll() {
        String sqlGetAll = "SELECT * FROM Cart";
        return get(sqlGetAll);
    }

    public int totalPrice() {
        int total = 0;
        Cursor cursor = DB.rawQuery("SELECT SUM(price) FROM Cart", null);
        if (cursor.moveToFirst()) {
            do {
                total = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return total;
    }
}
