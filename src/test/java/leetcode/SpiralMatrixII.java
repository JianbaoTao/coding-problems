package leetcode;

import com.tao.Utils;
import org.junit.Test;

/*
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */
public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[] layerTotal = new int[n/2 + 1];
        layerTotal[0] = 0; // layer length outside current index
        for (int i = 0; i < n; i++) {
            int v = Math.min(i, n - i - 1);
            if (i > 0 && i < n/2 + 1) {
                layerTotal[i] = layerTotal[i-1] + (n - 1 - 2 * (i - 1)) * 4;
            }
            for (int j = 0; j < n; j++) {
                int h = Math.min(j, n - j - 1);
                int layer = Math.min(v, h);
                int layerLen = (n - 1 - 2 * layer);
                if (v <= h) { // top or bottom
                    if (i < n / 2) { // top
                        matrix[i][j] = layerTotal[layer] + j - layer + 1;
                    } else { // bottom
                        matrix[i][j] = layerTotal[layer] + layerLen * 2 + (n - 1 - layer) - j + 1;
                    }
                } else { // left or right
                    if (j < n / 2) { // left
                        matrix[i][j] = layerTotal[layer] + layerLen * 3 + (n - 1 - layer) - i + 1;
                    } else { // right
                        matrix[i][j] = layerTotal[layer] + layerLen + i - layer + 1;
                    }
                }
            }
        }
        return matrix;
    }

    @Test
    public void test() {
        int[][] M = generateMatrix(1);
        Utils.printMatrix(M);
    }

}
