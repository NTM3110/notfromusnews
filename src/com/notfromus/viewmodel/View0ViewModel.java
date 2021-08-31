package com.notfromus.viewmodel;

import com.notfromus.models.ArticleModel;
import com.notfromus.models.CrawlModel;
import com.notfromus.models.ModelFactory;
import com.notfromus.view.mainitem.MainItemController;
import com.notfromus.view.view2.View2Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.concurrent.Worker.State;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class View0ViewModel {

    private int bound = 10;

    private int temp_iterator = 100000; // temp_iterator moves in between bounds

    private int iterator = 0; // temp_iterator moves in between bounds

    private int countup = 0;

    private int countdown = 0;

    private int sensitivity;

    private List<MainItemController> finalMainItemController = new ArrayList<>();

    private View2Controller finalView2Controller;

    private List<ArticleModel> lam;

    private CrawlModel crawlModel;

    private int cells = 8; // Number of article cells on each page

    private ModelFactory modelFactory;

    public ModelFactory getModelFactory() {
        return modelFactory;
    }

    public List<ArticleModel> getLam() {
        return lam;
    }

    public int getIterator() {
        return iterator;
    }

    private WebView htmlEditor;

    private Node article = null;

    //Initiate basis stat for view 0
    public View0ViewModel(ModelFactory modelFactory) {
        this.finalView2Controller = new View2Controller();
        this.modelFactory = modelFactory;
        lam = new ArrayList<>();
        crawlModel = this.modelFactory.getCrawlModel();
        this.bound = 8;
        temp_iterator = 0; // temp_iterator moves in between bounds
        countup = 0;
        countdown = 0;
        this.sensitivity = 1;
        htmlEditor = new WebView();
        htmlEditor.setCache(true);
        htmlEditor.setCacheHint(CacheHint.SPEED);
        htmlEditor.setTranslateY(90);
        htmlEditor.setStyle("-fx-opacity: 0");
        htmlEditor.setDisable(true);

    }

    public void addMouseScrollingCounter(ScrollPane scrollPane, ProgressBar progressBar) throws MalformedURLException {
        addProgressBarChangeScrollCounter(progressBar);
        scrollPane.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                addVirtualCounter(scrollEvent.getDeltaY(), progressBar);
            }
        });

        scrollPane.vvalueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                //addVirtualCounter(t1.doubleValue(),progressBar);
                // i.setData(getLam().get(getIterator()));
            }
        });
    }

    private void addProgressBarChangeScrollCounter(ProgressBar progressBar) {
        double a = (double) (getIterator() + 1) / bound;
        progressBar.setProgress(a);
    }

    private void addVirtualCounter(double deltaY, ProgressBar progressBar) {
        if (countdown == sensitivity) {
            //System.out.println(deltaY);
            countdown = 0;
            temp_iterator++;
            //System.out.println(StrictMath.abs(temp_iterator % bound));
            this.iterator = StrictMath.abs(temp_iterator % bound);
            System.out.println(this.iterator);
            addProgressBarChangeScrollCounter(progressBar);
            addContentChangeScrollCounter();
        }
        if (countup == sensitivity) {
            //System.out.println(deltaY);
            countup = 0;
            temp_iterator--;
            //System.out.println(StrictMath.abs(temp_iterator % bound));
            this.iterator = StrictMath.abs(temp_iterator % bound);
            System.out.println(this.iterator);
            addProgressBarChangeScrollCounter(progressBar);
            addContentChangeScrollCounter();
        }
        System.out.println(deltaY);
        if (deltaY > 0) {
            countup++;
        }
        if (deltaY < 0) {
            countdown++;
        }
    }

    private void addContentChangeScrollCounter() {
        int j = 0;
        for (MainItemController i : finalMainItemController) {
            int k = getIterator() * cells + (j++) % cells;
            try {
                if(k<getLam().size())
                i.setData(getLam().get(k));
                //System.out.println(k);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setChildren(String file) throws IOException, ParseException {
        String instruction = "Crawl " + file + ".txt";
        lam.addAll(crawlModel.Crawl(instruction));
        Comparator<ArticleModel> comparator = Comparator.comparing(ArticleModel::getT);
        Collections.sort(lam,comparator);
        //lam.sort(Comparator.comparingLong(ArticleModel::getT));
        this.bound = lam.size() / cells;
        System.out.println(lam.size());

    }

    public void setCategory(GridPane gridPane, ScrollPane scrollPane, Button reader_btn, Button page_btn, ScrollPane scrollPane1, ComboBox<String> category, ProgressIndicator categoryProgress) {
        category.setMinHeight(45);
        List<String> cat = new ArrayList<>();
        cat.add("Newest");
        cat.add("Covid-19");
        cat.add("Politic");
        cat.add("Business");
        cat.add("World");
        cat.add("Technology");
        cat.add("Sport");
        cat.add("Entertainment");
        categoryProgress.setStyle("-fx-opacity: 0");
        scrollPane1.setStyle("-fx-opacity: 0");
        category.setItems(FXCollections.observableList(cat));
        category.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                int selectedIndex = category.getSelectionModel().getSelectedIndex();
                Object selectedItem = category.getSelectionModel().getSelectedItem();
                System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
                System.out.println("   ComboBox.getValue(): " + category.getValue());
                lam = new ArrayList<>();
                gridPane.getChildren().removeAll(gridPane.getChildren());
                scrollPane1.setContent(null);
                Service service = new Service() {
                    @Override
                    protected Task createTask() {
                        return new Task() {
                            @Override
                            protected Object call() throws Exception {
                                setChildren(category.getValue());
                                return null;
                            }
                        };
                    }

                    @Override
                    protected void succeeded() {
                        categoryProgress.setStyle("-fx-opacity: 0");
                        try {
                            addMainItem(gridPane, scrollPane, reader_btn, page_btn, scrollPane1, category, categoryProgress);
                            createButtonList(scrollPane1);
                            scrollPane1.setStyle("-fx-opacity: 1");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                };
                categoryProgress.setStyle("-fx-opacity: 1");
                categoryProgress.progressProperty().bind(service.progressProperty());
                service.start();

            }
        });
        category.setOnAction((event) -> {

        });
    }

    public void setReaderButton(Button reader_btn, GridPane gridPane, StackPane stackPane, Button page_btn, VMFactory vmFactory, ScrollPane scrollPane, ComboBox<String> category, ProgressIndicator categoryProgress) {
        page_btn.setStyle("-fx-background-color: rgba(200,200,200,1)");
        page_btn.setStyle("-fx-opacity: 0");
        reader_btn.setStyle("-fx-background-color: rgba(200,200,200,0.3)");
        stackPane.setStyle("-fx-background-color: transparent");
        gridPane.setStyle("-fx-background-color: transparent");
        //view2Controller.getView2ViewModel().setHtmlEditor("https://google.com",view2Controller.getHtmlEditor());
        htmlEditor.setMaxHeight(820);
        stackPane.getChildren().add(htmlEditor);
        reader_btn.setOnMouseClicked(mouseEvent -> {
            gridPane.setStyle("-fx-opacity: 0");
            category.setStyle("-fx-opacity: 0");

            scrollPane.setStyle("-fx-opacity: 0");
            category.setDisable(true);

            scrollPane.setDisable(true);
            gridPane.setDisable(true);
            htmlEditor.setStyle("-fx-opacity: 1");
            htmlEditor.setDisable(false);
            reader_btn.setStyle("-fx-background-color: rgba(200,200,200,1)");
            page_btn.setStyle("-fx-background-color: rgba(200,200,200,0.3)");

        });
        page_btn.setOnMouseClicked(mouseEvent -> {
            page_btn.setStyle("-fx-background-color: rgba(200,200,200,1)");
            page_btn.setStyle("-fx-opacity: 0");
            reader_btn.setStyle("-fx-background-color: rgba(200,200,200,0.3)");
            gridPane.setStyle("-fx-opacity: 1");
            scrollPane.setStyle("-fx-opacity: 1");
            category.setStyle("-fx-opacity: 1");
            category.setDisable(false);
            gridPane.setDisable(false);
            scrollPane.setDisable(false);
            htmlEditor.setStyle("-fx-opacity: 0");
            htmlEditor.setDisable(true);
        });
    }

    public void addMainItem(GridPane gridPane, ScrollPane scrollPane, Button reader_btn, Button page_btn, ScrollPane scrollPane1, ComboBox<String> category, ProgressIndicator categoryProgress) throws MalformedURLException {
        MainItemController mainItemController = new MainItemController();
        scrollPane.setStyle("-fx-background-color: transparent");
        gridPane.setStyle("-fx-background-color: transparent");
        gridPane.getChildren().removeAll(gridPane.getChildren());
        int count = 0;
        int row = 0, column = 0;
        for (ArticleModel i : getLam()) {
            // Number of row of cells on each page
            int row1 = 2;
            if (count % row1 == 0) {
                column = 0;
                row++;
            }
            FXMLLoader local = new FXMLLoader();
            local.setLocation(getClass().getResource("../view/mainitem/mainitem.fxml"));
            try {
                article = local.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mainItemController = local.getController();
            mainItemController.setData(i);
            addClickListener(article, count, gridPane, reader_btn, page_btn, scrollPane1, category, categoryProgress);
            finalMainItemController.add(mainItemController);
            gridPane.setHgap(5);
            gridPane.setVgap(10);
            gridPane.add(article, column++, row);
            if (count == cells-1) break;
            count++;
        }
        scrollPane.setContent(gridPane);
    }

    private void addClickListener(Node article, int i, GridPane gridPane, Button reader_btn, Button page_btn, ScrollPane scrollPane, ComboBox<String> category, ProgressIndicator categoryProgress) {
        article.setOnMouseClicked(evt -> {
            //htmlEditor.getEngine().load("http://javafx.com");

            //htmlEditor.getEngine().load(getLam().get(cells*getIterator()+i).getDirect_url());
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/com/notfromus/text/out.o"));
                writer.write(crawlModel.crawl(getLam().get(cells * getIterator() + i).getDirect_url()));
                htmlEditor.getEngine().loadContent(crawlModel.crawl(getLam().get(cells * getIterator() + i).getDirect_url()));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            htmlEditor.setContextMenuEnabled(false);
            //htmlEditor.setHtmlText(crawlModel.crawl(getLam().get(cells*getIterator()+i).getDirect_url()));
            reader_btn.setStyle("-fx-background-color: rgba(200,200,200,1)");
            page_btn.setStyle("-fx-background-color: rgba(200,200,200,0.3)");
            htmlEditor.setStyle("-fx-opacity: 1");
            htmlEditor.setDisable(false);
            //System.out.println(getLam().get(cells*getIterator()+i).getDirect_url());
            category.setStyle("-fx-opacity: 0");
            categoryProgress.setStyle("-fx-opacity: 0");
            scrollPane.setStyle("-fx-opacity: 0");
            category.setDisable(true);
            categoryProgress.setDisable(true);
            gridPane.setStyle("-fx-opacity: 0");
            gridPane.setDisable(true);
            scrollPane.setStyle("-fx-opacity: 0");
            scrollPane.setDisable(true);

        });
    }

    public void createButtonList(ScrollPane scrollPane) {
        HBox hBox = new HBox();
        ToggleGroup toggleGroup = new ToggleGroup();
        hBox.setStyle("-fx-background-color: transparent");
        for (int i = 0; i < bound; i++) {
            ToggleButton b = new ToggleButton();
            b.setText("Page " + String.valueOf(i));
            toggleGroup.getToggles().add(b);
            b.setOnMouseClicked(evt -> {
                this.iterator = Integer.parseInt(b.getText().split(" ")[1]);
                addContentChangeScrollCounter();
            });
            //System.out.println(scrollPane.getMaxHeight());
            b.setMinHeight(20);
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(b);
            hBox.setSpacing(10);

        }
        scrollPane.setContent(null);
        scrollPane.setContent(hBox);
    }

}



