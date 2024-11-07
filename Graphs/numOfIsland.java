Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.

//Dhanush Chinivar


//Solution

class Solution {
       void markCurrentIslandStatus(char[][] arrayMatrix, int x, int y, int r, int c) {
        if (x < 0 || x >= r || y < 0 || y >= c || arrayMatrix[x][y] != '1'){ // Boundary case for matrix
            return;
        }

        // Mark current cell as visited - this is just for our reference can choose any identity
        matrix[x][y] = '2';

        // Make recursive calls in all 4 adjacent directions
        markCurrentIsland(arrayMatrix, x + 1, y, r, c); // DOWN
        markCurrentIsland(arrayMatrix, x, y + 1, r, c); // RIGHT
        markCurrentIsland(arrayMatrix, x - 1, y, r, c); // TOP
        markCurrentIsland(arrayMatrix, x, y - 1, r, c); // LEFT
    }

    public int numIslands(char[][] grid) {
        int rows = grid.length;
        if (rows == 0){ // Empty grid boundary case
            return 0;
        }
        int cols = grid[0].length;

        // Iterate for all cells of the array
        int noOfIslands = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '1') {
                    markCurrentIslandStatus(grid, i, j, rows, cols);
                    noOfIslands += 1;
                }
            }
        }
        return noOfIslands;
    }
}

/*
Dry Run/Explanation

We consider each cell with '1' to be part of an island.
For each '1' cell (land) we discover, we initiate a DFS to identify all connected '1's (land cells) as 'visited' by turning them to '2'.
This ensures that we only count each island once.

Steps:
Go through each cell in the grid.
If the cell is '1' (representing unexplored land):
Increase the island count.
Begin a DFS to mark all connected land cells (connected sections of the island) as visited.
DFS goes in four directions: up, down, left, and right, labelling each connecting cell as visited ('2') until there is no more land to mark.
Once all cells have been examined, the island count indicates the overall number of islands.

*/



// Improvised/Alternative Solution


class Solution {
    class UnionFind {
        int[] parent;
        int[] rank;
        int count;  // Number of disjoint sets (islands)

        public UnionFind(char[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            parent = new int[rows * cols];
            rank = new int[rows * cols];
            count = 0;

            // Initialize UnionFind structure
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        int id = i * cols + j;
                        parent[id] = id;  // Each cell is its own parent initially
                        count++;  // Count each '1' cell as a separate island initially
                    }
                }
            }
        }

        // Find with path compression
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // Path compression
            }
            return parent[x];
        }

        // Union with union by rank
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;  // Decrease count of disjoint sets
            }
        }

        public int getCount() {
            return count;
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        UnionFind uf = new UnionFind(grid);

        // Directions for right and down only (to avoid duplicates)
        int[][] directions = {{0, 1}, {1, 0}};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    int id1 = i * cols + j;
                    for (int[] direction : directions) {
                        int x = i + direction[0];
                        int y = j + direction[1];
                        if (x < rows && y < cols && grid[x][y] == '1') {
                            int id2 = x * cols + y;
                            uf.union(id1, id2);
                        }
                    }
                }
            }
        }

        return uf.getCount();
    }
}
/*
We use a Union-Find data structure to manage "connected components."
Each land cell ('1') is initially considered its own island.
We connect (or "union") neighboring land cells into a single component (island) whenever they are adjacent.

Steps:

Initialize each land cell as its own island in the Union-Find structure.
For each land cell, check its right and down neighbors (to avoid double-counting connections):
If the neighbor is also '1', perform a union operation to merge the two cells into a single component (island).
At the end, the number of unique root cells in the Union-Find structure gives the number of distinct islands.
*/





