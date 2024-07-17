package com.example;

import java.util.Random;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Boost {

    private static ArrayList<int[]> locs = new ArrayList<>();

    public static void setRandomBoost() {
        int n = new Random().nextInt(100);
        if(n > 1) return;
        int x, y;
        while(true) {
            x = new Random().nextInt(GameMap.width);
			y = new Random().nextInt(GameMap.height);

            if(GameMap.map[y][x].equals("E"))
                break;
        }
        GameMap.map[y][x] = "G";
        locs.add(new int[] {x, y});
    }

    public static void getBoost(int x, int y) {
        for (int[] is : locs) {
            if(is[0] != x || is[1] != y) continue;
            locs.remove(is);

            int n = new Random().nextInt(3);
            Timer timer; TimerTask timerTask;
            switch(n) {
                case 0:
                    for (Entity i : GameController.entities.values()) {
                        if(i instanceof Player) continue;
                        i.speed = 0;
                    }

                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            for (Entity i : GameController.entities.values()) {
                                if(i instanceof Player) continue;
                                i.speed = 15;
                            }
                        }
                    };
                    timer.schedule(timerTask, 5000);
                    break;

                case 1:
                    GameController.entities.get("pacman").speed = 15;

                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            GameController.entities.get("pacman").speed = 10;
                        }
                    };
                    timer.schedule(timerTask, 7500);
                    break;

                case 2:
                GameController.hideGhosts = true;

                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            GameController.hideGhosts = false;
                        }
                    };
                    timer.schedule(timerTask, 5000);
                    break;

            }

            return;
        }
    }

    public static void checkBoosts() {
        int n;
        if(locs.isEmpty()) return;
        for (int[] is : locs) {
            n = new Random().nextInt(100);
            if(n == 0) {
                GameMap.map[is[1]][is[0]] = "E";
                locs.remove(is);
                return;
            }
        }
    }

}
