package leetcode;

/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] num) {
        return findMinBinarySearch(num, 0, num.length - 1);
    }

    private int findMinBinarySearch(int[] num, int start, int end) {
        if (start == end) return num[start];
        if (end - start == 1) {
            return Math.min(num[start], num[end]);
        } else { // end - start > 1
            int mid = (start + end) / 2;
            if (num[mid] < num[start]) {
                return findMinBinarySearch(num, start, mid);
            } else if (num[mid + 1] > num[end]) {
                return findMinBinarySearch(num, mid + 1, end);
            } else {
                return Math.min(num[start], num[mid + 1]);
            }
        }
    }
}
