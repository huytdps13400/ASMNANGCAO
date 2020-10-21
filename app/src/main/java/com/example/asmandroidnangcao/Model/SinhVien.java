package com.example.asmandroidnangcao.Model;

public class SinhVien {
    private String id;
    private String masv;
    private String tensv;
    private String nganh;
    private String idkh;
    private String _image;

    public SinhVien() {
    }

    public SinhVien(String id, String masv, String tensv, String nganh, String idkh, String _image) {
        this.id = id;
        this.masv = masv;
        this.tensv = tensv;
        this.nganh = nganh;
        this.idkh = idkh;
        this._image = _image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getIdkh() {
        return idkh;
    }

    public void setIdkh(String idkh) {
        this.idkh = idkh;
    }

    public String get_image() {
        return _image;
    }

    public void set_image(String _image) {
        this._image = _image;
    }
}
