package com.example.usiandroid.gameoflife.Logic;

/**
 * Created by matthew on 12/14/15.
 */
public class BoardState_Yandroid extends BoardState {
    // Constructor
    public BoardState_Yandroid(int width, int height) {
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
        // Right Angle
        blocks[11][4].setWall(true);
        blocks[12][4].setWall(true);
        blocks[11][5].setWall(true);
        blocks[12][5].setWall(true);
        blocks[11][6].setWall(true);
        blocks[12][6].setWall(true);

        blocks[10][6].setWall(true);
        blocks[11][6].setWall(true);
        blocks[10][7].setWall(true);
        blocks[11][7].setWall(true);
        blocks[10][8].setWall(true);
        blocks[11][8].setWall(true);

        blocks[9][8].setWall(true);
        blocks[10][8].setWall(true);
        blocks[9][9].setWall(true);
        blocks[10][9].setWall(true);
        blocks[9][10].setWall(true);
        blocks[10][10].setWall(true);

        blocks[8][10].setWall(true);
        blocks[9][10].setWall(true);
        blocks[8][11].setWall(true);
        blocks[9][11].setWall(true);
        blocks[8][12].setWall(true);
        blocks[9][12].setWall(true);

        // Left Angle
        blocks[2][4].setWall(true);
        blocks[3][4].setWall(true);
        blocks[2][5].setWall(true);
        blocks[3][5].setWall(true);
        blocks[2][6].setWall(true);
        blocks[3][6].setWall(true);

        blocks[3][6].setWall(true);
        blocks[4][6].setWall(true);
        blocks[3][7].setWall(true);
        blocks[4][7].setWall(true);
        blocks[3][8].setWall(true);
        blocks[4][8].setWall(true);

        blocks[4][8].setWall(true);
        blocks[5][8].setWall(true);
        blocks[4][9].setWall(true);
        blocks[5][9].setWall(true);
        blocks[4][10].setWall(true);
        blocks[5][10].setWall(true);

        blocks[5][10].setWall(true);
        blocks[6][10].setWall(true);
        blocks[5][11].setWall(true);
        blocks[6][11].setWall(true);
        blocks[5][12].setWall(true);
        blocks[6][12].setWall(true);

        // Bottom Support
        blocks[6][13].setWall(true);
        blocks[6][14].setWall(true);
        blocks[6][15].setWall(true);
        blocks[6][16].setWall(true);
        blocks[6][17].setWall(true);
        blocks[6][18].setWall(true);
        blocks[6][19].setWall(true);
        blocks[6][20].setWall(true);
        blocks[6][21].setWall(true);
        blocks[6][22].setWall(true);
        blocks[6][23].setWall(true);
        blocks[6][24].setWall(true);
        blocks[6][25].setWall(true);
        blocks[7][11].setWall(true);
        blocks[7][12].setWall(true);
        blocks[7][13].setWall(true);
        blocks[7][14].setWall(true);
        blocks[7][15].setWall(true);
        blocks[7][16].setWall(true);
        blocks[7][17].setWall(true);
        blocks[7][18].setWall(true);
        blocks[7][19].setWall(true);
        blocks[7][20].setWall(true);
        blocks[7][21].setWall(true);
        blocks[7][22].setWall(true);
        blocks[7][23].setWall(true);
        blocks[7][24].setWall(true);
        blocks[7][25].setWall(true);
        blocks[8][13].setWall(true);
        blocks[8][14].setWall(true);
        blocks[8][15].setWall(true);
        blocks[8][16].setWall(true);
        blocks[8][17].setWall(true);
        blocks[8][18].setWall(true);
        blocks[8][19].setWall(true);
        blocks[8][20].setWall(true);
        blocks[8][21].setWall(true);
        blocks[8][22].setWall(true);
        blocks[8][23].setWall(true);
        blocks[8][24].setWall(true);
        blocks[8][25].setWall(true);

        // Remove wall count from total block count
        totalBlocks -= 89;
    }
}
