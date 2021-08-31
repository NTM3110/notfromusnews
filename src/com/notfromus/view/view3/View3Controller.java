/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2021B
  Assessment: Test 2
  Author: Cao Duy Minh
  ID: s3818272
  Created  date: 30/8/2021
  Acknowledgement: Google
*/
package com.notfromus.view.view3;

import com.notfromus.viewmodel.VMFactory;
import com.notfromus.viewmodel.View2ViewModel;
import com.notfromus.viewmodel.View3ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URISyntaxException;

public class View3Controller {
    private VMFactory vmFactory;
    private View3ViewModel view3ViewModel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private HBox cards;

    @FXML
    private Label score;

    @FXML
    private Button deal;

    @FXML
    private Button exit;

    public void init(VMFactory vmFactory) throws URISyntaxException {
        this.vmFactory = vmFactory;
        this.view3ViewModel = vmFactory.getView3ViewModel();
        //this.view3ViewModel.red(getAnchorPane());
        this.view3ViewModel.exit(getExit());
        this.view3ViewModel.deal(getCards(),getDeal(),getScore());
    }

    public HBox getCards() {
        return cards;
    }

    public Button getDeal() {
        return deal;
    }

    public Label getScore() {
        return score;
    }

    public Button getExit() {
        return exit;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
}
