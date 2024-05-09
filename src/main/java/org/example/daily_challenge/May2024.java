package org.example.daily_challenge;

import org.example.utilities.ListNode;

import java.util.*;

public class May2024 {
    // 165. Compare Version Numbers
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < Math.max(v1.length, v2.length); i++) {
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (num1 < num2) {
                return -1;
            }
            if (num1 > num2) {
                return 1;
            }
        }
        return 0;
    }

    // 237. Delete Node in a Linked List
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // 506. Relative Ranks
    public String[] findRelativeRanks(int[] score) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        for (int i = 0; i < score.length; i++) {
            queue.add(new int[]{i, score[i]});
        }
        int count = 0;
        String[] res = new String[score.length];
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            if (count == 0) {
                res[arr[0]] = "Gold Medal";
            } else if (count == 1) {
                res[arr[0]] = "Silver Medal";
            } else if (count == 2) {
                res[arr[0]] = "Bronze Medal";
            } else {
                res[arr[0]] = String.valueOf(count + 1);
            }
            count++;
        }
        return res;
    }

    // 881. Boats to Save People
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        for (; i <= j; j--) {
            if (people[i] + people[j] <= limit) i++;
        }
        return people.length - 1 - j;
    }

    // 2000. Reverse Prefix of Word
    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index == -1) {
            return word;
        } else {
            StringBuilder sb = new StringBuilder();
            String sub = sb.append(word, 0, index + 1).reverse().toString();
            return sub + word.substring(index + 1);
        }
    }

    // 2441. Largest Positive Integer That Exists With Its Negative
    public int findMaxK(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        while (nums[i] < 0 && i < j) {
            if (nums[j] == -nums[i]) {
                return nums[j];
            } else if (nums[j] > -nums[i]) {
                j--;
            } else {
                i++;
            }
        }
        return -1;
    }

    // 2487. Remove Nodes From Linked List
    public ListNode removeNodes(ListNode head) {
        List<Integer> valList = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            valList.add(curr.val);
            curr = curr.next;
        }

        boolean[] deleted = new boolean[valList.size()];
        int maxValue = Integer.MIN_VALUE;
        for (int j = valList.size() - 1; j >= 0; j--) {
            if (valList.get(j) >= maxValue) {
                maxValue = valList.get(j);
                deleted[j] = false;
            } else {
                deleted[j] = true;
            }
        }

        ListNode fakeHead = new ListNode(10000, head);
        ListNode prev = fakeHead;
        curr = head;
        int i = 0;
        while (curr != null) {
            if (deleted[i]) {
                curr = curr.next;
                prev.next = curr;
            } else {
                prev = prev.next;
                curr = curr.next;
            }
            i++;
        }
        return fakeHead.next;
    }

    // 2816. Double a Number Represented as a Linked List
    public ListNode doubleIt(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode curr = head;
        int val = 0;
        while(curr != null) {
            stack.push(curr.val);
            curr = curr.next;
        }

        ListNode newTail = null;
        while (!stack.isEmpty() || val != 0) {
            newTail = new ListNode(0, newTail);

            if (!stack.isEmpty()) {
                val += stack.pop() * 2;
            }
            newTail.val = val % 10;
            val /= 10;
        }
        return newTail;
    }

    // 3075. Maximize Happiness of Selected Children
    public long maximumHappinessSum(int[] happiness, int k) {
        long res = 0;
        int c = 0, n = happiness.length;
        Arrays.sort(happiness);

        for (int i = n - 1; i >= n - k; i--) {
            int value = happiness[i] - c++;
            if (value > 0) {
                res += value;
            }
        }
        return res;
    }
}
