package org.example.contest.biweekly_contest;

import java.util.*;

public class Contest135 {
    // Find the Winning Player in Coin Game
    public String losingPlayer(int x, int y) {
        int max = Math.min(x, y / 4);
        return max % 2 == 1 ? "Alice" : "Bob";
    }

    // Minimum Length of String After Operations
    public int minimumLength(String s) {
        int n = s.length();
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }

        int remove = 0;
        for (int i = 0; i < 26; i++) {
            if (arr[i] < 3) continue;
            if (arr[i] % 2 == 1) {
                remove += arr[i] - 1;
            } else {
                remove += arr[i] - 2;
            }
        }
        return n - remove;
    }

    // Minimum Array Changes to Make Differences Equal
    public int minChanges(int[] nums, int k) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> vector = new ArrayList<>();
        for (int i = 0; i < nums.length / 2; i++) {
            int a = nums[i], b = nums[nums.length - i - 1];
            int diff = Math.abs(a - b);
            int x = Math.max(a, Math.max(b, Math.max(k - a, k - b)));
            map.computeIfAbsent(diff, v -> new ArrayList<>()).add(x);
            vector.add(x);
        }

        int ans = nums.length;
        Collections.sort(vector);


        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int diff = entry.getKey();
            int lowerBound = 0;

            for (int i = 0; i < vector.size(); i++) {
                if (vector.get(i) >= diff) {
                    lowerBound = i;
                    break;
                }
            }

            int t = Collections.binarySearch(vector, diff);

            if (t < 0) {
                t = -t - 1;
            }

            t = t * 2 + (nums.length / 2 - t);

            for (Integer x : entry.getValue()) {
                if (x < diff) {
                    t -= 2;
                } else {
                    t -= 1;
                }
            }
            ans = Math.min(ans, t);
        }
        return ans;
    }


//    map<int,vector<int>> m;
//    vector<int> v;
//        for(int i = 0;i<nums.size()/2;i++){
//        int a = nums[i],b = nums[nums.size()-i-1];
//        int diff = abs(a-b);
//        int X = max({a,b,k-a,k-b});
//        m[diff].push_back(X);
//        v.push_back(X);
//    }
//    int ans = nums.size();
//    sort(v.begin(),v.end());
//
//        for(auto &val:m){
//
//        int diff = val.first;
//        int T = lower_bound(v.begin(),v.end(),diff)-v.begin();
//        T = T*2+(nums.size()/2-T);
//
//        for(auto &X:val.second){
//            if(X<diff){
//                T-=2;
//            }
//            else{
//                T-=1;
//            }
//        }
//
//        ans = min(ans,T);
//
//    }
//
//        return ans;
}
