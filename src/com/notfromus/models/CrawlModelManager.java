package com.notfromus.models;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;

public class CrawlModelManager implements CrawlModel {
    /**
     * Craw Model Manager manages functions for interface Crawl - Model
     * logs are usually write to console at runtime, look for "@CrawlModelManager/"
     *
     * @param HTML is a string
     * <p>
     * <p>
     * Written by Cao Minh - s3818272 14/8/21
     */

    List<ArticleModel> articles = new ArrayList<>();
    private String HTML;
    private int bound = 50;

    @Override
    public String crawl(String url) {
        Document doc;
        String dir = " @CrawlModelManager/crawl";
        try {
            doc = Jsoup.connect(url).get();
            System.out.println(url + "connection looks find " + dir);
        } catch (IOException e) {
            System.out.println(" URL not found" + dir);
            throw new RuntimeException(e);
        }
        //System.out.println(doc.html());
        for (Element element : doc.select("div.form-comment")) {
            element.remove();
        }
        for (Element element : doc.select("script")) {
            element.remove();
        }
        for (Element element : doc.select("footer")) {
            element.remove();
        }
        for (Element element : doc.select("noscript")) {
            element.remove();
        }

        for (Element element : doc.select("div.details__morenews")) {
            element.remove();
        }

        for (Element element : doc.select("div.col_right_news")) {
            element.remove();
        }
        for (Element element : doc.select("div.bread-crumbs")) {
            element.remove();
        }
        for (Element element : doc.select("div.content-left")) {
            element.remove();
        }
        for (Element element : doc.select("p.title-time-home")) {
            element.remove();
        }
        for (Element element : doc.select("div.menu-box")) {
            element.remove();
        }
        for (Element element : doc.select("div.simplePopup")) {
            element.remove();
        }
        for (Element element : doc.select("div.site-header__grid")) {
            element.remove();
        }
        for (Element element : doc.select("div.wrapPopup")) {
            element.remove();
        }

        for (Element element : doc.select("div.details__author__meta")) {
            element.remove();
        }

        for (Element element : doc.select("div.breadcrumbs")) {
            element.remove();
        }
        for (Element element : doc.select("div.modal")) {
            element.remove();
        }
        for (Element element : doc.select("div.social")) {
            element.remove();
        }
        for (Element element : doc.select("ul.social")) {
            element.remove();
        }
        for (Element element : doc.select("section.zone")) {
            element.remove();
        }
        for (Element element : doc.select("textarea")) {
            element.remove();
        }
        for (Element element : doc.select("div.float-ads floatbot")) {
            element.remove();
        }
        for (Element element : doc.select("div.social-signin")) {
            element.remove();
        }
        for (Element element : doc.select("div.banner")) {
            element.remove();
        }
        for (Element element : doc.select("input")) {
            element.remove();
        }
        for (Element element : doc.select("div.as-content")) {
            element.remove();
        }

        for (Element element : doc.select("table.video")) {
            element.remove();
        }
         for (Element element : doc.select("div.box_embed_video_parent")) {
            element.remove();
        }
        for (Element element : doc.select("videolist")) {
            element.remove();
        }
        for (Element element : doc.select("videoitem")) {
            element.remove();
        }
        for (Element element : doc.select("[type*=RelatedOneNews]")) {
            element.remove();
        }
        for (Element element : doc.select("div.network")) {
            element.remove();
        }

        for (Element element : doc.select("div.content-bottom-detail")) {
            element.remove();
        }
        for (Element element : doc.select("div.tab-moreview")) {
            element.remove();
        }
        for (Element element : doc.select("picture")) {
            element.parent().removeAttr("fig-picture").removeAttr("style");
            String img = element.select("[data-srcset]").attr("data-srcset").split(" 1x")[0];
            Element in = new Element("div").attr("class", "fig-picture");
            in.appendElement("img").attr("src", img).attr("style", "width:60%;height:60%");
            //System.out.println(img);
            element.replaceWith(in);
        }

        for (Element element : doc.select("img")) {
            String img = element.select("[data-src]").attr("data-src").isEmpty() ? element.select("[src]").attr("src") : element.select("[data-src]").attr("data-src");
            Element in = new Element("div").attr("class", "fig-picture");
            in.appendElement("img").attr("src", img).attr("style", "width:60%;height:60%");
            //System.out.println(img);
            element.replaceWith(in);
        }
        for (Element element : doc.select("style")) {
            element.remove();
        }
        for (Element element : doc.select("div.title-content clearfix")) {
            element.remove();
        }
        for (Element element : doc.select("meta")) {
            element.remove();
        }
        for (Element element : doc.select("a")) {
            element.removeAttr("href");
        }
        for (Element element : doc.select("link")) {
            element.remove();
        }
        for (Element element : doc.select("ul.parent")) {
            element.remove();
        }
        for (Element element : doc.select("span")) {
            element.remove();
        }

        for (Element element : doc.select("button")) {
            element.remove();
        }

        for (Element element : doc.select("div[id=stickyrm1bot]")) {
            element.remove();
        }
        for (Element element : doc.select("div[id=myvne_taskbar_tmp]")) {
            element.remove();
        }
        for (Element element : doc.select("div[id=ctl00_main_divSidebar]")) {
            element.remove();
        }
        for (Element element : doc.select("div[id=abde]")) {
            element.remove();
        }
        for (Element element : doc.select("div[type='RelatedNews']")) {
            element.remove();
        }
        for (Element element : doc.select("div.tagandnetwork")) {
            element.remove();
        }
        for (Element element : doc.select("div.nd_header")) {
            element.remove();
        }
        for (Element element : doc.select("div.header_link")) {
            element.remove();
        }
        for (Element element : doc.select("div.nd_header_menu")) {
            element.remove();
        }
        for (Element element : doc.select("div.dropdown-menu")) {
            element.remove();
        }
        for (Element element : doc.select("ul.pager")) {
            element.remove();
        }
        for (Element element : doc.select("div.panel-default")) {
            element.remove();
        }
        for (Element element : doc.select("div.footer_vne")) {
            element.remove();
        }
        for (Element element : doc.select("div.cdn_top2")) {
            element.remove();
        }
        for (Element element : doc.select("details__meta")) {
            element.remove();
        }
        for (Element element : doc.select("div.native-ad")) {
            element.remove();
        }
        for (Element element : doc.select("div.col_footer")) {
            element.remove();
        }
        for (Element element : doc.select("div.details__bottombanner")) {
            element.remove();
        }
        for (Element element : doc.select("div.util-box")) {
            element.remove();
        }
        for (Element element : doc.select("div.section_footer")) {
            element.remove();
        }
        for (Element element : doc.select("div[data-view='formcomment']")) {
            element.remove();
        }
        for (Element element : doc.select("p[data-view='nocomment']")) {
            element.remove();
        }
        for (Element element : doc.select("div.relate-container")) {
            element.remove();
        }
        for (Element element : doc.select("div.topbar")) {
            element.remove();
        }
        for (Element element : doc.select("div.box_search_topbar")) {
            element.remove();
        }
        for (Element element : doc.select("div.box_input")) {
            element.remove();
        }
        for (Element element : doc.select("div.section_menu")) {
            element.remove();
        }
        for (Element element : doc.select("div.banner-ads")) {
            element.remove();
        }
        for (Element element : doc.select("div.pull-right")) {
            element.remove();
        }
        for (Element element : doc.select("div.btn-group")) {
            element.remove();
        }
        for (Element element : doc.select("div.block_thumb_slide_show")) {
            element.removeAttr("style");
        }
        for (Element element : doc.select("div.row")) {
            element.removeAttr("style");
        }
        for (Element element : doc.select("a")) {
            if (element.select("[src]").isEmpty()) element.remove();
        }
        for (Element element : doc.select("iframe")) {
            element.remove();
        }
        for (Element element : doc.select("ul")) {
            element.remove();
        }
        for (Element element : doc.select("ul.social_left")) {
            element.remove();
        }
        for (Element element : doc.select("ul.breadcrumb")) {
            element.remove();
        }
        for (Element element : doc.select("ul.list-news")) {
            element.remove();
        }
        for (Element element : doc.select("ul.row")) {
            element.remove();
        }
        for (Element element : doc.select("div.z-widget-corona")) {
            element.remove();
        }
        for (Element element : doc.select("div[id=innerarticle]")) {
            element.remove();
        }
        for (Element element : doc.select("div.tags-scroller")) {
            element.remove();
        }
        for (Element element : doc.select("section[id='news-latest']")) {
            element.remove();
        }
        for (Element element : doc.select("section.recommendation")) {
            element.remove();
        }
        for (Element element : doc.select("div[id=pushed_popup]")) {
            element.remove();
        }
        for (Element element : doc.select("div.firefox-warning")) {
            element.remove();
        }
        for (Element element : doc.select("p.the-article-tags")) {
            element.remove();
        }
        for (Element element : doc.select("section.sidebar")) {
            element.remove();
        }
        for (Element element : doc.select("svg")) {
            element.remove();
        }
        for (Element element : doc.select("nav")) {
            element.remove();
        }
        //TODO remove all attributes from every elements

        for (Element element : doc.getAllElements()) {
            // System.out.println("--"+element.html());
            element.removeAttr("class").removeAttr("id").removeAttr("muted");
            element.attr("autoplay",true).attr("controls",true).attr("muted",true).attr("allowfullscreen",true);
        }

        for (Element element : doc.select("p")) {
            element.attr("style", "font-size: 28px; font-family:sans-serif;");
        }
        for (Element element : doc.select("div[itemprop='articleBody']")) {
            element.select("div").attr("style", "font-size: 28px; font-family:sans-serif;");
        }
        for (Element element : doc.select("div")) {
            element.select("div").attr("style", "font-size: 28px; font-family:sans-serif;");
        }

        for (Element element : doc.select("h1")) {
            element.attr("style", "font-size: 28px;font-family:sans-serif;");
        }
        for (Element element : doc.select("h2")) {
            element.attr("style", "font-size: 28px;font-family:sans-serif;");
        }
        for (Element element : doc.select("a")) {

            if (element.select("img").isEmpty())element.remove();
        }

        doc.select("body").last().after("<div style='height:100px;'>");
        doc.select("body").first().attr("style","width:80%; text-align: justify; text-justify: inter-word;");
        String HTML = doc.html();
        this.HTML = HTML;
        System.out.println("Return HTML successfully" + dir);
        return HTML;
    }

    @Override
    public List<ArticleModel> Crawl(String instruction) throws IOException, ParseException {
        String dir = " @CrawlModelManager/Crawl(instruction)";
        bound = 50000;
        if (instruction.contains("Crawl") && instruction.split(" ").length > 1) {
            List<ArticleModel> articles = Crawl1(instruction.split(" ")[1]);
            return articles;
        }

        System.out.println("Wrong instruction format " + "'" + instruction + "'" + " - show this instruction to the author " + dir);
        System.out.println("Return null List<AriticleModel>" + dir);
        return null;
    }

    @Override
    public List<ArticleModel> getHTMLs() {
        return articles;
    }


    //Publisher.txt is one of this function input
    private List<ArticleModel> Crawl1(String file) throws IOException, ParseException {
        String dir = " @CrawlModelManager/Crawl1(file)";
        List<ArticleModel> articles = new ArrayList<>();
        //parsing a CSV file into Scanner class constructor
        Scanner sc = null;
        try {

            sc = new Scanner(new File("src/com/notfromus/text/" + file));
        } catch (FileNotFoundException e) {
            System.out.println("file not found" + dir);
            System.out.println("Return null List<AriticleModel>" + dir);
            return null;
        }
        Document doc;

        while (sc.hasNext()) {


            String st = sc.next();
            String[] s = st.split(",");
            try {
                doc = Jsoup.connect(s[2]).get();
                System.out.println(s[2] + "connection looks find" + dir);
            } catch (IOException e) {
                System.out.println("cannot get Jsoup connect" + dir);
                System.out.println("Return null List<AriticleModel>" + dir);
                return null;
                //
                //
                // throw new RuntimeException(e);
            }
            Elements pageContent = s[0].contains("Zing") ? doc.getElementsByTag("url") : doc.getElementsByTag("item");
            String[] s1 = new String[0];
            for (Element e : pageContent) {
                bound = bound - 1;
                if (bound == 0) break;
                //System.out.println("---");
                //System.out.println(e.html());
                ArticleModel temp = s[0].contains("Zing")?
                        new ArticleModelManager(e.select("image|caption").text(), e.select("loc").text())
                        : new ArticleModelManager(e.select("title").text(), e.select("link").text());

                if(s[0].contains("Zing")) temp.setImg_src(e.select("image|loc").text());
                if (!s[0].contains("Nhandan") && !s[0].contains("Zing"))
                    if (e.text().contains("src")) {
                        s1 = e.select("description").text().split("src");
                        temp.setImg_src(s1[1].split(Character.toString((char) 34))[1]);

                    }
                if (s[0].contains("Nhandan")) {
                    temp.setImg_src(e.select("enclosure").attr("url"));
                    temp.setImg_src("https://i.ibb.co/tJNKY1Y/placeholder.png");
                    //System.out.println(e.select("enclosure").attr("url"));
                }
                if(temp.getImg_src() == null){
                    temp.setImg_src("https://i.ibb.co/tJNKY1Y/placeholder.png");
                }
                String time = e.select("pubDate").text()+e.select("lastmod").text();
                Date dt = null;
                //System.out.println(time.split("\\+7")[0]);
                if(e.select("pubDate").text().isEmpty()){
                    dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm",Locale.getDefault()).parse(time);

                }
                else{
                    if(e.select("pubDate").text().contains("GMT")){
                        dt = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss",Locale.getDefault()).parse(time.split("GMT")[0]);
                    }else
                    {
                        dt = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ssZ").parse(time);
                    }
                }
                //System.out.println(dt);

                Calendar a =  Calendar.getInstance();
                a.setTime(dt);
                Calendar b = Calendar.getInstance();
                long c = System.currentTimeMillis() - a.getTimeInMillis();
                long seconds = c/1000;
                long hours = seconds/(60*60);
                //System.out.println(hours);
                if(hours <= 48)temp.setTime(String.valueOf(hours)+" hours ago");
                else temp.setTime(String.valueOf(hours/24)+" days ago");
                temp.setT(hours);
                //System.out.println(System.currentTimeMillis());
                //System.out.println(a.getTimeInMillis());

                //System.out.println(temp.getTime());
                articles.add(temp);
                ///BREAK 1 to bao
                if(bound%20==0) break;
            }

        }
        sc.close();  //closes the scanner
        return articles;
    }


}
