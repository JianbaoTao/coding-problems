package leetcode;

/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        if( word.length() == 0 ) return false;
        if( m * n < word.length() ) return false;

        for ( int i = 0; i < m; i++ ) {
            for ( int j = 0; j < n; j++ ) {
                if( board[i][j] == word.charAt(0) ) {
                    if( dfs( board, i, j, word, 0, null ) ) return true;
                }
            }
        }
        return false;
    }

    boolean dfs( char[][] board, int i0, int j0, String word, int ihead, Set<List<Integer>> visited ) {
        if( ihead == word.length() - 1 ) return true;
        if( visited == null ) {
            visited = new HashSet<List<Integer>>();
        }
        visited.add(Arrays.asList( i0, j0 ) );


        // up
        if( i0 - 1 >= 0 && !visited.contains(Arrays.asList(i0 - 1, j0)) &&
                board[i0 - 1][j0] == word.charAt(ihead + 1) ) {
            if( dfs( board, i0 - 1, j0, word, ihead + 1, visited ) ) return true;
        }

        // left
        if( j0 - 1 >= 0 && !visited.contains(Arrays.asList(i0, j0 - 1)) &&
                board[i0][j0 - 1] == word.charAt(ihead + 1) ) {
            if( dfs( board, i0, j0 - 1, word, ihead + 1, visited ) ) return true;
        }

        // down
        if( i0 + 1 < board.length && !visited.contains(Arrays.asList(i0 + 1, j0)) &&
                board[i0 + 1][j0] == word.charAt(ihead + 1) ) {
            if( dfs( board, i0 + 1, j0, word, ihead + 1, visited ) ) return true;
        }

        // right
        if( j0 + 1 < board[0].length && !visited.contains(Arrays.asList(i0, j0 + 1)) &&
                board[i0][j0 + 1] == word.charAt(ihead + 1) ) {
            if( dfs( board, i0, j0 + 1, word, ihead + 1, visited ) ) return true;
        }
        visited.remove( Arrays.asList( i0, j0 ) );
        return false;
    }

    @Test
    public void test() {
        char[][] board;

        board = new char[][] {
                {'a', 'a'}
        };
//        assertTrue( exist(board, "a") );

        board = new char[][] {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
//        assertFalse( exist( board, "ABCB" ) );

        board = new char[][] {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        assertTrue( exist( board, "ABCESEEEFS" ) );

    }
}
