package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        if (level > 60) level = level % 60;
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            int readedLevel = 0;
            int x;
            int y = Model.FIELD_CELL_SIZE / 2;
            boolean isLevelMap = false;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Maze:")) {
                    readedLevel = Integer.parseInt(line.split(" ")[1]);
                    continue;
                }
                if (readedLevel == level) {
                    if (line.length() == 0) {
                        boolean isEnd = isLevelMap;
                        isLevelMap = !isLevelMap;
                        if (isEnd) break;
                        else continue;
                    }
                    if (isLevelMap) {
                        x = Model.FIELD_CELL_SIZE / 2;
                        char[] objects = line.toCharArray();
                        for (char c : objects) {
                            switch (c) {
                                case 'X':
                                    walls.add(new Wall(x, y));
                                    break;
                                case '*':
                                    boxes.add(new Box(x, y));
                                    break;
                                case '.':
                                    homes.add(new Home(x, y));
                                    break;
                                case '&':
                                    boxes.add(new Box(x, y));
                                    homes.add(new Home(x, y));
                                    break;
                                case '@':
                                    player = new Player(x, y);
                                    break;
                            }
                            x += Model.FIELD_CELL_SIZE;
                        }
                        y += Model.FIELD_CELL_SIZE;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
