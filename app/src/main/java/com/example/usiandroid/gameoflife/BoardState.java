package com.example.usiandroid.gameoflife;

import android.util.Log;

/**
 * Created by matthew on 12/13/15.
 */



public class BoardState {

    protected int sizeX;
    protected int sizeY;
    protected Cell[][] blocks;
    protected int totalBlocks, totalPainted = 0, maxPlaced = 30;
    protected int totalPlaced = 0;

    public BoardState(int width, int height){
        sizeX = width;
        sizeY = height;
        totalBlocks = sizeX * sizeY;
        blocks = new Cell[sizeX][sizeY];
        for (int x = 0; x < blocks.length; x++){
            for (int y = 0; y < blocks[x].length; y++){
                blocks[x][y] = new Cell(false, x, y);
            }
        }
    }

    public void incTotalPlaced(){
        totalPlaced++;
    }

    public void decTotalPlaced(){
        totalPlaced--;
    }

    public Cell getCellAtPos(int x, int y){
        return blocks[x][y];
    }

    public void updateCells(){
        for (int x = 0; x < blocks.length; x++){
            for (int y = 0; y < blocks[x].length; y++){
                Cell current = blocks[x][y];
                if(!current.isWall()) {
                    if(current.isAlive()){
                        if(!current.isVisited()){
                            totalPainted++;
                        }
                        current.setVisited(true);
                    }
                    int numNeighbors = getNumberOfNeighbors(current);
                    if (numNeighbors < 2 || numNeighbors > 3) {
                        current.setAlive(false);
                    } else if (numNeighbors == 3) {
                        current.setAlive(true);
                    }
                }
            }
        }
    }

    public void clearCells(){
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                Cell current = blocks[x][y];
                current.setAlive(false);
                current.setVisited(false);
                totalPainted = 0;
                totalPlaced = 0;
            }
        }
    }

    public int getRemainingBlocks(){
        return maxPlaced - totalPlaced;
    }

    public double getPercentagePainted(){
        return ((double)totalPainted / (double)totalBlocks) * 100.0;
    }

    private int getNumberOfNeighbors(Cell c){
        int x = c.getxPos();
        int y = c.getyPos();
        int checkX, checkY;
        int sumofAliveNeighbors = 0;

        // Starting at upper left and moving clockwise around the cell, check...
        // Upper left
        checkX = x - 1;
        checkY = y - 1;
        if(checkX >= 0 && checkY >= 0){
            sumofAliveNeighbors += (blocks[checkX][checkY].isAlive()) ? 1 : 0;
        }
        // Upper middle
        checkX = x;
        checkY = y - 1;
        if(checkY >= 0){
            sumofAliveNeighbors += (blocks[checkX][checkY].isAlive()) ? 1 : 0;
        }
        // Upper right
        checkX = x + 1;
        checkY = y - 1;
        if(checkX < sizeX && checkY >= 0){
            sumofAliveNeighbors += (blocks[checkX][checkY].isAlive()) ? 1 : 0;
        }
        // Right middle
        checkX = x + 1;
        checkY = y;
        if(checkX < sizeX){
            sumofAliveNeighbors += (blocks[checkX][checkY].isAlive()) ? 1 : 0;
        }
        // Right lower
        checkX = x + 1;
        checkY = y + 1;
        if(checkX < sizeX && checkY < sizeY){
            sumofAliveNeighbors += (blocks[checkX][checkY].isAlive()) ? 1 : 0;
        }
        // Lower middle
        checkX = x;
        checkY = y + 1;
        if(checkY < sizeY){
            sumofAliveNeighbors += (blocks[checkX][checkY].isAlive()) ? 1 : 0;
        }
        // Lower left
        checkX = x - 1;
        checkY = y + 1;
        if(checkX >= 0 && checkY < sizeY){
            sumofAliveNeighbors += (blocks[checkX][checkY].isAlive()) ? 1 : 0;
        }
        // Left middle
        checkX = x - 1;
        checkY = y;
        if(checkX >= 0){
            sumofAliveNeighbors += (blocks[checkX][checkY].isAlive()) ? 1 : 0;
        }
        return sumofAliveNeighbors;
    }
}

