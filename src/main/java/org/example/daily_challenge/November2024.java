package org.example.daily_challenge;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class November2024 {
    // 3254. Find the Power of K-Size Subarrays I
    public int[] resultsArray(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> indexDeque = new ArrayDeque<>();

        for (int currentIdx = 0; currentIdx < nums.length; currentIdx++) {
            if (!indexDeque.isEmpty() && indexDeque.peekFirst() < currentIdx - k + 1) {
                indexDeque.pollFirst();
            }

            if (!indexDeque.isEmpty() && nums[currentIdx] != nums[currentIdx - 1] + 1) {
                indexDeque.clear();
            }

            indexDeque.offerLast(currentIdx);
            if (currentIdx >= k - 1) {
                if (indexDeque.size() == k) {
                    res[currentIdx - k + 1] = nums[indexDeque.peekLast()];
                } else {
                    res[currentIdx - k + 1] = -1;
                }
            }
        }
        return res;
    }

    // 862. Shortest Subarray with Sum at Least K

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;

        // Initialize result to the maximum possible integer value
        int shortestSubarrayLength = Integer.MAX_VALUE;

        long cumulativeSum = 0;

        // Min-heap to store cumulative sum and its corresponding index
        PriorityQueue<Pair<Long, Integer>> prefixSumHeap = new PriorityQueue<>((a, b) -> Long.compare(a.getKey(), b.getKey()));

        // Iterate through the array
        for (int i = 0; i < n; i++) {
            // Update cumulative sum
            cumulativeSum += nums[i];

            // If cumulative sum is already >= k, update shortest length
            if (cumulativeSum >= k) {
                shortestSubarrayLength = Math.min(shortestSubarrayLength, i + 1);
            }

            // Remove subarrays from heap that can form a valid subarray
            while (!prefixSumHeap.isEmpty() && cumulativeSum - prefixSumHeap.peek().getKey() >= k) {
                // Update shortest subarray length
                shortestSubarrayLength = Math.min(shortestSubarrayLength, i - prefixSumHeap.poll().getValue());
            }

            // Add current cumulative sum and index to heap
            prefixSumHeap.offer(new Pair<>(cumulativeSum, i));
        }

        // Return -1 if no valid subarray found
        return shortestSubarrayLength == Integer.MAX_VALUE ? -1 : shortestSubarrayLength;
    }
}
