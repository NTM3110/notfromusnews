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
package com.notfromus;

import com.notfromus.models.ModelFactory;
import com.notfromus.view.ViewHandler;
import com.notfromus.viewmodel.VMFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class notfromusnewsApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ModelFactory mf = new ModelFactory();
        VMFactory vmf = new VMFactory(mf);
        ViewHandler vh = new ViewHandler(primaryStage, vmf);
        vh.start();
    }
    public static void main(String[] args) {
        Application.launch(notfromusnewsApp.class);
    }
}
