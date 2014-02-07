package leetcode;

/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?

 */

import leetcode.structures.TreeNode;

import java.util.*;

public class BinaryTreeInorderTraversal {
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if( root == null ) return result;

        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        Set<TreeNode> set = new HashSet<TreeNode>(); // visited set

        stack.push( root );

        while( stack.size() > 0 ) {
            TreeNode node = stack.peek();
            if( node.left != null && !set.contains( node.left ) ) {
                stack.push( node.left );
                continue;
            }
            node = stack.pop();
            set.add( node );

            result.add( node.val );

            if( node.right != null ) {
                stack.push( node.right );
            }
        }

        return result;

    }
}
