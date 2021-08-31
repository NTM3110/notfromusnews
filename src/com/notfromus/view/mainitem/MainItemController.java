package com.notfromus.view.mainitem;

import com.notfromus.models.ArticleModel;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.MalformedURLException;

public class MainItemController {
    @FXML
    private Label title;
    @FXML
    private AnchorPane item;
    @FXML
    private ImageView img;
    @FXML
    private Label time;
    public void setTitleTextColor() {
        item.setOnMouseClicked(evt -> {
            title.setStyle("-fx-text-fill: #373d39");
        });
    }

    public void setData(ArticleModel Newspaper) throws MalformedURLException {
        title.setText(Newspaper.getTitle());
        img.setImage(Newspaper.getImg());
        time.setText(Newspaper.getTime());
    }

}
