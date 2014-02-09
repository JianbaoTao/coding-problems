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
            result += "" + p.val + ",";
            p = p.next;
        }
        return result;
    }
}
