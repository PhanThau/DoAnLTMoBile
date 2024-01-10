package com.example.appdoctintuc;

public class DanhMuc {
    private String tenDanhMuc;
    private int imgDanhMuc;
    private String url;

    public DanhMuc(String tenDanhMuc, int imgDanhMuc, String url) {
        this.tenDanhMuc = tenDanhMuc;
        this.imgDanhMuc = imgDanhMuc;
        this.url = url;
    }

    public DanhMuc(String tenDanhMuc, String url) {
        this.tenDanhMuc = tenDanhMuc;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public int getImgDanhMuc() {
        return imgDanhMuc;
    }

    public void setImgDanhMuc(int imgDanhMuc) {
        this.imgDanhMuc = imgDanhMuc;
    }

}
