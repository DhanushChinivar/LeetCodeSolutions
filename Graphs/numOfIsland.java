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

