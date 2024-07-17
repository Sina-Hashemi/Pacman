package com.example;

public class Player extends Entity {

    Player() {
        super(9, 15, 10, GameMap.blockSize, Dir.NONE, Dir.NONE, "pacman");

    }

    public void checkStatus() {
        if(x % GameMap.blockSize != 0 || y % GameMap.blockSize != 0) {}
        else {
            if(GameMap.map[y / GameMap.blockSize][x / GameMap.blockSize].equals("C")) {
                GameController.score += 10;
                GameMap.map[y / GameMap.blockSize][x / GameMap.blockSize] = "E";

                GameController.playSound("Pill.mp3");

            }
            if(GameMap.map[y / GameMap.blockSize][x / GameMap.blockSize].equals("B")) {
                GameController.score += 50;
                GameMap.map[y / GameMap.blockSize][x / GameMap.blockSize] = "E";

                GameController.playSound("Pill.mp3");
            }
            if(GameMap.map[y / GameMap.blockSize][x / GameMap.blockSize].equals("G")) {
                GameController.score += 25;
                GameMap.map[y / GameMap.blockSize][x / GameMap.blockSize] = "E";
                Boost.getBoost(x / GameMap.blockSize, y / GameMap.blockSize);

                GameController.playSound("Fruit.mp3");
            }
        }

        if(GameController.hideGhosts) return;
        for (Entity entity : GameController.entities.values()) {
            if(entity == this) continue;
            if(Math.abs(entity.x - this.x) < GameMap.blockSize / 3 && Math.abs(entity.y - this.y) < GameMap.blockSize / 3) {
                GameController.health--;
                GameController.entities.clear();

                GameController.entities.put("pacman", new Player());
                GameController.entities.put("redGhost", new Ghost("Red"));
                GameController.entities.put("cyanGhost", new Ghost("Cyan"));
                GameController.entities.put("orangeGhost", new Ghost("Orange"));
                GameController.entities.put("pinkGhost", new Ghost("Pink"));

                GameController.playSound("Died.mp3");

                return;
            }
        }
    }

    @Override
    public void move() {
        if(isNextDirOK()) {
            direction = nextDir;
            nextDir = Dir.NONE;
        }
		switch (direction) {
            case UP:
                if(isDirOK())
                    y -= speed;
                break;
            case DOWN:
                if(isDirOK())
                    y += speed;
                break;
            case LEFT:
                if(isDirOK())
                    x -= speed;
                break;
            case RIGHT:
                if(isDirOK())
                    x += speed;
                break;

            default:
                break;
        }

        if(x >= GameMap.blockSize * (GameMap.width)) x = 0;
        else if(x <= GameMap.blockSize * -1) x = (GameMap.width - 1) * GameMap.blockSize;
    }

}
