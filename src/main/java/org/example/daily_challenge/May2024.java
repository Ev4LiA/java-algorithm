package org.example.daily_challenge;

import org.example.utilities.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
