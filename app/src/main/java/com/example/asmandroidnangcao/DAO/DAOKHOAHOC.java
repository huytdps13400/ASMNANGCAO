package com.example.asmandroidnangcao.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmandroidnangcao.DpHeper.SQLite;
import com.example.asmandroidnangcao.Model.KhoaHoc;

import java.util.ArrayList;
public class DAOKHOAHOC {
    SQLite khoahoc;
    public SQLiteDatabase db;
    public DAOKHOAHOC(Context context){
        khoahoc= new SQLite(context);
    }
    public ArrayList<KhoaHoc> readAll(){
        ArrayList<KhoaHoc> datakhoahoc = new ArrayList<>();
        SQLiteDatabase db = khoahoc.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * From KHOAHOC ",null);
        cs.moveToFirst();
        while ((!cs.isAfterLast())){
            KhoaHoc contact = new KhoaHoc();
                contact.setIdkh(cs.getString(0));
                contact.setMakhoahoc(cs.getString(1));
                contact.setTenkhoahoc(cs.getString(2));
                contact.setTien(cs.getDouble(3));
                contact.setNgay(cs.getString(4));
                contact.setImage(cs.getBlob(5));
                // Adding contact to list
                datakhoahoc.add(contact);
            cs.moveToNext();

        }cs.close();
        return datakhoahoc;
    }

    public boolean insert(KhoaHoc khoaHoc){
        SQLiteDatabase db = khoahoc.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MAKHOAHOC",khoaHoc.getMakhoahoc());
        contentValues.put("TENKHOAHOC",khoaHoc.getTenkhoahoc());
        contentValues.put("TIEN",khoaHoc.getTien());
        contentValues.put("NGAY",khoaHoc.getNgay());
        contentValues.put("IMAGE",khoaHoc.getImage());
        long insertkh = db.insert("KHOAHOC",null,contentValues);
        return insertkh>0;
    }
    public boolean update(String id,String makh,String tenkh ,double tien,String ngay,byte[] image){
        db= khoahoc.getReadableDatabase();
        ContentValues values =  new ContentValues();
        values.put("MAKHOAHOC",makh);
        values.put("TENKHOAHOC",tenkh);
        values.put("TIEN",tien);
        values.put("NGAY",ngay);
        values.put("IMAGE",image);
        int updatekh = db.update("KHOAHOC",values,"IDKH=?",new String[]{id});
        return updatekh>0;
    }
    public boolean delete(String id){
        db= khoahoc.getReadableDatabase();
        int xoaloai = db.delete("KHOAHOC","IDKH=?",new String[]{id});
        return xoaloai>0;
    }
}
