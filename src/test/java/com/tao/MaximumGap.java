package com.tao;

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

        int max = findMax(num);
        int min = findMin(num);

        int buckets = num.length - 1;
        double step = (max - min + 0.0) / buckets;

        // bucketize
        Map<Integer, List<Integer>> bucketMinMax = bucketize(num, min, step);

        // find empty bucket
        int lastMax = bucketMinMax.get(0).size() > 1 ? bucketMinMax.get(0).get(1) : bucketMinMax.get(0).get(0);
        int maxGap = lastMax - min;
        for (int i = 1; i < bucketMinMax.size(); i++) {
            List<Integer> list = bucketMinMax.get(i);
            if (list.size() > 0) {
                maxGap = Math.max(maxGap, list.get(0) - lastMax);
                lastMax = list.size() > 1 ? list.get(1) : list.get(0);
            }
        }
        return maxGap;
    }

    private Map<Integer, List<Integer>> bucketize(int[] num, int min, double step) {
        Map<Integer, List<Integer>> bucketMap = new HashMap<Integer, List<Integer>>();
        initBucketMap(bucketMap, num.length - 1);
        for (int i = 0; i < num.length; i++) {
            int ibucket = findBucketIndex(num[i], min, step);
            addToBucketMap(bucketMap, ibucket, num[i]);
        }
        return bucketMap;
    }

    private void addToBucketMap(Map<Integer, List<Integer>> bucketMap, int ibucket, int element) {
        List<Integer> list = bucketMap.get(ibucket);
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

    private void initBucketMap(Map<Integer, List<Integer>> bucketMap, int size) {
        for (int i = 0; i < size; i++) {
            bucketMap.put(i, new ArrayList<Integer>());
        }
    }

    private int findMin(int[] num) {
        int min = num[0];
        for (int i = 1; i < num.length; i++) {
            min = Math.min(min, num[i]);
        }
        return min;
    }

    private int findMax(int[] num) {
        int min = num[0];
        for (int i = 1; i < num.length; i++) {
            min = Math.max(min, num[i]);
        }
        return min;
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
