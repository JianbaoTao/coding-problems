package leetcode;

/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
 */

import leetcode.structures.ListNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if( head == null || head.next == null ) return head;

        Set<Integer> set = new HashSet<Integer>();
        set.add( head.val );
        ListNode p = head.next;
        ListNode prev = head;
        while( p != null ) {
            if( set.contains( p.val ) ) {
                prev.next = p.next;
                p = p.next;
            } else {
                set.add( p.val );
                p = p.next;
                prev = prev.next;
            }
        }
        return head;
    }

    @Test
    public void test() {
        ListNode a0 = new ListNode(1);
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        a0.next = a1;
        a1.next = a2;

        ListNode head = deleteDuplicates( a0 );
        System.out.println( head.toCommaString() );
    }
}
