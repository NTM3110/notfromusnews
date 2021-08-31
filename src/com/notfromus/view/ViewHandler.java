package com.notfromus.view;



import com.notfromus.view.view0.View0Controller;
import com.notfromus.view.view2.View2Controller;
import com.notfromus.view.view3.View3Controller;
import com.notfromus.viewmodel.VMFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.plaf.basic.BasicListUI;
import java.io.IOException;
import java.net.URISyntaxException;

import static javafx.fxml.FXMLLoader.load;



public class ViewHandler extends Thread{

    private Stage primaryStage;
    private VMFactory vmFactory;
    private View0Controller view0Controller;
    private View2Controller view2Controller; //Test 2 Question 1
    private View3Controller view3Controller; // Test 2 Question 2


    public void start() {
        try {
            openView("view0"); // Test 2 Question 2
            //openView("view2"); //Test 2 Question 1
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //Set view wait for buttons pressed
    private void openView (String viewToOpen) throws IOException, InterruptedException, URISyntaxException {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewToOpen+"/"+viewToOpen+".fxml"));
        root = loader.load();

        if("view0".equals(viewToOpen)) {
            view0Controller = loader.getController();
            view0Controller.init(getVmFactory());
        }

        if("view2".equals(viewToOpen)) {
            view2Controller = loader.getController();
            view2Controller.init(getVmFactory());
        }
        if("view3".equals(viewToOpen)) {
            view3Controller = loader.getController();
            view3Controller.init(getVmFactory());
        }
        Scene s;
        s = new Scene(root);
        primaryStage.setScene(s);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

        //getcounter/iteratorvalue



    }
    private VMFactory getVmFactory() {
        return vmFactory;
    }


    public ViewHandler(Stage stage,VMFactory vmFactory){
        this.primaryStage = stage;
        this.vmFactory = vmFactory;
    }



}
