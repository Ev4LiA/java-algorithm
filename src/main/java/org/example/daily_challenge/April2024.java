package org.example.daily_challenge;

public class April2024 {
    // 58. Length of Last Word
    public int lengthOfLastWord(String s) {
        String[] parts = s.split("\\s+");
        return parts[parts.length - 1].length();
    }
}
