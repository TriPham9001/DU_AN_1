package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1.Database.SQLite;
import com.example.duan1.Model.Favorite;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDAO {
    private SQLiteDatabase DbFavorite;

    public FavoriteDAO(Context context) {
        SQLite sqLite = new SQLite(context);
        DbFavorite = sqLite.getReadableDatabase();
        DbFavorite = sqLite.getWritableDatabase();
    }

    public long Insert(Favorite item) {
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("images", item.getImages());
        values.put("price", item.getPrice());
        values.put("describe", item.getDescribe());

        return DbFavorite.insert("Cart", null, values);
    }

    public List<Favorite> get(String sql, String... selectionArgs) {
        List<Favorite> list = new ArrayList<Favorite>();
        Cursor c = DbFavorite.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Favorite item = new Favorite();
            item.setName(c.getString(c.getColumnIndex("name")));
            item.setImages(c.getString(c.getColumnIndex("images")));
            item.setPrice(c.getDouble(c.getColumnIndex("price")));
            item.setDescribe(c.getString(c.getColumnIndex("describe")));

            list.add(item);
        }
        return list;
    }

    public List<Favorite> getAll() {
        String sqlGetAll = "SELECT * FROM Favorite";
        return get(sqlGetAll);
    }


}
