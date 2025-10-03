import java.util.*;
class Solution {
    int N, n, m;
    int INF = Integer.MAX_VALUE;
    int[][] info;
    int[][][] dp;
    public int solution(int[][] info, int n, int m) {
        N = info.length;
        this.n = n;
        this.m = m;
        this.info = info;
        
        
        dp = new int[info.length][121][121];
        for (int i=0; i<dp.length; i++) {
            for (int j=0; j<121; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int res = dfs(0, 0, 0);
        
        return res==INF?-1:res;
    }
    
    
    public int dfs(int idx, int aSum, int bSum) {
        if (idx == N) {
            return aSum;
        }
        
        if(dp[idx][aSum][bSum]!=-1) return dp[idx][aSum][bSum];
        
        int res = INF;
        if(aSum+info[idx][0] < n) {
            res = Math.min(dfs(idx+1, aSum+info[idx][0], bSum), res);
        }
        
        if (bSum+info[idx][1] < m) {
            res = Math.min(dfs(idx+1, aSum, bSum+info[idx][1]), res);
        }
        
        return dp[idx][aSum][bSum]=res;
    }
}

/*
    A도둑이랑 B도둑이랑 팀을 이루어 물건을 훔치는데, 흔적을 최소화해야함
    
    물건 I를 훔칠 때,
        A도둑이 훔치면 info[i][0]개의 A에 대한 흔적을 남김
        B도둑이 훔치면 info[i][1]개의 B에 대한 흔적을 남김
    
    A도둑이 n개 이상 남기면 붙잡히고, B도둑이 m개 이상 남기면 붙잡힘
    
    두 도둑 모두 경찰에 붙잡히지 않도록 물건을 훔쳤을 때, A도둑이 남긴 흔적의 누적 개수의 최솟값을 return (만약 두 도둑모두 경찰에 붙잡힐 수밖에 없다면 -1 return)
    
    
    
*/