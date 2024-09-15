package org.example.daily_challenge;

import java.util.Arrays;

public class September2024 {
    // 1371. Find the Longest Substring Containing Vowels in Even Counts
    public int findTheLongestSubstring(String s) {
        int prefixXor = 0;
        int[] charMap = new int[26];
        charMap[0] = 1;
        charMap['e' - 'a'] = 2;
        charMap['i' - 'a'] = 4;
        charMap['o' - 'a'] = 8;
        charMap['u' - 'a'] = 16;
        int[] mp = new int[32];
        Arrays.fill(mp, -1);
        int longestSubstring = 0;
        for (int i = 0; i < s.length(); i++) {
            prefixXor ^= charMap[s.charAt(i) - 'a'];
            if (mp[prefixXor] == -1 && prefixXor != 0) mp[prefixXor] = i;
            longestSubstring = Math.max(longestSubstring, i - mp[prefixXor]);
        }
        return longestSubstring;
    }

    // 1684. Count the Number of Consistent Strings
    public int countConsistentStrings(String allowed, String[] words) {
        int[] alphabets = new int[26];
        int count = 0;
        for (char c : allowed.toCharArray()) {
            alphabets[c - 'a']++;
        }
        for (String word : words) {
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if (alphabets[c - 'a'] == 0) {
                    flag = false;
                    break;
                }
            }
            count += flag ? 1 : 0;
        }
        return count;
    }
}
