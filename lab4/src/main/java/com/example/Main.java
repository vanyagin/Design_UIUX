package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Slider;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    private static final double SIZE = 400;
    private final Content content = Content.create(SIZE);

    private static final class Content {

        private final Group group = new Group();
        private final Group cube = new Group();
        private final Group axes = new Group();
        private final Rotate rx = new Rotate(0, Rotate.X_AXIS);
        private final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
        private final Rotate rz = new Rotate(0, Rotate.Z_AXIS);
        private final Box box1;
        private final Box box2;
        private final Box box3;
        private final Box box4;
        private PointLight light;

        private AmbientLight ambientLight;

        private static Content create(double size) {
            Content c = new Content(size);
            c.cube.getChildren().addAll(c.box1, c.box2, c.box3, c.box4);
            c.cube.getTransforms().addAll(c.rz, c.ry, c.rx);
            c.group.getChildren().addAll(c.cube, c.axes);
            c.group.getChildren().addAll(c.light);
            c.group.getChildren().addAll(c.ambientLight);
            return c;
        }

        private Content(double size) {

            double edge = SIZE/2;
            PhongMaterial material1 = new PhongMaterial();
            Image tex1 = new Image("/gold.jpg");
            Image tex = new Image("/1.png");

            Blend blend = new Blend();
            blend.setMode(BlendMode.MULTIPLY);

            material1.setDiffuseMap(tex1);
            //material1.setDiffuseMap(tex);
            box1 = new Box(edge, edge, edge);
            box1.setMaterial(material1);

            box1.setLayoutX(0);

            box2 = new Box(edge, edge, edge);
            Image tex2 = new Image("/pattern.jpg");
            PhongMaterial material2 = new PhongMaterial();
            material2.setDiffuseMap(tex2);
            box2.setMaterial(material2);
            box2.setTranslateX(edge);
            box2.setLayoutX(0);

            box3 = new Box(edge, edge, edge);
            //box3.setMaterial(new PhongMaterial(Color.SILVER));
            Image tex3 = new Image("/stone.jpg");
            PhongMaterial material3 = new PhongMaterial();
            material3.setDiffuseMap(tex3);
            box3.setMaterial(material3);
            box3.setTranslateX(-edge);
            box3.setLayoutX(0);

            box4 = new Box(edge, edge, edge);
            box4.setMaterial(new PhongMaterial(Color.GOLD));
            box4.setTranslateY(edge);
            box4.setLayoutX(0);


            light = new PointLight();
            light.setTranslateZ(-1000);
            light.setTranslateX(1000);
            light.setTranslateY(+10);

            light.getScope().addAll(box1,box2,box3,box4);

            ambientLight = new AmbientLight();
            //ambientLight.setTranslateZ(-1000);
            //ambientLight.setTranslateY(+100000);
            //ambientLight.setColor(Color.RED);
            //ambientLight.setLightOn(false);
            ambientLight.getScope().addAll(box1,box2,box3,box4);
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("lab4");

        VBox root = new VBox(10);

        SubScene subScene = new SubScene(content.group, SIZE * 2, SIZE * 2, true, SceneAntialiasing.BALANCED);

        //sliders.setAlignment(Pos.CENTER);
        root.getChildren().addAll(subScene);
        Scene scene = new Scene(root, SIZE * 2, SIZE * 2 + 150, true, SceneAntialiasing.BALANCED);

        primaryStage.setScene(scene);

        subScene.setOnMouseDragged((final MouseEvent e) -> {
            content.rx.setAngle(-e.getSceneY() * 360 / scene.getHeight());
            content.ry.setAngle(-e.getSceneX() * 360 / scene.getWidth());
        });

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setFarClip(SIZE * 100);
        camera.setTranslateZ(-5 * SIZE);
        subScene.setCamera(camera);
        subScene.setOnScroll((final ScrollEvent e) -> {
            camera.setTranslateZ(camera.getTranslateZ() + e.getDeltaY());
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}