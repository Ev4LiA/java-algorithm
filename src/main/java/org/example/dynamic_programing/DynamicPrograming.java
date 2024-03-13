package org.example.dynamic_programing;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class DynamicPrograming {
    // 5. Longest Palindromic Substring
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 1) return s;

        int maxLen = 1;
        int start = 0, end = 0;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;

            for (int j = 0; j < i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[i - 1][j + 1] == 1)) {
                    dp[i][j] = 1;

                    if (i - j + 1 >= maxLen) {
                        maxLen = i - j + 1;
                        start = j;
                        end = i;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

    // 62. Unique Paths
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i - 1 < 0 && j - 1 < 0) {
                    dp[i][j] = 1;
                } else if (j - 1 < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else if (i - 1 < 0) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // 63. Unique Paths II
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = -1;
                } else if (i - 1 < 0 && j - 1 < 0) {
                    dp[i][j] = 1;
                } else if (j - 1 < 0) {
                    if (dp[i - 1][j] != -1) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = -1;
                    }
                } else if (i - 1 < 0) {
                    if (dp[i][j - 1] != -1) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
                        dp[i][j] = -1;
                    }
                } else {
                    if (dp[i - 1][j] == -1 && dp[i][j - 1] == -1) {
                        dp[i][j] = -1;
                    } else if (dp[i - 1][j] == -1) {
                        dp[i][j] = dp[i][j - 1];
                    } else if (dp[i][j - 1] == -1) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
        }
        return dp[rows - 1][cols - 1] == -1 ? 0 : dp[rows - 1][cols - 1];
    }

    // 64. Minimum Path Sum
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i - 1 < 0 && j - 1 < 0) {
                    dp[i][j] = grid[i][j];
                } else if (j - 1 < 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (i - 1 < 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[rows - 1][cols - 1];
    }

    // 72. Edit Distance
    public int minDistance(String word1, String word2) {
        if (word1.isEmpty() || word2.isEmpty()) return Math.max(word1.length(), word2.length());
        if (word1.equals(word2)) return 0;
        int n = word1.length(), m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[n][m];
    }

    // 115. Distinct Subsequences
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[n + 1][m + 1];

        // An empty string can not contain another string
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 0;
        }

        // Every string contain an empty string one time
        for (int j = 0; j <= m; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    // 120. Triangle
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) return triangle.get(0).get(0);
        int rows = triangle.size();
        int[] dp = new int[rows + 1];
        Arrays.fill(dp, 0);
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }

        return dp[0];
    }


    // 139. Word Break
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, 0);

        for (int i = 0; i < n; i++) {
            for (String word : wordDict) {
                if (i - word.length() + 1 < 0) {
                    continue;
                }

                if (s.substring(i - word.length() + 1, i + 1).equals(word)) {
                    if (i - word.length() + 1 == 0 || (i - word.length() + 1 > 0 && dp[i - word.length()] == 1)) {
                        dp[i] = 1;
                        break;
                    }
                }
            }
        }
        return dp[n - 1] == 1;
    }

    //  198. House Robber
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        int beforePrev = nums[0];
        int prev = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int cur = Math.max(beforePrev + nums[i], prev);
            beforePrev = prev;
            prev = cur;
        }
        return prev;
    }


    // 221. Maximal Square
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dp[i][j] = 0;
            }
        }
        int max = Integer.MIN_VALUE;

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i + 1][j + 1], dp[i][j + 1]), dp[i + 1][j]) + 1;
                    max = Math.max(dp[i][j], max);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max * max;
    }

    // 516. Longest Palindromic Subsequence
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if (n == 1) return s.length();

        int[] dp = new int[n];
        int max = 0;

        for (int j = 0; j < n; j++) {
            dp[j] = 1;
            max = 0;
            for (int i = j - 1; i >= 0; i--) {
                int len = dp[i];
                if (s.charAt(i) == s.charAt(j))
                    dp[i] = 2 + max;

                max = Math.max(max, len);
            }
        }
        for (int len : dp)
            max = Math.max(max, len);
        return max;
    }

    // 712. Minimum ASCII Delete Sum for Two Strings
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;

        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }
        return dp[m][n];
    }

    // 740. Delete and Earn
    public int deleteAndEarn(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int[] dp = new int[max + 1];
        Arrays.fill(dp, 0);
        for (int num : nums) {
            dp[num] += num;
        }

        if (dp.length == 1) return dp[0];
        if (dp.length == 2) return Math.max(dp[0], dp[1]);

        int beforePrev = dp[0], prev = Math.max(dp[0], dp[1]);

        for (int i = 2; i < dp.length; i++) {
            int cur = Math.max(beforePrev + dp[i], prev);
            beforePrev = prev;
            prev = cur;
        }
        return prev;
    }

    // 931. Minimum Falling Path Sum
    public int minFallingPathSum(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (rows == 1 && cols == 1) return matrix[0][0];

        int[] dp = new int[cols];

        for (int i = 0; i < cols; i++) {
            dp[i] = matrix[rows - 1][i];
        }

        for (int i = rows - 2; i >= 0; i--) {
            int[] temp = Arrays.copyOf(dp, dp.length);
            for (int j = 0; j < cols; j++) {
                if (j - 1 < 0) {
                    dp[j] = Math.min(temp[j], temp[j + 1]) + matrix[i][j];
                } else if (j + 1 >= cols) {
                    dp[j] = Math.min(temp[j], temp[j - 1]) + matrix[i][j];
                } else {
                    dp[j] = Math.min(Math.min(temp[j], temp[j + 1]), temp[j - 1]) + matrix[i][j];
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int ele : dp) {
            res = Math.min(res, ele);
        }
        return res;
    }

    // 1143. Longest Common Subsequence
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = 0;
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
