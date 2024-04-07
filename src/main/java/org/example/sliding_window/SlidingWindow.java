package org.example.sliding_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlidingWindow {
    // 3. Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;

        HashMap<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0, max = 0;
        while (r < s.length()) {
            char c = s.charAt(r);
            if (!map.containsKey(c) || map.get(c) == 0) {
                map.put(s.charAt(r), 1);
            } else {
                while (map.get(c) > 0) {
                    map.put(s.charAt(l), map.get(s.charAt(l)) - 1);
                    l++;
                }
                map.put(c, 1);
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }

    public int lengthOfLongestSubstringWayII(String s) {
        if (s.length() <= 1) return s.length();
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int l = 0, r = 0; r < s.length(); r++) {
            if (map.containsKey(s.charAt(r))) {
                l = Math.max(l, map.get(s.charAt(r)) + 1);
            }
            max = Math.max(max, r - l + 1);
            map.put(s.charAt(r), r);
        }
        return max;
    }

    // 30. Substring with Concatenation of All Words
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordLen = words[0].length();
        int windowLen = wordLen * words.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        int l = 0;
        while (l + windowLen <= s.length()) {
            if (checkSubString(s.substring(l, l + windowLen), map, wordLen)) res.add(l);
            l++;
        }
        return res;
    }

    private boolean checkSubString(String s, HashMap<String, Integer> map, int wordLen) {
        HashMap<String, Integer> seen = new HashMap<>();
        for (int j = 0; j < s.length(); j += wordLen) {
            String w = s.substring(j, j + wordLen);
            seen.put(w, seen.getOrDefault(w, 0) + 1);
        }
        return seen.equals(map);
    }

    // 76. Minimum Window Substring
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int m = s.length(), n = t.length();
        int l = 0, r = 0, min = Integer.MAX_VALUE, startIndex = 0;
        while (r < m) {
            if (map[s.charAt(r++)]-- > 0) {
                n--;
            }

            while (n == 0) {
                if (r - l < min) {
                    startIndex = l;
                    min = r - l;
                }

                if (map[s.charAt(l++)]++ == 0) {
                    n++;
                }
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + min);
    }

    // 209. Minimum Size Subarray Sum
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0] >= target ? 1 : 0;

        int ans = Integer.MAX_VALUE;
        int left = 0, sum = 0;

        for (int i = 0; i < n; i++) {
            sum = sum + nums[i];
            while (sum >= target) {
                ans = Math.min(ans, i - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
