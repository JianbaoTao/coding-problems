package leetcode;

import java.util.HashSet;
import java.util.Set;

/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> cols = new HashSet<Integer>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (int r : rows) {
            for( int j = 0; j < matrix[0].length; j++) {
                matrix[r][j] = 0;
            }
        }
        for (int c : cols) {
            for( int j = 0; j < matrix.length; j++) {
                matrix[j][c] = 0;
            }
        }
    }
}
