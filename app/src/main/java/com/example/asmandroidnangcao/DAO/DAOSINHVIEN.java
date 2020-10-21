package com.example.asmandroidnangcao.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmandroidnangcao.DpHeper.SQLite;
import com.example.asmandroidnangcao.Model.SinhVien;


import java.util.ArrayList;

public class DAOSINHVIEN {
    SQLite khoahoc;
    public SQLiteDatabase db;
    public DAOSINHVIEN(Context context){
        khoahoc= new SQLite(context);
    }
    public ArrayList<SinhVien> readAll(){
        ArrayList<SinhVien> datasinhvien = new ArrayList<>();
        SQLiteDatabase db = khoahoc.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * From SINHVIEN ",null);
        cs.moveToFirst();
        while ((!cs.isAfterLast())){
            String id =cs.getString(0);
            String masv =cs.getString(1);
            String tensv =cs.getString(2);
            String nganh =cs.getString(3);
            String idkh =cs.getString(4);
            String image = cs.getString(5);
            datasinhvien.add(new SinhVien(id,masv,tensv,nganh,idkh,image));
            cs.moveToNext();

        }cs.close();
        return datasinhvien;
    }

    public boolean insert(SinhVien sinhVien){
        SQLiteDatabase db = khoahoc.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MASV",sinhVien.getMasv());
        contentValues.put("TENSV",sinhVien.getTensv());
        contentValues.put("NGANH",sinhVien.getNganh());
        contentValues.put("IDKH",sinhVien.getIdkh());
        contentValues.put("IMAGE",sinhVien.get_image());
        long insertkh = db.insert("SINHVIEN",null,contentValues);
        return insertkh>0;
    }
    public boolean update(String id,String masv,String tensv ,String nganh,String idkh, String imgae){
        db= khoahoc.getReadableDatabase();
        ContentValues values =  new ContentValues();
        values.put("MASV",masv);
        values.put("TENSV",tensv);
        values.put("NGANH",nganh);
        values.put("IDKH",idkh);
        values.put("IMAGE",imgae);
        int updatekh = db.update("SINHVIEN",values,"ID=?",new String[]{id});
        return updatekh>0;
    }
    public boolean delete(String id){
        db= khoahoc.getReadableDatabase();
        int xoaloai = db.delete("SINHVIEN","ID=?",new String[]{id});
        return xoaloai>0;
    }
}
