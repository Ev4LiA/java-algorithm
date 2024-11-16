package org.example.daily_challenge;

import java.util.ArrayDeque;
import java.util.Deque;

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
}
