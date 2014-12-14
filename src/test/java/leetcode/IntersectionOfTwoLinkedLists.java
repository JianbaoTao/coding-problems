package leetcode;

import leetcode.structures.ListNode;

public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // find length of A
        int alen = findLength(headA);
        if (alen == 0) return null;

        // find length of B
        int blen = findLength(headB);
        if (blen == 0) return null;

        // move the difference
        ListNode p1, p2;
        if( alen > blen) {
            p1 = premove(headA, alen - blen);
            p2 = headB;
        } else {
            p1 = headA;
            p2 = premove(headB, blen - alen);
        }

        // move A and B together until hit the intersection
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        // check if the final nodes are the same
        if (p1 != null && p1 == p2) return p1;
        return null;
    }

    private ListNode premove(ListNode head, int moves) {
        for (int i = 0; i < moves; i++) {
            head = head.next;
        }
        return head;
    }

    private int findLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
