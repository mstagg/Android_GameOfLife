package com.example.usiandroid.gameoflife;

import android.util.Log;

/**
 * Created by matthew on 12/13/15.
 */



public class BoardState {

    private int sizeX;
    private int sizeY;
    private Cell[][] blocks;

    public BoardState(int width, int height){
        sizeX = width;
        sizeY = height;
        blocks = new Cell[sizeX][sizeY];
        for (int x = 0; x < blocks.length; x++){
            for (int y = 0; y < blocks[x].length; y++){
                blocks[x][y] = new Cell(false, x, y);
            }
        }
    }

    public Cell getCellAtPos(int x, int y){
        return blocks[x][y];
    }

    public void updateCells(){
        blocks[3][3].setAlive(true);
        /*for (int x = 0; x < blocks.length; x++){
            for (int y = 0; y < blocks[x].length; y++){
                Cell current = blocks[x][y];
                int numNeighbors = getNumberOfNeighbors(current);
                Log.v("NEIGHBORS", Integer.toString(numNeighbors));
                if(numNeighbors < 2 || numNeighbors > 3){
                    current.setAlive(false);
                } else if(numNeighbors == 3){
                    current.setAlive(true);
                }
            }
        }*/
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
        if(checkX < sizeY){
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

