package leetcode;

import leetcode.structures.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycleII {
    /*
    Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

    Follow up:
    Can you solve it without using extra space?
     */

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();

        while( head != null ) {
            if( set.contains( head ) ) return head;
            set.add( head );
            head = head.next;
        }
        return null;
    }
}
