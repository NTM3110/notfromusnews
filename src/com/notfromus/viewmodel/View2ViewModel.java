package com.notfromus.viewmodel;

import com.notfromus.models.CrawlModel;
import com.notfromus.models.ModelFactory;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class View2ViewModel {

    private ModelFactory modelFactory;

    private CrawlModel crawlModel;
    private Boolean flag = true;
    int s = 40;
    String size = String.valueOf(s) + "px;";

    public View2ViewModel(ModelFactory modelFactory){
        this.modelFactory = modelFactory;
        this.crawlModel = modelFactory.getCrawlModel();

    }

    public void setMouseListener(Label lb, AnchorPane anchor, StackPane stack) {
        lb.setText("Java is cool!");

        anchor.setPrefHeight(800);
        anchor.setPrefWidth(800);
        lb.setStyle("-fx-font-family:Arial Unicode MS; -fx-font-weight: bold;");
        lb.setTextFill(Color.BLUE);
        lb.setStyle("-fx-font-size: "+ size);
        anchor.setOnMouseClicked(mouseEvent -> {
            if(flag){
                lb.setTextFill(Color.RED);
                flag = false;
            }else {
                lb.setTextFill(Color.BLUE);
                flag = true;
            }
        });
        stack.setPrefHeight(anchor.getPrefHeight());
        stack.setPrefWidth(anchor.getPrefWidth());
        stack.setFocusTraversable(true);
        stack.setOnKeyPressed(keyEvent -> {
            System.out.println(keyEvent.getCode().toString());
            if(keyEvent.getCode().toString().contains("UP")){
               if(s+5<80){
                   change(5);
                   lb.setStyle("-fx-font-size: "+ size);
               }
            }
            if(keyEvent.getCode().toString().contains("DOWN")){
                if(s-5>10){
                    change(-5);
                    lb.setStyle("-fx-font-size: "+ size);
                }
            }
        });
    }

    void change(int k){
        s = s+k;
        size = String.valueOf(s) + "px;";

    }
}
