public class UnionFind {

    /* TODO: Add instance variables here. */
    private int[] DisSet;

    /* Creates a UnionFind data structure holding N vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
      DisSet = new int[N];
      for (int i = 0; i < N; i++) {
            DisSet[i] = -1;
          }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        if (v >= DisSet.length || v < 0) {
          throw new IllegalArgumentException();
        } else {
          return -parent(find(v));
        }
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
          return DisSet[v];
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {   
          return find(v1) == find(v2);
     }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid vertices are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
      if (v >= DisSet.length || v < 0) {
        throw new IllegalArgumentException();
      }
      int tofind = v;
      while (parent(tofind) > -1) {
        tofind = parent(tofind);
      }
      while (v != tofind) {
        int curr = parent(v);
        DisSet[v] = tofind;
        v = curr;
      }
      return tofind;
    }

    /* Connects two elements V1 and V2 together. V1 and V2 can be any element,
       and a union-by-size heuristic is used. If the sizes of the sets are
       equal, tie break by connecting V1's root to V2's root. Union-ing a vertex
       with itself or vertices that are already connected should not change the
       structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
      if (v1 >= DisSet.length || v1 < 0 || v2 >= DisSet.length || v2 < 0) {
          return;
        } else {
          if (!connected(v1, v2)) {
            if (sizeOf(v1) <= sizeOf(v2)) {
              DisSet[find(v2)] -= sizeOf(v1);
              DisSet[find(v1)] = find(v2);
            } else {
              DisSet[find(v1)] -= sizeOf(v2);
              DisSet[find(v2)] = find(v1);
            }
          }
        }
    }
}
