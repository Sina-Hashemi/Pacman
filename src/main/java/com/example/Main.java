package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Scene scene;

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        MainMenuController.makeScene();
        // GameController.makeScene();
        stage.setTitle("Pacman Game");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("pacman.png")));
        stage.setScene(scene);
        stage.show();

        primaryStage = stage;
    }

    public static void main(String[] args) {
        launch();
    }

}
