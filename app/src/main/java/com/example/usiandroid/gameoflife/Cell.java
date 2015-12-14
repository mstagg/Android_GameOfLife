package com.example.usiandroid.gameoflife;

/**
 * Created by matthew on 12/13/15.
 */

public class Cell{

    private boolean alive;
    private boolean visited;
    private boolean isWall;
    private int xPos, yPos; // Position of cell in grid, NOT SIZE OF CELL OR POSITION ON CANVAS

    public Cell(boolean wall, int x, int y){
        xPos = x;
        yPos = y;
        alive = false;
        visited = false;
        if(wall){
            isWall = true;
        } else {
            isWall = false;
        }
    }

    public int getxPos(){
        return this.xPos;
    }

    public int getyPos(){
        return this.yPos;
    }

    public void setAlive(boolean b){
        if(b){
            this.alive = true;
        } else{
            this.alive = false;
        }
    }

    public boolean isAlive(){
        if(this.alive){
            return true;
        } else{
            return false;
        }
    }

    public void setWall(boolean b){
        if(b){
            this.isWall = true;
        } else{
            this.isWall = false;
        }
    }

    public boolean isWall(){
        if(this.isWall){
            return true;
        } else{
            return false;
        }
    }

    public void setVisited(boolean b){
        if(b){
            this.visited = true;
        } else{
            this.visited = false;
        }
    }

    public boolean isVisited(){
        if(this.visited){
            return true;
        } else{
            return false;
        }
    }
}