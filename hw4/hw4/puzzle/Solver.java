package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {
    private int moves;
    private MinPQ<SearchNode> pq;
    private List<WorldState> solution;
    private SearchNode goalNode;
    public Solver(WorldState initial){
        moves = 0;
        pq = new MinPQ<>();
        solution = new ArrayList<>();
        SearchNode currentNode = new SearchNode(initial, 0, null);
        goalNode = currentNode;
        pq.insert(currentNode);

        while(!pq.isEmpty()){
            SearchNode node = pq.delMin();

            if(node.current.isGoal()){
                getAnswer(node);
                return;
            }else{
              for(WorldState a: node.current.neighbors()) {
                if (node.parent == null || !a.equals(node.parent.current)) {
                    pq.insert(new SearchNode(a, node.height + 1, node));

                }
              }
            }
        }
    }

    public void getAnswer(SearchNode a){
        goalNode = a;
        moves = a.height;
        while(goalNode != null){
            solution.add(goalNode.current);
            goalNode = goalNode.parent;
        }
    }

    public int moves(){
        return moves;
    }
    public Iterable<WorldState> solution(){
        List<WorldState> a = new ArrayList<>();
        for(int i = moves; i >= 0; i--){
            a.add(solution.get(i));
        }
        return a;
    }

        public class SearchNode implements Comparable<SearchNode> {
        private WorldState current;
        private SearchNode parent;
        private int priority;
        private int height;
          public SearchNode(WorldState current, int height, SearchNode parent){
              this.current = current;
              this.parent = parent;
              this.height = height;
              this.priority = height + current.estimatedDistanceToGoal();
          }
          public int compareTo(SearchNode other) {
              return this.priority - other.priority;
          }

        }
}