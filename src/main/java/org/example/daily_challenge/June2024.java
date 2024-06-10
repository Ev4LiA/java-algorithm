package org.example.daily_challenge;

import org.example.utilities.TrieNode;

import java.util.*;

public class June2024 {
    // 198. House Robber
    public int rob(int[] nums) {
        int n = nums.length;
        int includeI = 0, excludeI = 0;

        for (int i = 0; i < n; i++) {
            int newExclude = Math.max(includeI, excludeI);
            includeI = excludeI + nums[i];
            excludeI = newExclude;
        }
        return Math.max(includeI, excludeI);
    }

    // 325.Maximum Size Subarray Sum Equals k
    // Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
    public int lenOfLongSubarr(int nums[], int k) {
        int n = nums.length, sum = 0, res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res = i - Math.max(res, map.get(sum - k));
            } else {
                map.put(sum, i);
            }
        }
        return res;
    }

    // 344. Reverse String
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }

    // 409. Longest Palindrome
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int result = 0;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) % 2 == 0) {
                result += 2;
            }
        }
        return result == s.length() ? result : result + 1;
    }

    // 523. Continuous Subarray Sum
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) return false;
        long sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int remainder = (int) sum % k;

            if (map.containsKey(remainder)) {
                int left = map.get(remainder);
                if (i - left > 1) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    // 560. Subarray Sum Equals K
    public int subarraySum(int[] nums, int k) {
        int sum = 0, res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    // 648. Replace Words
    public TrieNode root;

    public String replaceWords(List<String> dictionary, String sentence) {
        root = new TrieNode();
        for (String word : dictionary) {
            insertTrie(word);
        }

        StringBuilder sb = new StringBuilder();
        String[] arr = sentence.split(" ");
        for (String str : arr) {
            String res = searchWord(str);
            sb.append(res).append(" ");
        }
        return sb.toString().trim();
    }

    private void insertTrie(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.child[i] == null) {
                node.child[i] = new TrieNode();
            }
            node = node.child[i];
        }
        node.isEnd = true;
    }

    private String searchWord(String word) {
        TrieNode node = root;
        int j = 0;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            j++;
            if (node.child[i] == null) {
                return word;
            } else if (node.child[i].isEnd) {
                return word.substring(0, j);
            } else {
                node = node.child[i];
            }
        }
        return word;
    }

    // 846. Hand of Straights
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) return false;
        Arrays.sort(hand);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : hand) {
            if (map.get(num) > 0) {
                for (int i = 0; i < groupSize; i++) {
                    if (map.containsKey(num + i) && map.get(num + i) > 0) {
                        map.put(num + i, map.get(num + i) - 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 974. Subarray Sums Divisible by K
    public int subarraysDivByK(int[] nums, int k) {
        int remainder = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0;
        for (int num : nums) {
            remainder = (remainder + num) % k;
            if (remainder < 0) remainder = remainder + k;

            if (map.containsKey(remainder)) {
                res += map.get(remainder);
            }
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }
        return res;
    }

    // 1002. Find Common Characters
    public List<String> commonChars(String[] words) {
        List<String> result = new ArrayList<>();
        int[] last = count(words[0]);
        for (int i = 1; i < words.length; i++) {
            last = intersect(last, count(words[i]));
        }

        for (int i = 0; i < 26; i++) {
            while (last[i] > 0) {
                char a = (char) (i + 'a');
                result.add(String.valueOf(a));
                last[i]--;
            }
        }
        return result;
    }

    private int[] intersect(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] = Math.min(a[i], b[i]);
        }
        return a;
    }

    private int[] count(String word) {
        int[] arr = new int[26];
        for (char c : word.toCharArray()) {
            arr[c - 'a']++;
        }
        return arr;
    }

    // 1051. Height Checker
    public int heightChecker(int[] heights) {
        int[] expected = heights.clone();
        Arrays.sort(expected);
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != expected[i]) {
                res++;
            }
        }
        return res;
    }

    // 2486. Append Characters to String to Make Subsequence
    public int appendCharacters(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
            i++;
        }
        return t.length() - j;
    }

    // 3110. Score of a String
    public int scoreOfString(String s) {
        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            res += Math.abs(s.charAt(i) - s.charAt(i + 1));
        }
        return res;
    }
}
