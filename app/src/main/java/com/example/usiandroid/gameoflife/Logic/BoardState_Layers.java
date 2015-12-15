package com.example.usiandroid.gameoflife.Logic;

/**
 * Created by matthew on 12/14/15.
 */
public class BoardState_Layers extends BoardState{
    // Constructor
    public BoardState_Layers(int width, int height) {
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
        blocks[0][4].setWall(true);
        blocks[1][4].setWall(true);
        blocks[2][4].setWall(true);
        blocks[3][4].setWall(true);
        blocks[4][4].setWall(true);
        blocks[5][4].setWall(true);
        blocks[6][4].setWall(true);
        blocks[7][4].setWall(true);
        blocks[8][4].setWall(true);
        blocks[9][4].setWall(true);

        // Row 2
        blocks[14][9].setWall(true);
        blocks[13][9].setWall(true);
        blocks[12][9].setWall(true);
        blocks[11][9].setWall(true);
        blocks[10][9].setWall(true);
        blocks[9][9].setWall(true);
        blocks[8][9].setWall(true);
        blocks[7][9].setWall(true);
        blocks[6][9].setWall(true);
        blocks[5][9].setWall(true);

        // Row 3
        blocks[0][14].setWall(true);
        blocks[1][14].setWall(true);
        blocks[2][14].setWall(true);
        blocks[3][14].setWall(true);
        blocks[4][14].setWall(true);
        blocks[5][14].setWall(true);
        blocks[6][14].setWall(true);
        blocks[7][14].setWall(true);
        blocks[8][14].setWall(true);
        blocks[9][14].setWall(true);

        // Row 4
        blocks[14][19].setWall(true);
        blocks[13][19].setWall(true);
        blocks[12][19].setWall(true);
        blocks[11][19].setWall(true);
        blocks[10][19].setWall(true);
        blocks[9][19].setWall(true);
        blocks[8][19].setWall(true);
        blocks[7][19].setWall(true);
        blocks[6][19].setWall(true);
        blocks[5][19].setWall(true);

        // Row 5
        blocks[0][24].setWall(true);
        blocks[1][24].setWall(true);
        blocks[2][24].setWall(true);
        blocks[3][24].setWall(true);
        blocks[4][24].setWall(true);
        blocks[5][24].setWall(true);
        blocks[6][24].setWall(true);
        blocks[7][24].setWall(true);
        blocks[8][24].setWall(true);
        blocks[9][24].setWall(true);



        // Remove wall count from total block count
        totalBlocks -= 50;
    }
}
