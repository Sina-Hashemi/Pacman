package com.example;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {

    int x, y, speed, r;
    Dir direction, nextDir;
    Image imgLeft, imgDown, imgRight, imgUp;
    ImageView imageViewLeft, imageViewDown, imageViewRight, imageViewUp;

	public enum Dir {
		UP,
        DOWN,
        RIGHT,
        LEFT,
        NONE
	}

    public Entity() {
    }

    public Entity(int x, int y, int speed, int r, Dir direction, Dir nextDir, String color) {
        this.x = x * GameMap.blockSize;
        this.y = y * GameMap.blockSize;
        this.speed = speed;
        this.r = r;
        this.direction = direction;
        this.nextDir = nextDir;

        this.imgRight = new Image((new File("/Users/sina/Desktop/OOP/pacman/target/classes/com/example/" + color + "Right.gif")).toURI().toString());
        this.imageViewRight = new ImageView(imgRight);
        this.imgUp = new Image((new File("/Users/sina/Desktop/OOP/pacman/target/classes/com/example/" + color + "Up.gif")).toURI().toString());
        this.imageViewUp = new ImageView(imgUp);
        this.imgLeft = new Image((new File("/Users/sina/Desktop/OOP/pacman/target/classes/com/example/" + color + "Left.gif")).toURI().toString());
        this.imageViewLeft = new ImageView(imgLeft);
        this.imgDown = new Image((new File("/Users/sina/Desktop/OOP/pacman/target/classes/com/example/" + color + "Down.gif")).toURI().toString());
        this.imageViewDown = new ImageView(imgDown);
    }

    public abstract void move();

    public boolean isDirOK() {
		switch (direction) {
            case UP:
                if((y) % GameMap.blockSize != 0) return true;
                return !GameMap.isOccupied(x / GameMap.blockSize, (y) / GameMap.blockSize - 1);
            case DOWN:
                if((y) % GameMap.blockSize != 0) return true;
                return !GameMap.isOccupied(x / GameMap.blockSize, (y) / GameMap.blockSize + 1);
            case LEFT:
                if((x) % GameMap.blockSize != 0) return true;
                return !GameMap.isOccupied((x) / GameMap.blockSize - 1, y / GameMap.blockSize);
            case RIGHT:
                if((x) % GameMap.blockSize != 0) return true;
                return !GameMap.isOccupied((x) / GameMap.blockSize + 1, y / GameMap.blockSize);

            case NONE:
                return false;

            default:
                return true;
        }
    }

    public boolean isNextDirOK() {
		switch (nextDir) {
            case UP:
                if(y % GameMap.blockSize != 0 || x % GameMap.blockSize != 0) return false;
                return !GameMap.isOccupied(x / GameMap.blockSize, y / GameMap.blockSize - 1);
            case DOWN:
                if(y % GameMap.blockSize != 0 || x % GameMap.blockSize != 0 ) return false;
                return !GameMap.isOccupied(x / GameMap.blockSize, y / GameMap.blockSize + 1);
            case LEFT:
                if(x % GameMap.blockSize != 0 || y % GameMap.blockSize != 0 ) return false;
                return !GameMap.isOccupied(x / GameMap.blockSize - 1, y / GameMap.blockSize);
            case RIGHT:
                if(x % GameMap.blockSize != 0 || y % GameMap.blockSize != 0 ) return false;
                return !GameMap.isOccupied(x / GameMap.blockSize + 1, y / GameMap.blockSize);

            case NONE:
                return false;

            default:
                return true;
        }
    }

    public void draw(GraphicsContext gc) {
        if(!(this instanceof Player))
            if(GameController.hideGhosts) return;

        switch (direction) {
            case UP:
                gc.drawImage(imageViewUp.getImage(), x, y, r, r);
                break;
            case RIGHT:
                gc.drawImage(imageViewRight.getImage(), x, y, r, r);
                break;
            case LEFT:
                gc.drawImage(imageViewLeft.getImage(), x, y, r, r);
                break;
            case DOWN:
                gc.drawImage(imageViewDown.getImage(), x, y, r, r);
                break;

            default:
                gc.drawImage(imageViewRight.getImage(), x, y, r, r);
                break;
        }
    }
}
