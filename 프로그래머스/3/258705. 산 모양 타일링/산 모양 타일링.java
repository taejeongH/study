import java.io.*;
import java.util.*;

class Solution {
    int[][] map;
    int N;
    int MOD = 10007;
    int[] dp;
    public int solution(int n, int[] tops) {
        int answer = 0;
        N = 2*n+1;
        map = new int[2][N];
        for (int i=1; i<N; i+=2) {
            map[0][i] = tops[i/2];
        }
        Arrays.fill(map[1], 1);
        
        dp = new int[N];
        Arrays.fill(dp, -1);

        return dfs(0);
    }
    
    public int dfs(int idx) {
        if (idx==N) {
            return 1;
        }
        if(dp[idx]!=-1) return dp[idx];
        
        int res = dfs(idx+1);
        
        if (idx!=N-1) {
            res += dfs(idx+2);
        }
        
        if(map[0][idx]==1) {
            res += dfs(idx+1);
        }
        
        return dp[idx]=res%MOD;
    }
    
    
}