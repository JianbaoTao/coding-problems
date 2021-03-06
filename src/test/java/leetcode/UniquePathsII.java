package leetcode;

/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        boolean hasObstacle = false;
        int[][] P = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1 && !hasObstacle) {
                hasObstacle = true;
            }
            if (hasObstacle) {
                P[i][0] = 0;
            } else {
                P[i][0] = 1;
            }
        }

        hasObstacle = false;
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1 && !hasObstacle) {
                hasObstacle = true;
            }
            if (hasObstacle) {
                P[0][i] = 0;
            } else {
                P[0][i] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    P[i][j] = 0;
                } else {
                    P[i][j] = P[i-1][j] + P[i][j-1];
                }
            }
        }
        return P[m-1][n-1];
    }
}
