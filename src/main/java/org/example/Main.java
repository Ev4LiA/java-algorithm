package org.example;

import org.example.contest.weekly_contest.Contest401;
import org.example.contest.weekly_contest.Contest406;
import org.example.contest.weekly_contest.Contest407;
import org.example.daily_challenge.July2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Contest407 problem = new Contest407();
//        List<List<Integer>> nums = new ArrayList<>();
//        nums.add(Arrays.asList(2));
//        nums.add(Arrays.asList(3, 4));
//        nums.add(Arrays.asList(6, 5, 7));
//        nums.add(Arrays.asList(4, 1, 8, 3));
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        int[] heights = {0, 1, 0, 0, 0, 0};
        String order = "cba";
        String s = "abcd";

        int[] freq = {5, 2};
        String s1 = "xaxcd";
        char c = 't';
//        int[] nums = {10, 9, 7};
//        int[][] arrays = {{1,0,0},{0,1,1},{0,1,1}};
        int[][] arrays = {{1, 1, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0}};
        s = "aaAbcBC";
        String t = "(*))";
        int[][] edges = {{2, 2}, {-1, -2}, {-4, 4}, {-3, 1}, {3, -3}};

        int[] nums1 = {4, 6, 3, 1, 4, 2, 10, 9, 5};
        int[] nums2 = {5, 10, 3, 2, 6, 1, 9};

//        int[] nums1 = {4,20,16,12,8};
//        int[] nums2 = {14,18,10};

        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(9, 5, 7, 3));
        grid.add(Arrays.asList(8, 9, 6, 1));
        grid.add(Arrays.asList(6, 7, 14, 3));
        grid.add(Arrays.asList(2, 5, 3, 1));

        int[] nums = {2, 4, 6};
        int[][] querries = {{0, 1}};
        System.out.println( problem.maxOperations("01"));
//        System.out.println(3 | 3);

        // System out Array
        System.out.print("Array elements: \n");
//        List<Integer> res = problem.luckyNumbers(new int[][]{{1,10,4,2},{9,3,8,7},{15,16,17,12}});
//        for (int i = 0; i < res.size(); i++) {
//            System.out.print(res.get(i) + " ");
//        }

//        for (int i = 0; i < res.length; i++) {
//            for (int j = 0; j < res[0].length; j++) {
//                System.out.print(res[i][j] + " ");
//            }
//            System.out.println("\n");
//        }
    }
}
