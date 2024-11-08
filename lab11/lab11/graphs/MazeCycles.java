package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Stack<Integer> stack;
    private int start;
    private int end;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1,1);
        t = maze.xyTo1D(maze.N(),maze.N());
        distTo[s] = 0;
        edgeTo[s] = s;
        stack = new Stack<>();
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(s);
        if(start != 0) {//不能写iscycle，因为isclcle不能得出最后循环了没有,所以我后来全改成了targetfound
            pop(start);
        }
    }

    // Helper methods go here
    public void dfs(int v){
        marked[v] = true;
        announce();
        stack.push(v);

        if( v == t){
            targetFound = true;
        }

        if(targetFound){
            return;
        }

        for(int w : maze.adj(v)){
            if(!marked[w]){
                stack.push(w);
                //edgeTo[w] = v;这个这里不用写了，最后用stack赋值
                distTo[w] = distTo[v] + 1;
                dfs(w);
                if(targetFound){//这里还在循环内部，所以这里写需要一个判断
                    return;
                }
            }else{
                if( distTo[w] != distTo[v] - 1){//判断w不是v的父辈
                    targetFound = true;
                    distTo[w] = distTo[v] + 1;
                    start = w;
                    return;
                }
            }
        }
    }

    public void pop(int start){
        end = stack.peek();//1，2，3举例理解一下，3-2，2-1，再pop就没有了
        while(stack.peek() != start){
            edgeTo[stack.pop()] = stack.peek();
            announce();
        }
        edgeTo[start] = end;//1-3
        announce();
    }

}


