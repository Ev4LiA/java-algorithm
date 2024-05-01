package org.example.daily_challenge;

public class May2024 {
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
}
