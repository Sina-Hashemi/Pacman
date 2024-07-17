package com.example;

import java.io.IOException;
import java.util.HashMap;
import java.io.File;

import com.example.Entity.Dir;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameController {
	static boolean gameOver, youWon, isMusicPlaying, hideGhosts;
    static int health, score;
    static HashMap<String, Entity> entities = new HashMap<>();
    static MediaPlayer player;

    public static void makeScene() throws IOException {
        gameOver = false; youWon = false;isMusicPlaying = false;hideGhosts = false;
        health = 3; score = 0;

        Pane root = new Pane();
        Canvas c = new Canvas(GameMap.width * GameMap.blockSize, GameMap.height * GameMap.blockSize);
        GraphicsContext gc = c.getGraphicsContext2D();
        root.getChildren().add(c);

        new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    tick(gc);
                    return;
                }

                if (now - lastTick > 100000000) {
                    lastTick = now;
                    tick(gc);
                }

                if(health == -1) gameOver = true;
                if(GameMap.isMapDone()) youWon = true;

                if(gameOver) {
                    gameOver(gc, root);
                    stop();
                }
                if(youWon) {
                    youWon(gc, root);
                    stop();
                }
            }

        }.start();

        entities.put("pacman", new Player());
        GameController.entities.put("redGhost", new Ghost("Red"));
        GameController.entities.put("cyanGhost", new Ghost("Cyan"));
        GameController.entities.put("orangeGhost", new Ghost("Orange"));
        GameController.entities.put("pinkGhost", new Ghost("Pink"));

        // control
        Main.scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.W) {
                entities.get("pacman").nextDir = Dir.UP;
            }
            if (key.getCode() == KeyCode.A) {
                entities.get("pacman").nextDir = Dir.LEFT; 
            }
            if (key.getCode() == KeyCode.S) {
                entities.get("pacman").nextDir = Dir.DOWN;
            }
            if (key.getCode() == KeyCode.D) {
                entities.get("pacman").nextDir = Dir.RIGHT;
            }

        });

        Main.scene.setRoot(root);

        playSound("Start.mp3");
    }

	public static void tick(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, GameMap.width * GameMap.blockSize, GameMap.height * GameMap.blockSize);

        GameMap.drawMap(gc);

        for (Entity entity : entities.values()) {
            entity.draw(gc);
        }

		gc.setFill(Color.WHITE);
		gc.setFont(new Font("", 30));
		gc.fillText("Score: " + score, 10, 24);
		gc.fillText("Health: " + health, 310, 24);

        if(entities.get("pacman").direction == Dir.NONE) {
            if(entities.get("pacman").nextDir == Dir.NONE)
                return;
        }

        for (Entity entity : entities.values()) {
            entity.move();
        }

        ((Player)entities.get("pacman")).checkStatus();
        Boost.setRandomBoost();
        Boost.checkBoosts();

	}

    private static void youWon(GraphicsContext gc, Pane root) {
        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, GameMap.width * GameMap.blockSize, GameMap.height * GameMap.blockSize);

        drawButton(root);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + score + " + " + health + " * 100", 10, 24);
        gc.fillText("Final Score: " + (score + 100 * health), 60, 120);

        gc.setFill(Color.GREEN);
        gc.setFont(new Font("", 50));
        gc.fillText("YOU WON", 100, 250);

        playSound("level-complete.mp3");
    }

    private static void gameOver(GraphicsContext gc, Pane root) {
        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, GameMap.width * GameMap.blockSize, GameMap.height * GameMap.blockSize);

        drawButton(root);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score: " + score, 10, 24);

        gc.setFill(Color.RED);
        gc.setFont(new Font("", 50));
        gc.fillText("GAME OVER", 100, 250);

        playSound("game-over.mp3");
    }

    public static void drawButton(Pane root) {

        Button button = new Button("Main Menu");
        button.setLayoutX(200);
        button.setLayoutY(400);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                MainMenuController.goBack();
            }
        });

        root.getChildren().add(button);

    }

    public static void playSound(String name) {

        Media music = new Media((new File("/Users/sina/Desktop/OOP/pacman/target/classes/com/example/" + name)).toURI().toString());
        player = new MediaPlayer(music);
        player.play();

        // if(!isMusicPlaying) {
        //     Media music = new Media((new File("/Users/sina/Desktop/OOP/pacman/target/classes/com/example/" + name)).toURI().toString());
        //     player = new MediaPlayer(music);
        //     player.play();
        //     isMusicPlaying = true;

        //     player.setOnEndOfMedia(() -> {
        //         isMusicPlaying = false;
        //     });
        // }
    }

}
