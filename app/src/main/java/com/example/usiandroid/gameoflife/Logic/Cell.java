package com.example.usiandroid.gameoflife.Logic;

/**
 * Created by matthew on 12/13/15.
 */


// Class to store and manages cells on the board
public class Cell{

    private boolean alive, visited, wall, readyToLive, readyToDie;
    private int xPos, yPos; // Position of cell in grid, NOT SIZE OF CELL OR POSITION ON CANVAS

    // Constructor
    public Cell(boolean wall, int x, int y){
        xPos = x;
        yPos = y;
        alive = false;
        visited = false;
        readyToDie = false;
        readyToLive = false;

        if(wall){
            this.wall = true;
        } else {
            this.wall = false;
        }
    }

    // Accessor for xPos
    public int getxPos(){
        return this.xPos;
    }

    // Accesor for yPos
    public int getyPos(){
        return this.yPos;
    }

    // Setter function for alive state
    public void setAlive(boolean b){
        if(b){
            this.alive = true;
        } else{
            this.alive = false;
        }
    }

    // Getter function for alive state
    public boolean isAlive(){
        if(this.alive){
            return true;
        } else{
            return false;
        }
    }

    // Setter function for wall state
    public void setWall(boolean b){
        if(b){
            this.wall = true;
        } else{
            this.wall = false;
        }
    }

    // Getter function for wall state
    public boolean isWall(){
        if(this.wall){
            return true;
        } else{
            return false;
        }
    }

    // Setter function for visited state
    public void setVisited(boolean b){
        if(b){
            this.visited = true;
        } else{
            this.visited = false;
        }
    }

    // Getter function for visited state
    public boolean isVisited(){
        if(this.visited){
            return true;
        } else{
            return false;
        }
    }

    // Marks cell for birth
    public void prepareToLive(){
        readyToLive = true;
    }

    // Marks cell for death
    public void prepareToDie(){
        readyToDie = true;
    }

    // Getter function for isReadyToLive
    public boolean isReadyToLive(){
        if(this.readyToLive){
            return true;
        } else{
            return false;
        }
    }

    // Getter function for isReadyToDie
    public boolean isReadyToDie(){
        if(this.readyToDie){
            return true;
        } else{
            return false;
        }
    }

    // Births cell and resets live/die status
    public void live(){
        setAlive(true);
        readyToLive = false;
        readyToDie = false;
    }

    // Births cell and resets live/die status
    public void die(){
        setAlive(false);
        readyToLive = false;
        readyToDie = false;
    }
}