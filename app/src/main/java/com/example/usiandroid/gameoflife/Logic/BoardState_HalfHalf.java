package com.example.usiandroid.gameoflife.Logic;

/**
 * Created by matthew on 12/14/15.
 */
public class BoardState_HalfHalf extends BoardState{
    // Constructor
    public BoardState_HalfHalf(int width, int height) {
        super(width, height);

        sizeX = width;
        sizeY = height;
        blocks = new Cell[sizeX][sizeY];
        for (int x = 0; x < blocks.length; x++){
            for (int y = 0; y < blocks[x].length; y++){
                blocks[x][y] = new Cell(false, x, y);
            }
        }
        setWallCells();
    }

    // Sets cells that will become walls in a cross shape
    private void setWallCells(){
        // Row 1
        blocks[0][14].setWall(true);
        blocks[0][15].setWall(true);
        blocks[1][14].setWall(true);
        blocks[1][15].setWall(true);
        blocks[2][14].setWall(true);
        blocks[2][15].setWall(true);
        blocks[3][14].setWall(true);
        blocks[3][15].setWall(true);
        blocks[4][14].setWall(true);
        blocks[4][15].setWall(true);
        blocks[5][14].setWall(true);
        blocks[5][15].setWall(true);
        blocks[6][14].setWall(true);
        blocks[6][15].setWall(true);
        blocks[7][14].setWall(true);
        blocks[7][15].setWall(true);
        blocks[8][14].setWall(true);
        blocks[8][15].setWall(true);
        blocks[9][14].setWall(true);
        blocks[9][15].setWall(true);
        blocks[10][14].setWall(true);
        blocks[10][15].setWall(true);
        blocks[11][14].setWall(true);
        blocks[11][15].setWall(true);
        blocks[12][14].setWall(true);
        blocks[12][15].setWall(true);
        blocks[13][14].setWall(true);
        blocks[13][15].setWall(true);
        blocks[14][14].setWall(true);
        blocks[14][15].setWall(true);

        // Remove wall count from total block count
        totalBlocks -= 30;
    }
}
