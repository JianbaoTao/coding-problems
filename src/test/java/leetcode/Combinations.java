package leetcode;

/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Combinations {
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if( k <= 0 || k > n ) return result;

        Deque<Integer> stack = new ArrayDeque<Integer>();
        combineDfs( n, k, 1, stack, result );
        return result;
    }

    void combineDfs( int n, int k, int start, Deque<Integer> stack, ArrayList<ArrayList<Integer>> result ) {
        if( stack.size() == k ) {
            result.add( new ArrayList<Integer>( stack ) );
            return;
        }
        for ( int i = start; i <= n - (k - stack.size()) + 1; i++ ) {
            stack.addLast( i );
            combineDfs( n, k, i + 1, stack, result );
            stack.removeLast();
        }
    }


}
