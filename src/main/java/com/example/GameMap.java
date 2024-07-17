package com.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.io.File;

public class GameMap {

    static String[][] map1 = new String[][]{
        {"W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"W", "C", "C", "C", "C", "C", "C", "C", "C", "W", "C", "C", "C", "C", "C", "C", "C", "C", "W"},
        {"W", "B", "W", "W", "C", "W", "W", "W", "C", "W", "C", "W", "W", "W", "C", "W", "W", "B", "W"},
        {"W", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "W"},
        {"W", "C", "W", "W", "C", "W", "C", "W", "W", "W", "W", "W", "C", "W", "C", "W", "W", "C", "W"},
        {"W", "C", "C", "C", "C", "W", "C", "C", "C", "W", "C", "C", "C", "W", "C", "C", "C", "C", "W"},
        {"W", "W", "W", "W", "C", "W", "W", "W", "E", "W", "E", "W", "W", "W", "C", "W", "W", "W", "W"},
        {"O", "O", "O", "W", "C", "W", "E", "E", "E", "0", "E", "E", "E", "W", "C", "W", "O", "O", "O"},
        {"W", "W", "W", "W", "C", "W", "E", "W", "W", "L", "W", "W", "E", "W", "C", "W", "W", "W", "W"},
        {"E", "E", "E", "E", "C", "E", "E", "W", "O", "O", "O", "W", "E", "E", "C", "E", "E", "E", "E"},
        {"W", "W", "W", "W", "C", "W", "E", "W", "W", "W", "W", "W", "E", "W", "C", "W", "W", "W", "W"},
        {"O", "O", "O", "W", "C", "W", "E", "E", "E", "E", "E", "E", "E", "W", "C", "W", "O", "O", "O"},
        {"W", "W", "W", "W", "C", "W", "E", "W", "W", "W", "W", "W", "E", "W", "C", "W", "W", "W", "W"},
        {"W", "C", "C", "C", "C", "C", "C", "C", "C", "W", "C", "C", "C", "C", "C", "C", "C", "C", "W"},
        {"W", "C", "W", "W", "C", "W", "W", "W", "C", "W", "C", "W", "W", "W", "C", "W", "W", "C", "W"},
        {"W", "B", "C", "W", "C", "C", "C", "C", "C", "0", "C", "C", "C", "C", "C", "W", "C", "B", "W"},
        {"W", "W", "C", "W", "C", "W", "C", "W", "W", "W", "W", "W", "C", "W", "C", "W", "C", "W", "W"},
        {"W", "C", "C", "C", "C", "W", "C", "C", "C", "W", "C", "C", "C", "W", "C", "C", "C", "C", "W"},
        {"W", "C", "W", "W", "W", "W", "W", "W", "C", "W", "C", "W", "W", "W", "W", "W", "W", "C", "W"},
        {"W", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "W"},
        {"W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"}
    };
    static String[][] map2 = new String[][]{
        {"W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"W", "C", "C", "C", "C", "W", "C", "C", "C", "C", "C", "C", "C", "W", "C", "C", "C", "C", "W"},
        {"W", "B", "W", "W", "C", "W", "C", "W", "W", "W", "W", "W", "C", "W", "C", "W", "W", "B", "W"},
        {"W", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "W"},
        {"W", "W", "C", "W", "C", "W", "W", "W", "C", "W", "C", "W", "W", "W", "C", "W", "C", "W", "W"},
        {"E", "E", "C", "W", "C", "C", "C", "C", "C", "W", "C", "C", "C", "C", "C", "W", "C", "E", "E"},
        {"W", "W", "C", "W", "W", "W", "C", "W", "W", "W", "W", "W", "C", "W", "W", "W", "C", "W", "W"},
        {"O", "W", "C", "C", "C", "C", "E", "E", "E", "0", "E", "E", "E", "C", "C", "C", "C", "W", "O"},
        {"O", "W", "C", "W", "W", "W", "E", "W", "W", "L", "W", "W", "E", "W", "W", "W", "C", "W", "O"},
        {"O", "W", "C", "W", "C", "C", "E", "W", "O", "O", "O", "W", "E", "C", "C", "W", "C", "W", "O"},
        {"W", "W", "C", "W", "C", "W", "E", "W", "W", "W", "W", "W", "E", "W", "C", "W", "C", "W", "W"},
        {"E", "E", "C", "C", "C", "W", "E", "E", "E", "E", "E", "E", "E", "W", "C", "C", "C", "E", "E"},
        {"W", "W", "C", "W", "W", "W", "W", "W", "C", "W", "C", "W", "W", "W", "W", "W", "C", "W", "W"},
        {"O", "W", "C", "C", "C", "C", "C", "C", "C", "W", "C", "C", "C", "C", "C", "C", "C", "W", "O"},
        {"W", "W", "C", "W", "W", "W", "C", "W", "W", "W", "W", "W", "C", "W", "W", "W", "C", "W", "W"},
        {"W", "C", "C", "C", "C", "C", "C", "C", "C", "0", "C", "C", "C", "C", "C", "C", "C", "C", "W"},
        {"W", "C", "W", "W", "C", "W", "W", "W", "C", "W", "C", "W", "W", "W", "C", "W", "W", "C", "W"},
        {"W", "C", "W", "W", "C", "W", "C", "C", "C", "W", "C", "C", "C", "W", "C", "W", "W", "C", "W"},
        {"W", "B", "W", "W", "C", "W", "C", "W", "W", "W", "W", "W", "C", "W", "C", "W", "W", "B", "W"},
        {"W", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "C", "W"},
        {"W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"}
    };
    static String[][] map3 = new String[][]{
        {"W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"},
        {"W", "C", "C", "C", "C", "W", "C", "C", "C", "C", "C", "C", "C", "W", "C", "C", "C", "C", "W"},
        {"W", "B", "W", "W", "C", "W", "C", "W", "W", "W", "W", "W", "C", "W", "C", "W", "W", "B", "W"},
        {"W", "C", "C", "W", "C", "C", "C", "C", "C", "W", "C", "C", "C", "C", "C", "W", "C", "C", "W"},
        {"W", "W", "C", "W", "C", "W", "C", "W", "C", "W", "C", "W", "C", "W", "C", "W", "C", "W", "W"},
        {"E", "E", "C", "C", "C", "W", "C", "W", "C", "W", "C", "W", "C", "W", "C", "C", "C", "E", "E"},
        {"W", "W", "C", "W", "C", "W", "C", "W", "C", "C", "C", "W", "C", "W", "C", "W", "C", "W", "W"},
        {"O", "W", "C", "W", "C", "W", "C", "W", "W", "W", "W", "W", "C", "W", "C", "W", "C", "W", "O"},
        {"W", "W", "C", "W", "C", "C", "E", "E", "E", "0", "E", "E", "E", "C", "C", "W", "C", "W", "W"},
        {"W", "W", "C", "C", "C", "W", "E", "W", "W", "L", "W", "W", "E", "W", "C", "C", "C", "W", "W"},
        {"W", "W", "C", "W", "W", "W", "E", "W", "0", "0", "0", "W", "E", "W", "W", "W", "C", "W", "W"},
        {"W", "C", "C", "C", "C", "W", "E", "W", "W", "W", "W", "W", "E", "W", "C", "C", "C", "C", "W"},
        {"W", "C", "W", "W", "C", "W", "E", "E", "E", "E", "E", "E", "E", "W", "C", "W", "W", "C", "W"}, 
        {"W", "C", "C", "W", "C", "W", "C", "W", "W", "W", "W", "W", "C", "W", "C", "W", "C", "C", "W"},
        {"W", "W", "C", "W", "C", "W", "C", "C", "C", "W", "C", "C", "C", "W", "C", "W", "C", "W", "W"},
        {"E", "E", "C", "C", "C", "C", "C", "W", "C", "0", "C", "W", "C", "C", "C", "C", "C", "E", "E"}, 
        {"W", "W", "C", "W", "C", "W", "W", "W", "C", "W", "C", "W", "W", "W", "C", "W", "C", "W", "W"}, 
        {"W", "C", "C", "W", "C", "C", "C", "C", "C", "W", "C", "C", "C", "C", "C", "W", "C", "C", "W"},
        {"W", "B", "W", "W", "C", "W", "C", "W", "W", "W", "W", "W", "C", "W", "C", "W", "W", "B", "W"},
        {"W", "C", "C", "C", "C", "W", "C", "C", "C", "C", "C", "C", "C", "W", "C", "C", "C", "C", "W"},
        {"W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "W"}
    };

    static String[][] map = new String[21][19];

    static int width = map[0].length, height = map.length, blockSize = 30;

    public static void drawMap(GraphicsContext gc) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int r;
                switch (map[j][i]) {
                    case "W":
                        gc.setFill(Color.BLUE);
                        r = blockSize;
		                gc.fillRect(i * blockSize, j * blockSize, r, r);
                        break;
                    case "C":
                        gc.setFill(Color.YELLOW);
                        r = blockSize / 4;
                        gc.fillOval(i * blockSize + blockSize / 2 - r / 2, j * blockSize + blockSize / 2 - r / 2, r, r);
                        break;
                    case "B":
                        gc.setFill(Color.YELLOW);
                        r = blockSize / 2;
                        gc.fillOval(i * blockSize + blockSize / 2 - r / 2, j * blockSize + blockSize / 2 - r / 2, r, r);
                        break;
                    case "L":
                        gc.setStroke(Color.WHITE);
                        gc.setLineWidth(2);
                        gc.strokeLine(i * blockSize, j * blockSize + blockSize / 2, (i+1) * blockSize, (j) * blockSize + blockSize / 2);
                        break;
                    case "G": // Gilas = Boost
                        r = blockSize;
                        gc.drawImage(new Image((new File("/Users/sina/Desktop/OOP/pacman/target/classes/com/example/boost.png")).toURI().toString()),i * blockSize, j * blockSize, r, r);
                        break;

                    default:
                        break;
                }
            }
        }
    }

    public static boolean isOccupied(int x, int y) {
        if(y >= height || x >= width || y < 0 || x < 0) return false;
        if(GameMap.map[y][x].equals("W") || GameMap.map[y][x].equals("L") || GameMap.map[y][x].equals("O"))
            return true;
        return false;
    }

    public static boolean isMapDone() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if(map[j][i].equals("C") || map[j][i].equals("B")) return false;
            }
        }
        return true;
    }

}
