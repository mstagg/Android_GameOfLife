package com.example.usiandroid.gameoflife.Logic;

import android.util.Log;

/**
 * Created by matthew on 12/13/15.
 */

// Class that manages board and cell state
public class BoardState {

    protected int sizeX, sizeY, totalBlocks;
    protected int totalPainted = 0, totalPlaced = 0, maxPlaced = 30;
    protected Cell[][] blocks;

    // Constructor
    public BoardState(int width, int height){
        sizeX = width;
        sizeY = height;
        totalBlocks = sizeX * sizeY;
        blocks = new Cell[sizeX][sizeY];
        // Init all cells in a dead state
        for (int x = 0; x < blocks.length; x++){
            for (int y = 0; y < blocks[x].length; y++){
                blocks[x][y] = new Cell(false, x, y);
            }
        }
    }

    // Accessor to increment totalPlaced
    public void incTotalPlaced(){
        totalPlaced++;
    }

    // Accessor to decrement totalPlaced
    public void decTotalPlaced(){
        totalPlaced--;
    }

    // Finds and returns the cell located at (x, y)
    // x and y are NOT screen coordinates, they are the index of the cell in blocks[][]
    public Cell getCellAtPos(int x, int y){
        return blocks[x][y];
    }

    // Updates the state of each cell on the grid
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
                    if (numNeighbors == 3) {
                        current.prepareToLive();
                    } else if (numNeighbors < 2 || numNeighbors > 3) {
                        current.prepareToDie();
                    }
                }
            }
        }
        for (int x = 0; x < blocks.length; x++){
            for (int y = 0; y < blocks[x].length; y++) {
                Cell current = blocks[x][y];
                if(current.isReadyToLive()){
                    current.live();
                } else if(current.isReadyToDie()){
                    current.die();
                }
            }
        }
    }

    // Clears all cells and resets variables to default states
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

    // Returns the remaining amount of blocks the user can place
    // only used by challenge modes
    public int getRemainingBlocks(){
        return maxPlaced - totalPlaced;
    }

    // Returns the percentage of blocks that have been visited
    // only used by challenge modes
    public double getPercentagePainted(){
        return ((double)totalPainted / (double)totalBlocks) * 100.0;
    }

    // Returns the number of cells adjacent and diagonal to Cell c that are alive
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

