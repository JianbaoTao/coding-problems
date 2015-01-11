package leetcode;

import leetcode.structures.ListNode;
import org.junit.Test;

/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null || head.next == null || n == 0) {
            return head;
        }

        ListNode tail = head;
        int len = 1;

        while (tail.next != null) {
            len++;
            tail = tail.next;
        }
        int shift = n % len;

        if (shift == 0) {
            return head;
        }

        ListNode p = head;
        int i = 1;
        while (i < len - shift) {
            p = p.next;
            i++;
        }
        tail.next = head;
        head = p.next;
        p.next = null;

        return head;
    }

    @Test
    public void test() {
        ListNode head = ListNode.fromInts(1, 2, 3, 4, 5);
        System.out.println("before: " + head.toCommaString());

        head = rotateRight(head, 2);
        System.out.println("after: " + head.toCommaString());
    }
}
