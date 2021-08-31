package com.notfromus.models;



import java.util.ArrayList;
import java.util.List;




public class ModelFactory {



    private CrawlModel crawlModel;



    private ArticleModel articleModel;



    private List<ArticleModel> articlesModel;



    public CrawlModel getCrawlModel(){
        if(crawlModel == null) crawlModel = new CrawlModelManager();
        return crawlModel;
    }

    public ArticleModel getArticleModel() {
        if(articleModel == null) articleModel = new ArticleModelManager();
        return articleModel;
    }

    public List<ArticleModel> getArticlesModel() {
        if(articlesModel == null) articlesModel = new ArrayList<>();
        return articlesModel;
    }

}
