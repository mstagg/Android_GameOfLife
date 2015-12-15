package com.example.usiandroid.gameoflife.Logic;

/**
 * Created by matthew on 12/14/15.
 */
public class BoardState_Chevron extends BoardState{
    // Constructor
    public BoardState_Chevron(int width, int height) {
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
        blocks[2][19].setWall(true);
        blocks[3][19].setWall(true);
        blocks[2][18].setWall(true);
        blocks[3][18].setWall(true);

        blocks[3][17].setWall(true);
        blocks[4][17].setWall(true);
        blocks[3][16].setWall(true);
        blocks[4][16].setWall(true);

        blocks[4][15].setWall(true);
        blocks[5][15].setWall(true);
        blocks[4][14].setWall(true);
        blocks[5][14].setWall(true);

        blocks[5][13].setWall(true);
        blocks[6][13].setWall(true);
        blocks[5][12].setWall(true);
        blocks[6][12].setWall(true);

        blocks[6][11].setWall(true);
        blocks[7][11].setWall(true);
        blocks[6][10].setWall(true);
        blocks[7][10].setWall(true);

        blocks[7][9].setWall(true);

        blocks[7][11].setWall(true);
        blocks[8][11].setWall(true);
        blocks[7][10].setWall(true);
        blocks[8][10].setWall(true);

        blocks[8][12].setWall(true);
        blocks[9][12].setWall(true);
        blocks[8][13].setWall(true);
        blocks[9][13].setWall(true);

        blocks[9][14].setWall(true);
        blocks[10][14].setWall(true);
        blocks[9][15].setWall(true);
        blocks[10][15].setWall(true);

        blocks[10][16].setWall(true);
        blocks[11][16].setWall(true);
        blocks[10][17].setWall(true);
        blocks[11][17].setWall(true);

        blocks[11][18].setWall(true);
        blocks[12][18].setWall(true);
        blocks[11][19].setWall(true);
        blocks[12][19].setWall(true);

        // Remove wall count from total block count
        totalBlocks -= 41;
    }
}
