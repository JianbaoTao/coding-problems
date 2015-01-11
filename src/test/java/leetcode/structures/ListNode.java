package leetcode.structures;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
        next = null;
    }

    public String toCommaString() {
        ListNode p = this;
        String result = "";
        while( p != null ) {
            if (p.next == null) {
                result += "" + p.val;
            } else {
                result += "" + p.val + ",";
            }
            p = p.next;
        }
        return result;
    }

    public static ListNode fromInts(int... ints) {
        ListNode prev = null;
        ListNode head = null;
        for (int x : ints) {
            ListNode node = new ListNode(x);
            if (prev == null) {
                head = node;
            } else {
                prev.next = node;
            }
            prev = node;
        }
        return head;
    }
}
