package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

import java.io.Console;
import java.util.*;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        stage.setResizable(false);
        Scene scene = new Scene(root, 250.0, 250.0);

        ArrayList<TextField> textFields = new ArrayList<TextField>(6);
        ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>(6);
        VBox vboxTF = new VBox();
        VBox vboxCheck = new VBox(10);
        for (int i = 0; i < 6; i++) {
            var strLabel = String.valueOf(i+1);
            var textF = new TextField(strLabel);
            var checkF = new CheckBox(strLabel);
            textFields.add(textF);
            checkBoxes.add(checkF);
            vboxTF.getChildren().add(new HBox(new Label(strLabel),textF));
            vboxCheck.getChildren().add(new HBox(new Label(strLabel),checkF));
        }

        var scroll = new ScrollPane(vboxTF);

        HBox hBoxTF = new HBox(10);
        hBoxTF.setPrefWidth(400);
        hBoxTF.setMaxWidth(400);
        hBoxTF.getChildren().addAll(scroll);
        hBoxTF.setAlignment(Pos.TOP_CENTER);


        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");

        HBox buttons = new HBox(5);
        buttons.setPadding(new Insets(5));
        buttons.setAlignment(Pos.TOP_CENTER);
        buttons.getChildren().addAll(button1, button2, button3);

        Label messageTextField = new Label();
        messageTextField.setPrefWidth(150);
        messageTextField.setMaxWidth(250);
        messageTextField.setPadding(new Insets(5));

        button1.setOnAction(event-> {
            Stage stage1 = new Stage();
            TextField tf = new TextField();

            VBox root1 = new VBox(15.0);
            Scene scene1 = new Scene(root1,150.0, 80.0);
            root1.getChildren().addAll(tf);

            tf.addEventHandler(ActionEvent.ACTION, ev -> {
                tf.getText();
                try{
                    var coef = Double.parseDouble(tf.getText());
                    if (coef>0)
                    {
                        if (!stage.isShowing()) stage.show();
                        stage.setWidth(stage.getWidth() * coef );
                        stage.setHeight(stage.getHeight() * coef );

                    }
                    else {
                        messageTextField.setText("Вы ввели число, меньшее нуля");
                    }
                }
                catch (NumberFormatException e){
                    messageTextField.setText("Вы ввели не число");
                }

            });

            stage1.setScene(scene1);
            stage1.show();
        });
        button2.setOnAction(event-> {
            Stage stage2 = new Stage();
            vboxCheck.setAlignment(Pos.TOP_CENTER);
            var scrollCheck = new ScrollPane(vboxCheck);
            vboxCheck.addEventHandler(ActionEvent.ACTION, ev -> {
                //CheckBox chk = (CheckBox) event.getSource();
                for (int i = 0; i < 6; i++) {
                    if (checkBoxes.get(i).isSelected()) {
                        textFields.get(i).setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
                    }
                }
            });
            Scene scene2 = new Scene(scrollCheck,150.0, 160.0);

            stage2.setScene(scene2);
            stage2.show();
        });
        button3.setOnAction(event-> {
            Stage stage3 = new Stage();
            VBox root3 = new VBox();
            root3.setAlignment(Pos.TOP_CENTER);
            TextField textFieldNum = new TextField("1");
            Button addButton = new Button("Добавить");
            addButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    var l = String.valueOf(textFields.size()+1);
                    Label add_label = new Label(l);
                    TextField add_tf = new TextField(l);
                    CheckBox add_check = new CheckBox(l);
                    textFields.add(add_tf);
                    checkBoxes.add(add_check);
                    vboxCheck.getChildren().add(new HBox(new Label(l),add_check));
                    vboxTF.getChildren().add(new HBox(new Label(l),add_tf));
                }
            });
            Button delButton = new Button("Удалить");
            delButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (!textFields.isEmpty()) {
                        var id = Integer.parseInt(textFieldNum.getText())-1;
                        if (id > textFields.size() - 1){
                            messageTextField.setText("Вы ввели некорректный номер!");
                        }
                        else {
                            textFields.remove(id);
                            vboxTF.getChildren().remove(id);
                        }
                    }
                    }
            });
            root3.getChildren().addAll(textFieldNum,addButton, delButton);
            root3.setSpacing(5);
            Scene scene3 = new Scene(root3,180.0, 120.0);
            stage3.setScene(scene3);
            stage3.show();
        });
        VBox vBox = new VBox();
        vBox.getChildren().addAll(buttons, messageTextField);
        vBox.setAlignment(Pos.TOP_CENTER);

        root.getChildren().addAll(hBoxTF);
        root.getChildren().addAll(vBox);

        stage.setTitle("lab 2");
        stage.setScene(scene);
        stage.show();
    }
}