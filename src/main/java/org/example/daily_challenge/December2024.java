package org.example.daily_challenge;

public class December2024 {
    // 2109. Adding Spaces to a String
    public String addSpaces(String s, int[] spaces) {
        StringBuilder result = new StringBuilder();
        int spaceIndex = 0;

        for (int stringIndex = 0; stringIndex < s.length(); stringIndex++) {
            if (
                    spaceIndex < spaces.length && stringIndex == spaces[spaceIndex]
            ) {
                // Insert space at the correct position
                result.append(' ');
                spaceIndex++;
            }
            // Append the current character
            result.append(s.charAt(stringIndex));
        }
        return result.toString();
    }

}
