package org.example.daily_challenge;

import org.example.utilities.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class July2024 {
    // 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
    public int minDifference(int[] nums) {
        Arrays.sort(nums);
        int max = Integer.MAX_VALUE, n = nums.length;
        if (n <= 3) return 0;
        max = Math.min(max, nums[n - 1] - nums[3]);
        max = Math.min(max, nums[n - 2] - nums[2]);
        max = Math.min(max, nums[n - 3] - nums[1]);
        max = Math.min(max, nums[n - 4] - nums[0]);
        return max;
    }

    // 1550. Three Consecutive Odds
    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int num : arr) {
            if (num % 2 == 1) {
                count++;
            } else {
                count = 0;
            }
            if (count == 3) return true;
        }
        return false;
    }

    // 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode dummy = head.next;
        int prevVal = head.val, index = 1;
        int[] res = new int[2];
        List<Integer> critPoints = new ArrayList<>();
        while (dummy.next != null) {
            if (dummy.val < prevVal && dummy.val < dummy.next.val) {
                critPoints.add(index);
            } else if (dummy.val > prevVal && dummy.val > dummy.next.val) {
                critPoints.add(index);
            }
            prevVal = dummy.val;
            dummy = dummy.next;
            index++;
        }
        int min = Integer.MAX_VALUE, max = -1;
        for (int i = 1; i < critPoints.size(); i++) {
            int dist = critPoints.get(i) - critPoints.get(i - 1);
            min = Math.min(min, dist);
        }

        if (min == Integer.MAX_VALUE) {
            res[0] = -1;
        } else {
            res[0] = min;
        }
        if (critPoints.size() > 1){
            max = critPoints.get(critPoints.size() - 1) - critPoints.get(0);
        }
        res[1] = max;
        return res;
    }

    // 2181. Merge Nodes in Between Zeros
    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = head.next;
        while (dummy != null) {
            int sum = 0;
            ListNode temp = dummy;
            while (temp.val != 0) {
                sum += temp.val;
                temp = temp.next;
            }
            dummy.val = sum;
            dummy.next = temp.next;
            dummy = dummy.next;
        }
        return head.next;
    }

    // 2582. Pass the Pillow
    public int passThePillow(int n, int time) {

    }
}
