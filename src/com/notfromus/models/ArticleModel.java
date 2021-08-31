package com.notfromus.models;


import javafx.scene.image.Image;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ArticleModel {


    Image getImg();


    String getImg_src();


    String getTime();

    long getT();

    void setT(long t);

    void setTime(String time);

    void setImg_src(String img_src) throws IOException;


    String getTitle();


    String getDirect_url();


}
