package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.io.File;
import java.nio.file.Paths;

public class Model {
    private EventListener eventListener;
    public static final int FIELD_CELL_SIZE = 20;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(new File(getClass().getResource("../res/levels.txt").getFile()).toPath());

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) return;
        if (checkBoxCollisionAndMoveIfAvaliable(direction)) return;
        moveBoxOrPlayer(direction, player);
        checkCompletion();
    }

    private void moveBoxOrPlayer(Direction direction, Movable object) {
        switch (direction) {
            case LEFT:
                object.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                object.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                object.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                object.move(0, FIELD_CELL_SIZE);
        }
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls())
            if (gameObject.isCollision(wall, direction))
                return true;
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = gameObjects.getPlayer();
        GameObject block = null;
        for (GameObject gameObject : gameObjects.getAll())
            if (!(gameObject instanceof Player) && !(gameObject instanceof Home) && player.isCollision(gameObject, direction))
                block = gameObject;
        if ((block == null)) return false;
        if (block instanceof Box) {
            Box blockBox = (Box) block;
            if (checkWallCollision(blockBox, direction))
                return true;
            for (Box box : gameObjects.getBoxes())
                if (blockBox.isCollision(box, direction))
                    return true;
            moveBoxOrPlayer(direction, blockBox);
        }
        return false;
    }

    public void checkCompletion() {
        boolean isLevelCompleted = true;
        for (Home home : gameObjects.getHomes()) {
            boolean flag = false;
            for (Box box : gameObjects.getBoxes())
                if (box.getX() == home.getX() && box.getY() == home.getY())
                    flag = true;
            if (!flag) isLevelCompleted = false;
        }
        if (isLevelCompleted)
            eventListener.levelCompleted(currentLevel);
    }
}
