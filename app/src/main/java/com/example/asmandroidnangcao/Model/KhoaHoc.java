package com.example.asmandroidnangcao.Model;

import androidx.annotation.NonNull;

public class KhoaHoc {
    private String idkh;
    private String makhoahoc;
    private String tenkhoahoc;
    private double tien;
    private String ngay;
    byte[] _image;
    public KhoaHoc() {
    }

    public KhoaHoc(String idkh, String makhoahoc, String tenkhoahoc, double tien, String ngay, byte[] image) {
        this.idkh = idkh;
        this.makhoahoc = makhoahoc;
        this.tenkhoahoc = tenkhoahoc;
        this.tien = tien;
        this.ngay = ngay;
        this._image = image;
    }

    public String getIdkh() {
        return idkh;
    }

    public void setIdkh(String idkh) {
        this.idkh = idkh;
    }

    public String getMakhoahoc() {
        return makhoahoc;
    }

    public void setMakhoahoc(String makhoahoc) {
        this.makhoahoc = makhoahoc;
    }

    public String getTenkhoahoc() {
        return tenkhoahoc;
    }

    public void setTenkhoahoc(String tenkhoahoc) {
        this.tenkhoahoc = tenkhoahoc;
    }

    public double getTien() {
        return tien;
    }

    public void setTien(double tien) {
        this.tien = tien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public byte[] getImage() {
        return _image;
    }

    public void setImage(byte[] image) {
        this._image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return getTenkhoahoc();
    }
}

