package org.example.string;

import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StringAlgorithm {
    // 6. Zigzag Conversion
    public String convert(String s, int numRows) {
        String[] arr = new String[numRows];
        Arrays.fill(arr, "");
        int i = 0, n = s.length();
        while (i < n) {
            for (int row = 0; row < numRows && i < n; row++) {
                arr[row] += s.charAt(i);
                i++;
            }
            for (int row = numRows - 2; row > 0 && i < n; row--) {
                arr[row] += s.charAt(i);
                i++;
            }
        }

        String res = "";
        for (String a : arr) {
            res += a;
        }
        return res;
    }

    // 12. Integer to Roman
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (num > 0) {
            if (num >= values[i]) {
                sb.append(roman[i]);
                num -= values[i];
            } else {
                i++;
            }
        }
        return sb.toString();
    }

    // 13. Roman to Integer
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0, prevValue = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int a = map.get(s.charAt(i));
            if (a >= prevValue) {
                res += a;
            } else {
                res -= a;
            }
            prevValue = a;
        }
        return res;
    }

    // 14. Longest Common Prefix
    public String longestCommonPrefix(String[] strs) {
        String result = "";
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                return result;
            } else {
                result = result + first.charAt(i);
            }
        }
        return result;
    }

    // 28. Find the Index of the First Occurrence in a String
    public int strStr(String haystack, String needle) {
        char a = needle.charAt(0);
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.charAt(i) == a) {
                String s = haystack.substring(i, i + needle.length());
                if (s.equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    // 58. Length of Last Word
    public int lengthOfLastWord(String s) {
        boolean flag = false;
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ' && flag) {
                return count;
            } else if (s.charAt(i) == ' ') {
                continue;
            } else {
                count++;
                flag = true;
            }
        }
        return count;
    }

    // 68. Text Justification
    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;

        List<String> ans = new ArrayList<>();
        int start=0;
        while(start<n){
            int[] idxSum = decideLine(words, start, maxWidth, n);
            int end = idxSum[0];
            int cumSum = idxSum[1];
            ans.add(createLine(words, start, end, cumSum, maxWidth, n));
            start = end+1;
        }
        return ans;
    }
    public int[] decideLine(String[] words, int start, int maxWidth, int n){
        int cumSum = 0, spaces = 0;
        int end = start;

        while(end<n){
            cumSum += words[end].length();
            if(cumSum + spaces > maxWidth){
                return new int[]{end-1, cumSum - words[end].length()};
            }
            spaces++; // for space
            end++;
        }
        return new int[]{end-1, cumSum};
    }
    public String createLine(String[] words, int start, int end, int cumSum, int maxWidth, int n){

        int spaces = maxWidth - cumSum;
        int wordSpaces = end - start;
        int reqSpaces = (end == n-1 || end==start)? 0 : spaces / wordSpaces;

        StringBuilder spaceWord = new StringBuilder();
        while(reqSpaces>0){
            spaceWord.append(' ');
            reqSpaces--;
        }

        int extraSpacing = (end == n-1 || end==start)? spaces : spaces % wordSpaces;

        StringBuilder sb = new StringBuilder();

        while(sb.length() !=  maxWidth){

            if(start <= end)
                sb.append(words[start]);

            if(start++ < end)
                sb.append(spaceWord);//only if the word is not the end

            if(extraSpacing>0){
                sb.append(' ');//adding the extra spaces!
                extraSpacing--;
            }
        }
        return sb.toString();
    }

    // 125. Valid Palindrome
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) return true;
        int start = 0;
        int end = s.length() - 1;
        while (start <= end) {
            Character first = s.charAt(start);
            Character last = s.charAt(end);
            if (!Character.isLetterOrDigit(first)) {
                start++;
            } else if (!Character.isLetterOrDigit(last)) {
                end--;
            } else {
                if (Character.toLowerCase(first) == Character.toLowerCase(last)) {
                    start++;
                    end--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    // 151. Reverse Words in a String
    public String reverseWords(String s) {
        String[] str = s.trim().split("\\s+");

        StringBuilder res = new StringBuilder();
        for (int i = str.length - 1; i > 0; i--) {
            res.append(str[i]).append(" ");
        }
        res.append(str[0]);
        return res.toString();
    }

    // 977. Squares of a Sorted Array
    public int[] sortedSquares(int[] nums) {
        int i = 0, j = nums.length - 1, k = nums.length - 1;
        int[] res = new int[nums.length];
        while (k >= 0) {
            if (nums[j] > Math.abs(nums[i])) {
                res[k] = nums[j] * nums[j];
                j--;
            } else {
                res[k] = nums[i] * nums[i];
                i++;
            }
            k--;
        }
        return res;
    }

    // 1071. Greatest Common Divisor of Strings
    public String gcdOfStrings(String str1, String str2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb1.append(str1).append(str2);
        sb2.append(str2).append(str1);

        if (sb1.toString().contentEquals(sb2)) {
            return str1.substring(0, gcd(str1.length(), str2.length()));
        } else {
            return "";
        }
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 1929. Concatenation of Array
    public int[] getConcatenation(int[] nums) {
        int[] result = new int[nums.length * 2];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i];
            result[i + nums.length] = nums[i];
        }
        return result;
    }

    public int[] getConcatenation2ndMethod(int[] nums) {
        int[] ans = new int[nums.length * 2];
        System.arraycopy(nums, 0, ans, 0, nums.length);
        System.arraycopy(nums, 0, ans, nums.length, nums.length);
        return ans;
    }
}
