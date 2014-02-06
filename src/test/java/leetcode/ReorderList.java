package leetcode;

import leetcode.structures.ListNode;

public class ReorderList {
    public void reorderList(ListNode head) {
        int n = listLength( head );
        if( n <= 2 ) return;

        // break at half
        ListNode secondHead = breakList( head, n );

        // reverse second half
        secondHead = reverseList( secondHead );

        // mix
        ListNode p1 = head, p2 = secondHead;
        while( p2 != null ) {
            ListNode tmp = p1.next;
            p1.next = p2;
            p1 = tmp;
            tmp = p2.next;
            p2.next = p1;
            p2 = tmp;
        }
    }

    int listLength( ListNode head ) {
        int n = 0;
        while( head != null ) {
            n++;
            head = head.next;
        }
        return n;
    }

    ListNode breakList( ListNode head, int listLength ) {
        ListNode p1 = null, p2 = head;
        for( int i = 0; i < (listLength + 1) / 2; i++ ) {
            p1 = p2;
            p2 = p2.next;
        }
        p1.next = null;
        return p2;
    }

    ListNode reverseList( ListNode head ) {
        if( head == null ) return head;
        ListNode p1 = head, p2 = head.next;
        p1.next = null;
        while( p2 != null ) {
            ListNode tmp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = tmp;
        }
        return p1;
    }
}
