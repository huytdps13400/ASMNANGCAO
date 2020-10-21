package com.example.asmandroidnangcao.DpHeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {
    public  SQLite(Context context){
        super(context,"Qlkhoahoc.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ;

            sql = "CREATE TABLE KHOAHOC(IDKH integer primary key autoincrement ,"+"MAKHOAHOC text,"+" TENKHOAHOC text,"+"TIEN double," +
                    "NGAY TEXT,"+"IMAGE BLOB)";
        db.execSQL(sql);

        sql = "CREATE TABLE SINHVIEN(ID integer primary key autoincrement,"+"MASV text," +
                "TENSV TEXT,"+ "NGANH TEXT,"+"IDKH integer  references  KHOAHOC(IDKH),"+"IMAGE TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists KHOAHOC");
        db.execSQL("Drop table if exists SINHVIEN");

        onCreate(db);
    }
}
