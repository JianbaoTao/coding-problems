package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
 */

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] height) {
        int maxArea = 0;
        int n = height.length;
        for( int i = 0; i < n; i++ ) {
            if( i < n - 1 && height[i] <= height[i+1] ) continue;
            int maxHeight = height[i];
            for( int j = i; j >= 0; j-- ) {
                int l = i - j + 1;
                maxHeight = Math.min( height[j], maxHeight );
                maxArea = Math.max( maxArea, maxHeight * l );
            }
        }
        return maxArea;
    }

    @Test
    public void test() {
        int[] height = new int[]{2,1,5,6,2,3};
        assertEquals( 10, largestRectangleArea( height ) );

    }

    @Test
    public void test2() {
        int[] height = new int[]{1,2,3,4,5};
        assertEquals( 9, largestRectangleArea( height ) );

    }
}
