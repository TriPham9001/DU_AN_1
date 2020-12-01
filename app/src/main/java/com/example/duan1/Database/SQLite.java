package com.example.duan1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {

    static final String DBName = "ASMMM";
    static final int Version = 1;

    public SQLite(Context context) {
        super(context, DBName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String sqlCart = "CREATE TABLE Cart(" +
                "name TEXT NOT NULL," +
                "images TEXT NOT NULL," +
                "price DOUBLE NOT NULL);";
        DB.execSQL(sqlCart);

        String sqlFavorite = "CREATE TABLE Favorite(" +
                "name TEXT NOT NULL," +
                "images TEXT NOT NULL," +
                "describe TEXT NOT NULL," +
                "price DOUBLE NOT NULL);";
        DB.execSQL(sqlFavorite);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        String sqlDropCart = "DROP TABLE Cart ;";
        DB.execSQL(sqlDropCart);
        onCreate(DB);
    }
}
