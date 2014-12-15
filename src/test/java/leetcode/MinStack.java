package leetcode;

import java.util.Stack;

public class MinStack {
    Stack<Integer> stack = new Stack<Integer>();
    int min = Integer.MAX_VALUE;

    public void push(int x) {
        stack.push(x);
        if (x < min) {
            min = x;
        }
    }

    public void pop() {
        int x = stack.pop();
        if (x == min) {
            // update min
            min = stack.size() > 0 ? stack.peek() : Integer.MAX_VALUE;
            for( Integer element : stack) {
                min = Math.min(min, element);
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
