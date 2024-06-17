package com.example.lab1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        GridPane gridPane = new GridPane();
        Button gridButton1 = new Button("Button 1");
        gridPane.add(gridButton1, 1, 0);
        Button gridButton2 = new Button("Button 2");
        gridPane.add(gridButton2, 0, 1);
        gridPane.setStyle("-fx-background-color: green;");


        AnchorPane anchorPane = new AnchorPane();
        Button anchorButton = new Button("Anchor Button");
        AnchorPane.setTopAnchor(anchorButton, 10.0);
        AnchorPane.setLeftAnchor(anchorButton, 100.0);
        anchorPane.setStyle("-fx-background-color: grey;");
        anchorPane.getChildren().add(anchorButton);


        BorderPane borderPane = new BorderPane();
        Button topButton = new Button("Border Top Button");
        borderPane.setTop(topButton);
        Button centerButton = new Button("Border Center Button");
        borderPane.setCenter(centerButton);
        borderPane.setStyle("-fx-background-color: yellow;");



        Label hBoxLabel = new Label();
        hBoxLabel.setText("HBox buttons:");
        HBox hBox = new HBox(hBoxLabel);
        Button hbButton1 = new Button("HButton 1");
        Button hbButton2 = new Button("HButton 2");
        Button hbButton3 = new Button("HButton 3");
        hBox.getChildren().addAll(hbButton1, hbButton2, hbButton3);


        Label vBoxLabel = new Label();
        vBoxLabel.setText("VBox buttons:");
        VBox vBox = new VBox(vBoxLabel);
        Button vbButton1 = new Button("VButton 1");
        Button vbButton2 = new Button("VButton 2");
        Button vbButton3 = new Button("VButton 3");
        Button vbButton4 = new Button("VButton 44");
        vBox.getChildren().addAll(vbButton1, vbButton2, vbButton3, vbButton4);


        VBox splitter = new VBox();
        splitter.getChildren().add(gridPane);
        splitter.getChildren().add(anchorPane);
        splitter.getChildren().add(borderPane);
        splitter.getChildren().add(hBox);
        splitter.getChildren().add(vBox);

        startScene(stage, splitter);
    }

    private void startScene(Stage stage, Pane pane) {
        Scene scene = new Scene(pane, 500, 500);
        stage.setTitle("Hello lab1 app");
        stage.setMinHeight(300);
        stage.setMinWidth(300);
        stage.setMaxHeight(900);
        stage.setMaxWidth(900);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}