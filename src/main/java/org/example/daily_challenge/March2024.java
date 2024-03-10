package org.example.daily_challenge;

import org.example.linked_list.ListNode;

import java.util.*;

public class March2024 {
    // 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }
        return false;
    }

    // 349. Intersection of Two Arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersection = new HashSet<>();

        for (int num : nums1) {
            set.add(num);
        }

        for (int num : nums2) {
            if (set.contains(num)) {
                intersection.add(num);
            }
        }

        int[] res = new int[intersection.size()];
        int i = 0;
        for (Integer num : intersection) {
            res[i++] = num;
        }
        return res;
    }

    // 876. Middle of the Linked List
    public ListNode middleNode(ListNode head) {
        if (head.next == null) return head;
        ListNode fast = head, slow = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 948. Bag of Tokens
    public static int bagOfTokensScore(int[] tokens, int power) {
        int n = tokens.length;
        if (n == 0) return 0;

        Arrays.sort(tokens);

        if (power < tokens[0]) return 0;
        int start = 0, end = n - 1, score = 0;
        while (start <= end) {
            if (power >= tokens[start]) {
                power -= tokens[start];
                score++;
                start++;
            } else if (score >= 0) {
                if (end == start) {
                    return score;
                } else {
                    power += tokens[end];
                    score--;
                    end--;
                }
            } else {
                return score;
            }
        }
        return score;
    }

    // 1750. Minimum Length of String After Deleting Similar Ends
    public int minimumLength(String s) {
        int n = s.length();
        if (n == 1) return 1;
        int start = 0, end = n - 1;

        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                char same = s.charAt(start);
                while (start <= end && s.charAt(start) == same) {
                    start++;
                }
                while (start <= end && s.charAt(end) == same) {
                    end--;
                }
            } else {
                break;
            }
        }
        return end - start + 1;
    }

    // 2540. Minimum Common Value
    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                return nums1[i];
            }
        }
        return -1;
    }

    // 3005. Count Elements With Maximum Frequency
    public int maxFrequencyElements(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = Integer.MIN_VALUE;
        int count = 0;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > max) {
                count = 1;
                max = map.get(num);
            } else if (map.get(num) == max) {
                count++;
            }
        }
        return count * max;
    }
}
