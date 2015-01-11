package com.tao;

public class Utils {
    public static void printMatrix(int[][] M) {
        int m = M.length;
        int n = M[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.format("%8d", M[i][j]);
            }
            System.out.print("\n");
        }
    }
}
