package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    //每行有几个
    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }

        return s + 2 * effectiveI;
    }

    //以第一行第一个为原点，确定每行的起始坐标，-x
    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }


    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    //画图了，填充六边形了
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        // hexagons have 2*s rows. this code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;

            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);//确定放的位置

            int rowWidth = hexRowWidth(s, yi);//确定放多少个

            addRow(world, rowStartP, rowWidth, t);

        }
    }

    public static class Position{
        public int x;
        public int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int WIDTH = 55;
    private static final int HEIGHT = 30;
    private static final Random RANDOM = new Random();

    //最中间的图案
    public static void middle( TETile[][] world ,int width) {
        int x = (WIDTH-1) / 2 - 1;
        for(int y = 0; y+ (2*width - 1) <= HEIGHT; y += 2*width ) { //第一行是0，不是1
            Position p = new Position(x, y);
            addHexagon(world, p, width, randomTile());
         }
    }

    public static void left( TETile[][] world, int width){
        int x = (WIDTH-1) / 2 - (2 * width - 1);
        int size = width * 2;
        for ( int i = 0; i < width - 1; i++) {
            int y = width + i * width;
            for ( int j = 0; j < width-i + 1; j++) {
                Position p = new Position(x, y);
                addHexagon(world, p, width, randomTile());
                y += size;
            }
            x = x - (2 * width - 1);
        }
    }

    public static void right( TETile[][] world, int width){
        int x = (WIDTH-1) / 2 - 1 + (2 * width - 1);
        int size = width * 2;
        for ( int i = 0; i < width - 1; i++) {
            int y = width + i * width;
            for ( int j = 0; j < width-i + 1; j++) {
                Position p = new Position(x, y);
                addHexagon(world, p, width, randomTile());
                y += size;
            }
            x = x + (2 * width - 1);
        }
    }



    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.SAND;
            case 1: return Tileset.MOUNTAIN;
            case 2: return Tileset.TREE;
            case 3: return Tileset.WATER;
            case 4: return Tileset.FLOWER;
            default: return Tileset.FLOWER;
        }
    }

    public static void main(String[] args) {
        //先搞个世界，全黑
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];//这个时候还没有对象，要给每个位置初始化一个对象
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        middle(world, 3);
        left(world, 3);
        right(world, 3);
        ter.renderFrame(world);
    }
}
