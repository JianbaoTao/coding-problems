package leetcode;

/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */

import leetcode.structures.TreeNode;

import java.util.ArrayList;

public class UniqueBinarySearchTreesII {
    public ArrayList<TreeNode> generateTrees(int n) {

        return generateTreesDFS(1, n);

    }

    ArrayList<TreeNode> generateTreesDFS( int lower, int upper ) {
        ArrayList<TreeNode> result = new ArrayList<TreeNode>();

        if( upper < lower ) {
            result.add( null );
        }

        for( int i = lower; i <= upper; i++ ) {
            ArrayList<TreeNode> leftTrees = generateTreesDFS( lower, i - 1 );
            ArrayList<TreeNode> rightTrees = generateTreesDFS( i + 1, upper );
            result.addAll( combineTrees( i, leftTrees, rightTrees ) );
        }
        return result;
    }

    ArrayList<TreeNode> combineTrees( int val, ArrayList<TreeNode> leftTrees,
                                      ArrayList<TreeNode> rightTrees ) {
        ArrayList<TreeNode> result = new ArrayList<TreeNode>();
        for( TreeNode left : leftTrees ) {
            for( TreeNode right : rightTrees ) {
                TreeNode node = new TreeNode( val );
                node.left = left;
                node.right = right;
                result.add( node );
            }
        }
        return result;
    }

}
