package com.notfromus.models;


import javafx.scene.image.Image;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ArticleModelManager implements ArticleModel {



    private String title;



    private String direct_url;



    private String img_src;


    private Image img;


    private String time;

    private long t;

    public ArticleModelManager(){

    }


    public ArticleModelManager(String title, String direct_url) {
        this.title = title;
        this.direct_url = direct_url;
    }

    public Image getImg() {
        return img;
    }

    public String getImg_src() {
        return img_src;
    }


    public String getTime() {
        return time;
    }


    public long getT() {
        return t;
    }


    public void setT(long t) {
        this.t = t;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public void setImg_src(String img_src) throws IOException {
        this.img_src = img_src;

        this.img = new Image(String.valueOf(new URL(getImg_src())),0,0,true,true);
        //System.out.println(img.getUrl());
    }



    public String getTitle() {
        return title;
    }



    public String getDirect_url() {
        return direct_url;
    }





}
