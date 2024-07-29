package blockbreaker;

import java.awt.*;

public class mapGenerator {
    public int map[][];
    public int blockwidth;
    public int blockheight;

    public mapGenerator(int row, int col) {
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1; // 1 represents that the block has not been touched by ball yet.
            }
        }

        blockwidth = 540 / col;
        blockheight = 150 / row;
    }

    // draw the blocks.
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.white);
                    g.fillRect(j * blockwidth + 80, i * blockheight + 50, blockwidth, blockheight);


                    //create border in map

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.blue);
                    g.drawRect(j * blockwidth + 80, i * blockheight + 50, blockwidth, blockheight);

                }
            }
        }
    }

    public void setBlockValue(int value, int row, int col){
        map[row][col]=value;
    }
}
