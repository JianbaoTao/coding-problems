package leetcode;

/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
 */

import leetcode.structures.ListNode;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartitionList {
    public ListNode partition(ListNode head, int x) {

        ListNode p1 = null, p2 = head, p2Prev = null;

        while( p2 != null ) {
            if( p2.val < x ) {
                if( p1 == p2Prev ) {
                    p1 = p2;
                    p2 = p2.next;
                    p2Prev = p1;
                } else {
                    p2Prev.next = p2.next;
                    if( p1 == null ) {
                        p2.next = head;
                        head = p2;
                    } else {
                        p2.next = p1.next;
                        p1.next = p2;
                    }
                    p1 = p2;
                    p2 = p2Prev.next;
                }
            } else {
                p2Prev = p2;
                p2 = p2.next;
            }
        }
        return head;
    }

    @Test  //@Ignore
    public void test() {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(2);
        ListNode a5 = new ListNode(5);
        ListNode a6 = new ListNode(2);

        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;

        assertEquals( partition(a1, 3 ).toCommaString(), "1,2,2,4,3,5," );
    }

    @Test
    public void test2() {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(1);
        a1.next = a2;

        assertEquals( partition(a1, 2 ).toCommaString(), "1,1," );
    }

    @Test
    public void test3() {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        a1.next = a2;
        a2.next = a3;

        assertEquals( partition(a1, 4 ).toCommaString(), "1,2,3," );
    }


}
