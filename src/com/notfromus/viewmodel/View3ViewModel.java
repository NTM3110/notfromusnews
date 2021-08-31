package com.notfromus.viewmodel;

import com.notfromus.models.ModelFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;


public class View3ViewModel {
    private ModelFactory modelFactory;
    public View3ViewModel(ModelFactory modelFactory){
        this.modelFactory = modelFactory;
    }


    public void exit(Button exit) {
        exit.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        });
    }

    public void deal(HBox cards, Button deal, Label score) throws URISyntaxException {


        deal.setOnMouseClicked(mouseEvent -> {
            //Find current path
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            System.out.println("Current absolute path is: " + s);

            //Assign random value
            Random random = new Random();
            int k= random.nextInt(51)+1;
            int k1 = random.nextInt(51)+1;
            int k2 = random.nextInt(51)+1;

            //From the path and value find correct image
            String url = s+"\\src\\com\\notfromus\\cards\\cards\\"+k+".png";
            String url1 = s+"\\src\\com\\notfromus\\cards\\cards\\"+k1+".png";
            String url2 = s+"\\src\\com\\notfromus\\cards\\cards\\"+k2+".png";
            Image i = null;
            Image i1 = null;
            Image i2 = null;

            //Calculate score
            if(k%13!=0) k = (k%13)>=10?10:k%13; else k = 10;
            if(k1%13!=0) k1 = (k1%13)>=10?10:k1%13; else k1 = 10;
            if(k2%13!=0) k2 = (k2%13)>=10?10:k2%13; else k2 = 10;
            score.setText(String.valueOf(k+k1+k2) + "points");

            //Add images to image views
            try {
                i = new Image(new FileInputStream(url),100,0,true,true);
                i1 = new Image(new FileInputStream(url1),100,0,true,true);
                i2 = new Image(new FileInputStream(url2),100,0,true,true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ImageView a = new ImageView(i);
            ImageView b = new ImageView(i1);
            ImageView c = new ImageView(i2);

            //Add image views to Hbox
            cards.getChildren().removeAll(cards.getChildren());
            cards.getChildren().add(a);
            cards.getChildren().add(b);
            cards.getChildren().add(c);
        });
    }
}
