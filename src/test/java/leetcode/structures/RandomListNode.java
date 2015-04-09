package leetcode.structures;

public class RandomListNode {
    public int label;
    public RandomListNode next, random;
    public RandomListNode(int x) { this.label = x; }

    @Override
    public String toString() {
        RandomListNode p = this;
        String s = "";
        while (p != null) {
            String r = (p.random == null) ? "(null)" : "(" + p.random.label + ")";
             s += p.label + r + ",";
            p = p.next;
        }
        return s;
    }
}
