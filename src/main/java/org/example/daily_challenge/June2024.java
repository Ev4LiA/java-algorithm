package org.example.daily_challenge;

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
