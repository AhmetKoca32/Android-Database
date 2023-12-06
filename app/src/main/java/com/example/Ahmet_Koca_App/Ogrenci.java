package com.example.Ahmet_Koca_App;

public class Ogrenci
{


    public Ogrenci(String id, String num, String ad) {
        this.id = id;
        this.num = num;
        this.ad = ad;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    private String id;
    private String num;
    private String ad;


}
