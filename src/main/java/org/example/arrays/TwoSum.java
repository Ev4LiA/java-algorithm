package org.example.arrays;

import java.util.HashMap;

public class TwoSum {
    public int[] solve(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int minus = target - nums[i];
            if (map.containsKey(minus)) {
                return new int[] {map.get(minus), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
