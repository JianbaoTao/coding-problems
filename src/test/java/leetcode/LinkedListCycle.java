package leetcode;

import leetcode.structures.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
    /*
    Given a linked list, determine if it has a cycle in it.

    Follow up:
    Can you solve it without using extra space?
     */

    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();

        while( head != null ) {
            if( set.contains( head ) ) return true;
            set.add( head );
            head = head.next;
        }
        return false;

    }
}
