package leetcode;

/*
 Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

 For example,
 Given 1->2->3->3->4->4->5, return 1->2->5.
 Given 1->1->1->2->3, return 2->3.
 */

import leetcode.structures.ListNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if( head == null || head.next == null ) return head;
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();

        ListNode p = head;
        while( p != null ) {
            if( freqMap.containsKey( p.val ) ) {
                freqMap.put( p.val, freqMap.get( p.val ) + 1 );
            } else {
                freqMap.put( p.val, 1 );
            }
            p = p.next;
        }

        p = head;
        ListNode prev = null;
        while( p != null ) {
            if( freqMap.get(p.val) > 1 ) {
                if( prev != null ) {
                    prev.next = p.next;
                } else {
                    head = p.next;
                }
                p = p.next;
            } else {
                prev = p;
                p = p.next;
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
