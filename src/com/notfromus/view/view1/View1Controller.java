package com.notfromus.view.view1;

import com.notfromus.view.mainitem.MainItemController;
import com.notfromus.models.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class View1Controller implements Initializable {
    @FXML
    private ScrollPane shelf;
    @FXML
    private VBox bg;
    @FXML
    private HTMLEditor wv;
    @FXML
    private Button allitems_btn;

    @FXML
    private Button favorite_btn;

    @FXML
    private Button trash_btn;

    @FXML
    private Button covid_btn;

    @FXML
    private Button politic_btn;

    @FXML
    private Button business_btn;

    @FXML
    private Button technology_btn;

    @FXML
    private Button sport_btn;

    @FXML
    private Button health_btn;

    @FXML
    private Button onehour_btn;

    @FXML
    private Button fourhour_btn;

    @FXML
    private Button twelvehour_btn;

    @FXML
    private Button foureighthour_btn;

    @FXML
    private Button mainaddbtn;

    @FXML
    private Button mainreloadbtn;

    @FXML
    protected VBox mother;

    @FXML
    protected Label category_lb;

    @FXML
    protected Label time_lb;
    protected String myHTML;
    protected List<Node> html_editor = new ArrayList<>();
    private HTMLEditor he;
    //Fix scroll pane slow scrolling

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainreloadbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                myStart();
            }
        });
        //Mother chooses smart baskets instead of regular baskets for her shop
        //She had them made with an AI inside
        //Every day, we wait for the news truck, help unload into the basket and put it up the Vbox for display
        //PS: She usually wants as many newspapers as her baskets' length


        myStart();
    }

    private void myStart() {
        he = new HTMLEditor();
        he.setFocusTraversable(false);
        shelf.setPannable(true);
        he.getStylesheets().add("com/notfromus/css/styling.css");
        bg.getChildren().removeAll(bg.getChildren());
        bg.setSpacing(10);
        bg.getChildren().add(he);
        shelf.setContent(bg);
        bg.setStyle("-fx-border-radius: 20; -fx-background-radius: 20;");
        shelf.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY();
            double contentHeight = shelf.getContent().getBoundsInLocal().getHeight();
            double shelfHeight = shelf.getHeight();
            double diff = contentHeight - shelfHeight;
            if (diff < 1) diff = 1;
            double vvalue = shelf.getVvalue();
            shelf.setVvalue(vvalue + -deltaY / diff);
        });
        mother.getChildren().removeAll(mother.getChildren());
        try {
            Newspaper = add_content_to_newspaper();
            for (int i = 0; i < Newspaper.size(); i++) {
                Node smart_basket = prepare_basket(i);
                mother.getChildren().add(smart_basket);
            }

            // Mother tells children to stack the smart_basket up into the VBox for display :(
            // -Haardei, if we try.
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    //prepare_basket is the main method.
    private List<ArticleModel> Newspaper = new ModelFactory().getArticlesModel();

    //{}
    private Node prepare_basket(int i) throws IOException {
        FXMLLoader delivery_truck = visit_news_factory();
        Node smart_basket = delivery_truck.load();
        put_paper_into_basket(delivery_truck.getController(), i);
        smart_basket = create_individual_AI_for_basket(smart_basket, i);
        //Adding feature to basket
        return smart_basket;
    }

    //{}
    private void put_paper_into_basket(MainItemController MIC, int i) throws MalformedURLException {
        //System.out.println(Newspaper.get(0).getDirect_url());
        MIC.setData(Newspaper.get(i));
        MIC.setTitleTextColor();
    }

    //{}
    private FXMLLoader visit_news_factory() throws MalformedURLException {
        //Under here is a newspaper factory
        //Right now it is printing information onto papers.
        FXMLLoader material_truck = new FXMLLoader();
        material_truck.setLocation(getClass().getResource("../fxml/mainitem.fxml"));
        //Put information from truck on to paper
        return material_truck;
    }

    //{}
    private List<ArticleModel> add_content_to_newspaper() throws IOException, ParseException {
        List<ArticleModel> Newspaper;
        //Decide whether the local content or additional library crawled contents
        Newspaper = add_internet_content();
        //Newspaper = add_local_content();
        return Newspaper;
    }

    //{}
    private List<ArticleModel> add_internet_content() throws IOException, ParseException {
        List<ArticleModel> a = new ArrayList<>();
        ModelFactory content = new ModelFactory();
        a.addAll(content.getCrawlModel().Crawl("Crawl publisher.txt"));
        return a;
    }

    //{}
    private List<ArticleModel> add_local_content() {
        List<ArticleModel> a = new ArrayList<ArticleModel>();
        a.add(new ArticleModelManager("Jack eats shit", "https://google.com"));
        return a;
    }
    //{}

    private Node create_individual_AI_for_basket(Node smart_basket, int i) {
        smart_basket.setOnMouseEntered(evt -> {
            //add AI into basket
            //smart_basket.setStyle("-fx-background-color: #165DDB");
        });
        smart_basket.setOnMouseExited(evt -> {
            //smart_basket.setStyle("-fx-background-color: #1e1e1e");
        });
        smart_basket.setOnMousePressed(evt -> {
            if (smart_basket.getStyle().contains("rgba(128, 138, 131, 1)")) {
                //Do something
                bg.setStyle("-fx-opacity: 1");
                smart_basket.setStyle("-fx-effect: dropshadow(gaussian, black, 50, 0, 0, 0);");
                category_lb.setText(Newspaper.get(i).getTitle());
                time_lb.setText(Newspaper.get(i).getDirect_url());
                //System.out.println(Newspaper.get(i).getDirect_url());
                ModelFactory html = new ModelFactory();
                he.setHtmlText(
                        html.getCrawlModel().crawl(Newspaper.get(i).getDirect_url()));
            } else {
                String html = "<p><img src='" + Newspaper.get(i).getImg_src() + "' height='100%' width='100%'/></p>";
                //String html = null;
                WebView wv = (WebView) he.lookup("WebView");
                try {
                    wv.getEngine().load(String.valueOf(new URL(Newspaper.get(i).getImg_src())));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                smart_basket.setStyle("-fx-background-color: rgba(128, 138, 131, 1)");
                he.setHtmlText(html);
                System.out.println(Newspaper.get(i).getDirect_url());

            }


        });
        return smart_basket;
    }
}
