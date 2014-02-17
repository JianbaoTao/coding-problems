package leetcode;
/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 */

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if( m == 0 ) return 0;
        int n = matrix[0].length;
        if( n == 0 ) return 0;

        int[][] h = new int[m][n]; // horizontal consecutive 1's
        int[][] v = new int[m][n]; // vertical consecutive 1's

        int result = 0;
        for( int i = 0; i < m; i++ ) {
            for( int j = 0; j < n; j++ ) {
                if( matrix[i][j] == '1' ) {
                    h[i][j] = (j > 0) ? h[i][j-1] + 1 : 1;
                    v[i][j] = (i > 0) ? v[i-1][j] + 1 : 1;
                }
                int minHeight = Integer.MAX_VALUE;
                for( int jj = j; j - jj + 1 <= h[i][j]; jj-- ) {
                    minHeight = Math.min( minHeight, v[i][jj] );
                    result = Math.max( result, minHeight * (j - jj + 1) );
                }
            }
        }
        return result;
    }

    @Test
    public void test3() {
        char[][] matrix = new char[][] {
                {'0','1','1','0','1'},
                {'1','1','0','1','0'},
                {'0','1','1','1','0'},
                {'1','1','1','1','0'},
                {'1','1','1','1','1'},
                {'0','0','0','0','0'}
        };
        assertEquals( 9, maximalRectangle( matrix ) );
    }


    @Test @Ignore
    public void test() {
        char[][] matrix = new char[][] {
                {'1'}
        };
        assertEquals( 1, maximalRectangle( matrix ) );
    }

    @Test @Ignore
    public void test2() {
        char[][] matrix = new char[][] {
                {'0','1'}
        };
        assertEquals( 1, maximalRectangle( matrix ) );
    }



}
