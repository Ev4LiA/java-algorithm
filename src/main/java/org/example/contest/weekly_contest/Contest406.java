package org.example.contest.weekly_contest;

import org.example.linked_list.ListNode;

import java.util.*;
import java.util.stream.Collectors;

public class Contest406 {
    // Lexicographically Smallest String After a Swap
    public String getSmallestString(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            if (((arr[i] - '0') % 2 == (arr[i + 1] - '0') % 2) && (arr[i] > arr[i + 1])) {
                char temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
                break;
            }
        }
        return String.valueOf(arr);
    }

    // Delete Nodes From Linked List Present in Array
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());

        ListNode base = new ListNode(0, head);
        ListNode prev = base;
        ListNode curr = head;
        while (curr != null) {
            if (set.contains(curr.val)) {
                prev.next = curr.next;
                curr = curr.next;

            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return base.next;
    }

    // Minimum Cost for Cutting Cake I
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);

        int hPieces = 1, vPieces = 1, i = horizontalCut.length - 1, j = verticalCut.length - 1, totalCost = 0;
        while (i >= 0 && j >= 0) {
            if (horizontalCut[i] > verticalCut[j]) {
                totalCost += horizontalCut[i] * vPieces;
                i--;
                hPieces++;
            } else {
                totalCost += verticalCut[j] * hPieces;
                j--;
                vPieces++;
            }
        }

        while (i >= 0) {
            totalCost += horizontalCut[i] * vPieces;
            i--;
        }

        while (j >= 0) {
            totalCost += verticalCut[j] * hPieces;
            j--;
        }
        return totalCost;
    }

    // Minimum Cost for Cutting Cake II
    public long minimumCostII(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);

        int  i = horizontalCut.length - 1, j = verticalCut.length - 1;
        long hPieces = 1, vPieces = 1, totalCost = 0;
        while (i >= 0 && j >= 0) {
            if (horizontalCut[i] > verticalCut[j]) {
                totalCost += horizontalCut[i] * vPieces;
                i--;
                hPieces++;
            } else {
                totalCost += verticalCut[j] * hPieces;
                j--;
                vPieces++;
            }
        }

        while (i >= 0) {
            totalCost += horizontalCut[i] * vPieces;
            i--;
        }

        while (j >= 0) {
            totalCost += verticalCut[j] * hPieces;
            j--;
        }
        return totalCost;
    }
}
