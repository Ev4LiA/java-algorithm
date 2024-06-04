package org.example.contest.weekly_contest.contest_300.contest_390;

import org.example.utilities.TrieNode;

import java.util.*;

public class Contest390 {
    // 3090. Maximum Length Substring With Two Occurrences
    //      + Brute force
    public int maximumLengthSubstring(String s) {
        int[] word = new int[26];
        int res = Integer.MIN_VALUE, strLength = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                int num = s.charAt(j) - 'a';
                if (word[num] == 2) {
                    strLength = j - i;
                    break;
                } else {
                    word[num]++;
                }

                if (j == s.length() - 1) {
                    strLength = j - i + 1;
                }
            }

            res = Math.max(res, strLength);
            Arrays.fill(word, 0);
        }
        return res;
    }

    //         _ Sliding Window
    public int maximumLengthSubstringSlidingWindow(String s) {
        int n = s.length();
        int res = Integer.MIN_VALUE, start = 0, end = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (end < n) {
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0) + 1);
            while (map.get(s.charAt(end)) >= 3) {
                map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                start++;
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }

    // ------------------------------ ########## ------------------------------ //
    // 3091. Apply Operations to Make Sum of Array Greater Than or Equal to k
    public int minOperations(int k) {
        if (k == 1) return 0;
        int minSum = Integer.MAX_VALUE;

        for (int a = 0; a <= k; a++) {
            int minB = (k + a) / (a + 1);
            int ab = (a + 1) * minB;
            if (ab >= k) {
                minSum = Math.min(minSum, a + minB - 1);
            }
        }
        return minSum;
    }

    //      + Calculus Solution
    /*
            (1 + a) * (b + 1) >= k
            For same value of a + b, to make ab biggest => a = b
            a = b => (b + 1) * (b + 1) >= k
            a = b + 1 => (1 + a) * a >= k
     */
    public int minOperationsCalculus(int k) {
        int a = (int) Math.sqrt(k);
        return a + (k - 1) / a - 1;
    }

    // ------------------------------ ########## ------------------------------ //
    // 3092. Most Frequent IDs
    /*
           A Hash Map to store Id to freq
           A Tree Map to store freq to Set<Id>
           A Tree Map is a map that has key sorted (Ascending Order)
     */
    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        int n = nums.length;
        long[] res = new long[n];
        HashMap<Integer, Long> map = new HashMap<>();
        TreeMap<Long, Set<Integer>> treeMap = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int id = nums[i], count = freq[i];
            long oldCount = map.getOrDefault(id, (long) 0);
            map.put(id, oldCount + count);

            if (oldCount != 0) {
                treeMap.get(oldCount).remove(id);
                // Empty set
                if (treeMap.get(oldCount).isEmpty()) {
                    treeMap.remove(oldCount);
                }
            }

            if (!treeMap.containsKey(oldCount + count)) {
                treeMap.put(oldCount + count, new HashSet<>());
            }
            treeMap.get(oldCount + count).add(id);
            res[i] = treeMap.lastKey();
        }
        return res;
    }

    // ------------------------------ ########## ------------------------------ //
    // 3093. Longest Common Suffix Queries
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        int[] res = new int[wordsQuery.length];
        TrieNode head = new TrieNode(0);

        for (int i = 0; i < wordsContainer.length; i++) {
            if (wordsContainer[head.ind].length() > wordsContainer[i].length()) head.ind = i;
            addNode(head, wordsContainer, i);
        }

        for (int i = 0; i < wordsQuery.length; i++) res[i] = search(head, wordsQuery[i]);
        return res;
    }

    public void addNode(TrieNode ptr, String[] wordsContainer, int i) {
        for (int j = wordsContainer[i].length() - 1; j >= 0; --j) {
            int c = wordsContainer[i].charAt(j) - 'a';
            if (ptr.child[c] == null) ptr.child[c] = new TrieNode(i);
            ptr = ptr.child[c];
            if (wordsContainer[ptr.ind].length() > wordsContainer[i].length()) ptr.ind = i;
        }
    }

    public int search(TrieNode ptr, String s) {
        int ans = ptr.ind;
        for (int i = s.length() - 1; i >= 0; --i) {
            ptr = ptr.child[s.charAt(i) - 'a'];
            if (ptr == null) return ans;
            ans = ptr.ind;
        }
        return ans;
    }
}
