package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class MainMenuController {

    @FXML
    private void startGame1() throws IOException {
        copyMap(GameMap.map1);
        GameController.makeScene();
    }
    @FXML
    private void startGame2() throws IOException {
        copyMap(GameMap.map2);
        GameController.makeScene();
    }
    @FXML
    private void startGame3() throws IOException {
        copyMap(GameMap.map3);
        GameController.makeScene();
    }

    public static void makeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), GameMap.width * GameMap.blockSize, GameMap.height * GameMap.blockSize);
    }

    public static void goBack() {
        try {
            // Scene mainScreen = new Scene(FXMLLoader.load(Main.class.getResource("MainMenu.fxml")), GameMap.width * GameMap.blockSize, GameMap.height * GameMap.blockSize);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
            Main.scene = new Scene(fxmlLoader.load(), GameMap.width * GameMap.blockSize, GameMap.height * GameMap.blockSize);
            Main.primaryStage.setScene(Main.scene); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyMap(String[][] baseMap) {
        for (int i = 0; i < baseMap.length; i++) {
            System.arraycopy(baseMap[i], 0, GameMap.map[i], 0, baseMap[i].length);
        }
    }

}
