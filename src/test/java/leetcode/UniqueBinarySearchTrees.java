package leetcode;

/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */

public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        return numTreesDFS(1, n);
    }

    int numTreesDFS( int lower, int upper ) {
        if( upper < lower ) return 1;
        int nTrees = 0;
        for( int i = lower; i <= upper; i++ ) {
            int left = numTreesDFS(lower, i - 1);
            int right = numTreesDFS( i + 1, upper );
            nTrees += left * right;
        }
        return nTrees;
    }
}
