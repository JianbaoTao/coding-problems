package leetcode;

/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] P = new int[m][n]; // stores dynamic min path sum

        // initialize P
        P[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            P[i][0] = P[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            P[0][i] = P[0][i-1] + grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int up = P[i-1][j] + grid[i][j];
                int left = P[i][j-1] + grid[i][j];
                P[i][j] = Math.min(up, left);
            }
        }
        return P[m-1][n-1];
    }
}
