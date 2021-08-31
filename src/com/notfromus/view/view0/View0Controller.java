package com.notfromus.view.view0;

import com.notfromus.viewmodel.VMFactory;
import com.notfromus.viewmodel.View0ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class View0Controller {
    @FXML
    private ProgressIndicator categoryProgress;
    @FXML
    private ImageView ivcon;

    @FXML
    private ScrollPane grandmotherInTheCentre;

    @FXML
    private StackPane greatGrandmother;

    @FXML
    private ProgressBar process;

    @FXML
    private ComboBox<String> category;

    @FXML
    private Button reader_btn;
    @FXML
    private Button page_btn;
    @FXML
    private ScrollPane father;

    @FXML
    private GridPane mother;

    private View0ViewModel view0ViewModel;

    public void init(VMFactory vmFactory) throws IOException {
        //Binding
        this.view0ViewModel = vmFactory.getView0ViewModel();
        //view0ViewModel.addMainItem(getMother(),getGrandmotherInTheCentre(),getReader_btn(),getPage_btn());
        //view0ViewModel.setChildren();
        //view0ViewModel.addMouseScrollingCounter(getGrandmotherInTheCentre(),getProcess());
        view0ViewModel.setReaderButton(getReader_btn(),getMother(),getGreatGrandmother(),getPage_btn(),vmFactory, getFather(),getCategory(),getCategoryProgress());
        //view0ViewModel.addMainItem(getMother(),getGrandmotherInTheCentre(),getReader_btn(),getPage_btn(),getFather(),getCategory(),getCategoryProgress());
        view0ViewModel.setCategory(getMother(),getGrandmotherInTheCentre(),getReader_btn(),getPage_btn(),getFather(),getCategory(),getCategoryProgress());
        view0ViewModel.createButtonList(getFather()); //TODO
        getMother().setStyle("-fx-background-color: rgba(255,255,255,0.3)");
        getGrandmotherInTheCentre().setStyle("-fx-background-color: rgba(255,255,255,0.3)");
    }

    public ScrollPane getFather() {
        return father;
    }

    public Button getPage_btn() {
        return page_btn;
    }

    public Button getReader_btn() {
        return reader_btn;
    }

    public ComboBox<String> getCategory() {
        return category;
    }

    public ProgressIndicator getCategoryProgress() {
        return categoryProgress;
    }

    public ProgressBar getProcess() {
        return process;
    }

    public GridPane getMother() {
        return mother;
    }

    public ScrollPane getGrandmotherInTheCentre() {
        return grandmotherInTheCentre;
    }

    public StackPane getGreatGrandmother() {
        return greatGrandmother;
    }

    public View0Controller(){}



}
