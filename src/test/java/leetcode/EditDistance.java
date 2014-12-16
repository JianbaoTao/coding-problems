package leetcode;

import org.junit.Test;

/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/
public class EditDistance {

    public int minDistance(String word1, String word2) {
        return minDistance_dp(word1, word2);
    }

    /**
     * Basic idea:
     * Maintain a (M+1)x(N+1) matrix, where M = word1.length, N = word2.length. For an arbitrary element (i,j) in the
     * matrix, the value represents the minimum edit distance for between sub-word1 of length i and sub-word2 of
     * length j.
     * <p/>
     * Suppose the matrix is called P, and P[i][j] is unknown, but P[i-1][j-1], P[i-1][j], and P[i][j-1] are known.
     * Suppose the last characters in word1 and word2 corresponding to P[i][j] are c1 and c2. In order to get
     * P[i][j], if c1 == c2, then P[i-1][j-1] = P[i][j]. Else,
     * P[i][j] = min{P[i-1][j-1], P[i-1][j], P[i][j-1]} + 1.
     */
    private int minDistance_dp(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        int M = word1.length(), N = word2.length();
        if (M == 0 || N == 0) {
            return Math.abs(M - N);
        }

        // init P
        int[][] P = new int[M + 1][N + 1];
        P[0][0] = 0;
        for (int i = 1; i <= M; i++) {
            P[i][0] = i;
        }
        for (int i = 1; i <= N; i++) {
            P[0][i] = i;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    P[i][j] = P[i - 1][j - 1];
                } else {
                    P[i][j] = Math.min(P[i - 1][j - 1], Math.min(P[i - 1][j], P[i][j - 1])) + 1;
                }
            }
        }
        return P[M][N];
    }

    @Test
    public void test() {
        String word1 = "dinitrophenylhydrazine", word2 = "acetylphenylhydrazine";
        System.out.println(minDistance(word1, word2));

    }
}
