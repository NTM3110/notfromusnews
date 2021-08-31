package com.notfromus.models;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;



public interface CrawlModel {


    //Article getDirect_url() is one of many inputs of this crawl
    String crawl(String url);


    //
    List<ArticleModel> Crawl(String instruction) throws IOException, ParseException;



    List<ArticleModel> getHTMLs();

}
