package com.example;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.EnumSet;

public class Ghost extends Entity {

    Ghost(String color) {
        super(0, 0, 15, GameMap.blockSize, Dir.NONE, Dir.NONE, color);
        setXY();
        setRandomNextDir();
        this.direction = this.nextDir;
        this.nextDir = Dir.NONE;

    }

    private void setXY() {
        while(true) {
            x = new Random().nextInt(GameMap.width);
			y = new Random().nextInt(GameMap.height);

            if(!GameMap.isOccupied(x, y))
                break;

        }

        x = x * GameMap.blockSize;
        y = y * GameMap.blockSize;
    }

    public void setRandomNextDir() {
        List<Dir> dirs = new ArrayList<>(EnumSet.allOf(Dir.class));

        dirs.remove(Dir.NONE);
        nextDir = Dir.DOWN;
        if(!isNextDirOK()) dirs.remove(nextDir);
        nextDir = Dir.UP;
        if(!isNextDirOK()) dirs.remove(nextDir);
        nextDir = Dir.LEFT;
        if(!isNextDirOK()) dirs.remove(nextDir);
        nextDir = Dir.RIGHT;
        if(!isNextDirOK()) dirs.remove(nextDir);

        if(dirs.size() <= 0) return;
        int n = new Random().nextInt(dirs.size());
        nextDir = dirs.get(n);
    }

    @Override
    public void move() {
        setRandomNextDir();
        if(isNextDirOK()) {
            if(nextDir != direction) {
                if(!(nextDir == Dir.DOWN && direction == Dir.UP) && !(nextDir == Dir.UP && direction == Dir.DOWN)) {
                    if(!(nextDir == Dir.LEFT && direction == Dir.RIGHT) && !(nextDir == Dir.RIGHT && direction == Dir.LEFT)) {
                        direction = nextDir;
                        nextDir = Dir.NONE;
                    }
                }
            }
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
