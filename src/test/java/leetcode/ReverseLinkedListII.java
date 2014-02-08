package leetcode;
/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
 */

import leetcode.structures.ListNode;

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {

        ListNode p0 = null, p = head;
        int count = 1;

        while( count < m ) {
            count++;
            p0 = p;
            p = p.next;
        }
        ListNode frontTail = p0;
        ListNode midTail = p, tmp;

        p0 = p;
        p = p.next;
        while( count < n ) {
            count++;
            tmp = p.next;
            p.next = p0;
            p0 = p;
            p = tmp;
        }

        if( frontTail != null ) {
            frontTail.next = p0;
        } else {
            head = p0;
        }
        midTail.next = p;

        return head;

    }

}
