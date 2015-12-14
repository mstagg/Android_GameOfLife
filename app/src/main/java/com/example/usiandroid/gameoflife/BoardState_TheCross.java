package com.example.usiandroid.gameoflife;

/**
 * Created by matthew on 12/13/15.
 */
public class BoardState_TheCross extends BoardState {

    public BoardState_TheCross(int width, int height) {
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

    private void setWallCells(){
        // Top Cross
        blocks[7][0].setWall(true);
        blocks[7][1].setWall(true);
        blocks[7][2].setWall(true);
        blocks[7][3].setWall(true);
        blocks[7][4].setWall(true);
        blocks[7][5].setWall(true);
        blocks[7][6].setWall(true);
        blocks[7][7].setWall(true);
        blocks[7][8].setWall(true);
        blocks[7][9].setWall(true);
        blocks[7][10].setWall(true);
        blocks[7][11].setWall(true);

        // Bottom Cross
        blocks[7][18].setWall(true);
        blocks[7][19].setWall(true);
        blocks[7][20].setWall(true);
        blocks[7][21].setWall(true);
        blocks[7][22].setWall(true);
        blocks[7][23].setWall(true);
        blocks[7][24].setWall(true);
        blocks[7][25].setWall(true);
        blocks[7][26].setWall(true);
        blocks[7][27].setWall(true);
        blocks[7][28].setWall(true);
        blocks[7][29].setWall(true);

        // Left Cross
        blocks[0][14].setWall(true);
        blocks[1][14].setWall(true);
        blocks[2][14].setWall(true);
        blocks[3][14].setWall(true);
        blocks[4][14].setWall(true);
        blocks[0][15].setWall(true);
        blocks[1][15].setWall(true);
        blocks[2][15].setWall(true);
        blocks[3][15].setWall(true);
        blocks[4][15].setWall(true);

        // Right Cross
        blocks[10][14].setWall(true);
        blocks[11][14].setWall(true);
        blocks[12][14].setWall(true);
        blocks[13][14].setWall(true);
        blocks[14][14].setWall(true);
        blocks[10][15].setWall(true);
        blocks[11][15].setWall(true);
        blocks[12][15].setWall(true);
        blocks[13][15].setWall(true);
        blocks[14][15].setWall(true);

        totalBlocks -= 42;
    }
}
