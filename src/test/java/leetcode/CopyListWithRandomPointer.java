package leetcode;

import leetcode.structures.RandomListNode;
import org.junit.Test;

public class CopyListWithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;

        RandomListNode p = head, p1;
        while (p != null) {
            p1 = new RandomListNode(p.label);
            p1.next = p.next;
            p.next = p1;
            p = p1.next;
        }

        p = head;
        while (p != null) {
            p1 = p.next;
            if (p.random != null) {
                p1.random = p.random.next;
            }
            p = p1.next;
        }

        RandomListNode dummy = new RandomListNode(0);
        p1 = dummy;

        for (p = head; p != null; ) {
            p1.next = p.next;
            p1 = p1.next;
            p = p1.next;
        }

        return dummy.next;
    }

    @Test
    public void test1() throws Exception {
        RandomListNode head = new RandomListNode(1);
        head.next = new RandomListNode(2);
        head.next.random = head;
        head.random = head.next;

        head = copyRandomList(head);
        System.out.println(head);
    }
}
