package leetcode;

import org.junit.Test;

import java.util.*;

/*
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

Reference: http://zkfairytale.blogspot.ca/2014/12/maximum-gap.html
*/
public class MaximumGap {
    public int maximumGap(int[] num) {
        return maximumGap_naive(num);
    }

    public int maximumGap_naive(int[] num) {
        Arrays.sort(num);

        int maxGap = 0;
        for (int i = 1; i < num.length; i++) {
            maxGap = Math.max(maxGap, Math.abs(num[i] - num[i-1]));
        }
        return maxGap;
    }

    public int maximumGap_bucket(int[] num) {
        if (num.length < 2) return 0;

        int max = findMinMax(num, 1);
        int min = findMinMax(num, -1);

        int buckets = num.length - 1;
        double step = (max - min + 0.0) / buckets;

        // bucketize
        Map<Integer, List<Integer>> bucketMinMax = bucketize(num, min, step);

        int lastMax = bucketMinMax.get(0).size() > 1 ? bucketMinMax.get(0).get(1) : bucketMinMax.get(0).get(0);
        int maxGap = lastMax - min;
        for (int i = 1; i < buckets; i++) {
            List<Integer> list = bucketMinMax.get(i);
            if (list != null && list.size() > 0) {
                maxGap = Math.max(maxGap, list.get(0) - lastMax);
                lastMax = list.size() > 1 ? list.get(1) : list.get(0);
            }
        }
        return maxGap;
    }

    private Map<Integer, List<Integer>> bucketize(int[] num, int min, double step) {
        Map<Integer, List<Integer>> bucketMap = new HashMap<Integer, List<Integer>>();
        for (int i:num) {
            int ibucket = findBucketIndex(i, min, step);
            addToBucketMap(bucketMap, ibucket, i);
        }
        return bucketMap;
    }

    private void addToBucketMap(Map<Integer, List<Integer>> bucketMap, int ibucket, int element) {
        List<Integer> list;
        if (bucketMap.containsKey(ibucket)) {
            list = bucketMap.get(ibucket);
        } else {
            list = new ArrayList<Integer>();
            bucketMap.put(ibucket, list);
        }
        if (list.isEmpty()) {
            list.add(element);
        } else if (list.size() == 1) {
            if (element > list.get(0)) {
                list.add(element);
            }
            if (element < list.get(0)) {
                list.add(0, element);
            }
        } else { // list.size == 2
            if (element < list.get(0)) { // min
                list.set(0, element);
            } else if (element > list.get(1)) {
                list.set(1, element);
            }
        }
    }

    private int findBucketIndex(int element, int min, double step) {
        return (int)((element - 0.5 * step - min) / step);
    }

    private int findMinMax(int[] num, int flag) {
        int output = num[0];
        for (int i:num) {
            if (flag < 0) {
                output = Math.min(output, i);
            } else {
                output = Math.max(output, i);
            }
        }
        return output;
    }

    public int maximumGap_notMine(int[] num) {  // not my solution
        if (num == null || num.length < 2)
            return 0;
        // get the max and min value of the array
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i:num) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        // the minimum possibale gap, ceiling of the integer division
        int gap = (int)Math.ceil((double)(max - min)/(num.length - 1));
        int[] bucketsMIN = new int[num.length - 1]; // store the min value in that bucket
        int[] bucketsMAX = new int[num.length - 1]; // store the max value in that bucket
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
        // put numbers into buckets
        for (int i:num) {
            if (i == min || i == max)
                continue;
            int idx = (i - min) / gap; // index of the right position in the buckets
            bucketsMIN[idx] = Math.min(i, bucketsMIN[idx]);
            bucketsMAX[idx] = Math.max(i, bucketsMAX[idx]);
        }
        // scan the buckets for the max gap
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i = 0; i < num.length - 1; i++) {
            if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
                // empty bucket
                continue;
            // min value minus the previous value is the current gap
            maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
            // update previous bucket value
            previous = bucketsMAX[i];
        }
        maxGap = Math.max(maxGap, max - previous); // updata the final max value gap
        return maxGap;
    }

    @Test
    public void testBucket() {
        int[] num = new int[] {3,6,9,1};
        int maxGap = maximumGap_bucket(num);
        System.out.println(maxGap);
    }

    //[1,1,1,1,1,5,5,5,5,5]
    @Test
    public void testBucket2() {
        int[] num = new int[] {1,1,1,1,1,5,5,5,5,5};
        int maxGap = maximumGap_bucket(num);
        System.out.println(maxGap);
    }
}
