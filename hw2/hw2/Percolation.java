package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public boolean[][] world;
    public int N;
    public WeightedQuickUnionUF uf;
    public WeightedQuickUnionUF ug;
    private int top;
    private int bottom;
    private int size;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        world = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        ug = new WeightedQuickUnionUF(N*N + 1);
        top = N * N;
        bottom = N * N + 1;
        for(int i = 0; i < N; i++){
            uf.union(top,xyToiD(0,i));
        }
        for(int i = 0; i < N; i++){
            uf.union(bottom,xyToiD(N-1,i));
        }
        for(int i = 0; i < N; i++){
            ug.union(top,xyToiD(0,i));
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                world[i][j] = false;
            }
        }
        size = 0;
    }

    public void open(int row, int col) {
        warn(row, col);

        if( !isOpen(row, col)) {
            world[row][col] = true;

            size += 1;
            unioneighbours(row,col,row+1,col);
            unioneighbours(row,col,row-1,col);
            unioneighbours(row,col,row,col-1);
            unioneighbours(row,col,row,col+1);

        }
    }

    public void unioneighbours(int row, int col,int newrow, int newcol) {
        if(newrow < 0 || newrow >= N || newcol < 0 || newcol >= N) {
            return ;
        }
        if(world[newrow][newcol]) {
            uf.union(xyToiD(row, col), xyToiD(newrow, newcol));
            ug.union(xyToiD(row, col), xyToiD(newrow, newcol));
        }
    }

    public int xyToiD(int row, int col) {
        return row*N + col;
    }

    public boolean isOpen(int row, int col) {
        warn(row, col);
        return world[row][col];
    }


    public boolean isFull(int row, int col) {
        warn(row,col);
        if( !isOpen(row,col) ){
            return false;
        }
        int index = xyToiD(row, col);
        return ug.connected(top, index);
    }


    public int numberOfOpenSites() {
        return size;
    }

    public boolean percolates() {
        if( numberOfOpenSites() == 0) {
            return false;
        }
        return uf.connected(top, bottom);
    }

    public void warn(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("Row or column out of bounds");
        }
    }

}
//package hw2;
//
//import edu.princeton.cs.algs4.WeightedQuickUnionUF;
//
//public class Percolation {
//    private final int[][] world;
//    private final int N;
//    private final WeightedQuickUnionUF uf;
//    private final WeightedQuickUnionUF ug;
//    private final int top;
//    private final int bottom;
//    private int size;
//
//    public Percolation(int N) {
//        this.N = N;
//        world = new int[N][N];
//        uf = new WeightedQuickUnionUF(N * N + 2);
//        ug = new WeightedQuickUnionUF(N * N + 1);
//        top = N * N;
//        bottom = N * N + 1;
//
//        // Connect the top and bottom virtual nodes
//        for (int i = 0; i < N; i++) {
//            uf.union(top, i);
//            ug.union(top, i);
//        }
//        for (int i = 0; i < N; i++) {
//            uf.union(bottom, (N - 1) * N + i);
//        }
//
//        size = 0;
//    }
//
//    public void open(int row, int col) {
//        if (!isOpen(row, col)) {
//            world[row][col] = 1; // Mark as open
//            size++;
//
//            int index = xyToiD(row, col);
//
//            // Connect to open adjacent sites
//            if (col - 1 >= 0 && isOpen(row, col - 1)) {
//                uf.union(index, xyToiD(row, col - 1));
//                ug.union(index, xyToiD(row, col - 1));
//            }
//            if (col + 1 < N && isOpen(row, col + 1)) {
//                uf.union(index, xyToiD(row, col + 1));
//                ug.union(index, xyToiD(row, col + 1));
//            }
//            if (row - 1 >= 0 && isOpen(row - 1, col)) {
//                uf.union(index, xyToiD(row - 1, col));
//                ug.union(index, xyToiD(row - 1, col));
//            }
//            if (row + 1 < N && isOpen(row + 1, col)) {
//                uf.union(index, xyToiD(row + 1, col));
//                ug.union(index, xyToiD(row + 1, col));
//            }
//        }
//    }
//
//    public int xyToiD(int row, int col) {
//        return row * N + col;
//    }
//
//    public boolean isOpen(int row, int col) {
//        if (row < 0 || row >= N || col < 0 || col >= N) {
//            throw new IndexOutOfBoundsException("Row or column out of bounds");
//        }
//        return world[row][col] == 1;
//    }
//
//    public boolean isFull(int row, int col) {
//        if (row < 0 || row >= N || col < 0 || col >= N) {
//            throw new IndexOutOfBoundsException("Row or column out of bounds");
//        }
//        return world[row][col] == 1 && ug.connected(top, xyToiD(row, col));
//    }
//
//    public int numberOfOpenSites() {
//        return size;
//    }
//
//    public boolean percolates() {
//        return size > 0 && uf.connected(top, bottom);
//    }
//}

