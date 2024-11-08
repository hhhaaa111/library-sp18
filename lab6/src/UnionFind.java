public class UnionFind{
    private int[] parent;

    public UnionFind(int n){
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = -1;
        }//{-1, -1, -1, -1,  -1}  union(0,1);
        //{-2,0,-1,-1,-1}
    }

    public void validate(int v1) {
        if (v1 < 0 || v1 >= parent.length) {
            throw new IllegalArgumentException("不是有效索引");
        }
    }

    public int sizeOf(int v1) {
        validate(v1);
        int root = find(v1);
        return -parent[root];
    }

    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }

    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int root1 = find(v1);
        int root2 = find(v2);
        if ( root1 != root2) {
            if( -parent(root2) > -parent(root1) ){
                parent[root2] += parent[root1];
                parent[root1] = root2;
            }else{
                parent[root1] += parent[root2];
                parent[root2] = root1;
            }
        }
    }

    public int find(int v1) {
        while( parent[v1] >= 0) {
            v1 = parent[v1];
        }
        return v1;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(6);
        uf.union(1,0);
        uf.union(2, 0);
        uf.union(3, 2);
        uf.union(4, 2);
        uf.union(5, 4);

        System.out.println(uf.sizeOf(0));
        System.out.println(uf.parent(3));
        System.out.println(uf.connected(1,5));
    }
}