package lab11.graphs;

import java.util.LinkedList;
import java.util.List;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields继承的分别是：
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX,sourceY);
        t = maze.xyTo1D(targetX,targetY);
        distTo[s] = 0;//距离
        edgeTo[s] = s;//相邻点
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        List<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        marked[s] = true;

        while(!queue.isEmpty() && !targetFound) {
            int v = queue.remove(0);
            announce();

             for(int w : maze.adj(v)) {
                if(!marked[w]) {
                    marked[w] = true;
                    queue.add(w);
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
                if( w == v){
                    targetFound = true;
                    break;
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

