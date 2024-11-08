package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{
    private static final int BLANK = 0; // 假设 0 表示空白
    private int[][] tiles;
    private int[][] goal;
    private int size;
    public Board(int[][] tiles){
        size = tiles.length;
        this.tiles = new int[size][size];
        int value = 1;
        goal = new int[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                this.tiles[i][j] = tiles[i][j];
            }
        }

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                goal[i][j] = value++;
            }
        }
        goal[size-1][size-1] = BLANK;
    }

    public int tileAt(int i, int j){
        return tiles[i][j];
    }

    public int size(){
        return size;
    }

    /**
     * Returns neighbors of this board.
     * SPOILERZ: This is the answer.
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming(){
        int hamming = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(tileAt(i, j) == BLANK){
                    break;
                }
                if(tileAt(i,j) != goal[i][j]){
                    hamming++;
                }
            }
        }
        return hamming;
    }

    public int ManHatten(){
        int manhatten = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(tileAt(i,j) != BLANK){
                    int x = tileAt(i,j) / tiles.length ;
                    int y = tileAt(i,j) % tiles.length ;
                    manhatten += Math.abs(x -i) + Math.abs(y -j);
                }
            }
        }
        return manhatten;
    }

    @Override
    public int estimatedDistanceToGoal(){
        return ManHatten();
    }

    @Override
    public boolean equals(Object obj) {
        // 检查引用相等
        if (this == obj) {
            return true;
        }

        // 检查类型
        if (!(obj instanceof Board)) {
            return false;
        }

        Board other = (Board) obj;

        // 比较行和列的大小
        if (this.tiles.length != other.tiles.length || this.tiles[0].length != other.tiles[0].length) {
            return false;
        }

        // 逐个比较每个 tile 的值
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                if (this.tiles[i][j] != other.tiles[i][j]) {
                    return false;
                }
            }
        }

        return true; // 所有条件都满足，返回 true
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
