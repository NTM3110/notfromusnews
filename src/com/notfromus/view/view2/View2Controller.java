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
package com.notfromus.view.view2;

import com.notfromus.viewmodel.VMFactory;
import com.notfromus.viewmodel.View2ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.HTMLEditor;

public class View2Controller {
    @FXML
    private Label lb;
    @FXML
    private AnchorPane anchor;
    @FXML
    private StackPane stack;

    private View2ViewModel view2ViewModel;

    private VMFactory vmFactory;



    public void init(VMFactory vmFactory){
        this.vmFactory = vmFactory;
        this.view2ViewModel = vmFactory.getView2ViewModel();
        this.view2ViewModel.setMouseListener(getLb(),getAnchor(),getStack());
    }

    public Label getLb() {
        return lb;
    }

    public AnchorPane getAnchor() {
        return anchor;
    }

    public StackPane getStack() {
        return stack;
    }

    public View2ViewModel getView2ViewModel() {
            return view2ViewModel;
    }

}
