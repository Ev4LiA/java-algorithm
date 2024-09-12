package org.example.daily_challenge;

public class September2024 {
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
