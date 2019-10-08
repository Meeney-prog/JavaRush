package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;
    protected int score;
    protected int maxTile;

    public Model() {
        resetGameTiles();
    }

    public boolean canMove() {
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                if (gameTiles[i][j].value == 0)
                    return true;
                if (i != 0 && gameTiles[i - 1][j].value == gameTiles[i][j].value)
                    return true;
                if (j != 0 && gameTiles[i][j - 1].value == gameTiles[i][j].value)
                    return true;
            }
        }
        return false;
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        MoveEfficiency result;
        if (!hasBoardChanged())
            result = new MoveEfficiency(-1, 0, move);
        else
            result = new MoveEfficiency(getEmptyTiles().size(), score, move);
        rollback();
        return result;
    }

    private boolean hasBoardChanged() {
        return !(getOverAllValue(gameTiles) == getOverAllValue(previousStates.peek()));
    }

    private int getOverAllValue(Tile[][] game) {
        int overAllValue = 0;
        for (Tile[] tiles : game) {
            for (Tile tile : tiles) {
                overAllValue += tile.value;
            }
        }
        return overAllValue;
    }

    void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
        queue.add(getMoveEfficiency(this::left));
        queue.add(getMoveEfficiency(this::right));
        queue.add(getMoveEfficiency(this::up));
        queue.add(getMoveEfficiency(this::down));
        queue.peek().getMove().move();
    }

    void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    void right() {
        saveState(gameTiles);
        spin();
        spin();
        left();
        spin();
        spin();
    }

    void up() {
        saveState(gameTiles);
        spin();
        spin();
        spin();
        left();
        spin();
    }

    void down() {
        saveState(gameTiles);
        spin();
        left();
        spin();
        spin();
        spin();

    }

    void left() {
        if (isSaveNeeded) saveState(gameTiles);
        boolean flag = false;
        for (Tile[] gameTile : gameTiles) {
            if (compressTiles(gameTile) | mergeTiles(gameTile)) {
                flag = true;
            }
        }
        if (flag) {
            addTile();
            isSaveNeeded = true;
        }
    }

    private void saveState(Tile[][] game) {
        previousStates.push(getCopyGameTile(game));
        previousScores.push(score);
        isSaveNeeded = false;
    }

    private Tile[][] getCopyGameTile(Tile[][] game) {
        Tile[][] copy = new Tile[game.length][game.length];
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game.length; j++) {
                copy[i][j] = new Tile(game[i][j].value);
            }
        }
        return copy;
    }

    public void rollback() {
        if (!(previousScores.empty() || previousStates.empty())) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    private void spin() {
        int size = gameTiles.length;
        Tile[][] spined = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                spined[i][j] = gameTiles[size - j - 1][i];
            }
        }
        gameTiles = spined;
    }

    private boolean compressTiles(Tile[] tiles) {
        Tile tmp;
        boolean flag = true;
        boolean result = false;
        while (flag) {
            flag = false;
            for (int i = 1; i < tiles.length; i++) {
                if (tiles[i - 1].value == 0 && tiles[i].value != 0) {
                    tmp = tiles[i - 1];
                    tiles[i - 1] = tiles[i];
                    tiles[i] = tmp;
                    flag = true;
                    result = true;
                }
            }
        }
        return result;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean result = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value && tiles[i].value != 0) {
                tiles[i].value = tiles[i].value << 1;
                score += tiles[i].value;
                maxTile = tiles[i].value > maxTile ? tiles[i].value : maxTile;
                tiles[i + 1].value = 0;
                compressTiles(tiles);
                result = true;
            }
        }
        return result;
    }

    void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (!emptyTiles.isEmpty()) {
            Tile newTile = emptyTiles.get((int) (emptyTiles.size() * Math.random()));
            newTile.value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    tiles.add(gameTiles[i][j]);
                }
            }
        }
        return tiles;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }
}
