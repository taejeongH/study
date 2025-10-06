import java.util.*;
import java.io.*;
class Solution {
    int MOD = 20170805;
    int N, M;
    int[] dy = {0, 1};
    int[] dx = {1, 0};
    int[][] map;
    int[][][] dp;
    public int solution(int m, int n, int[][] cityMap) {
        N = m;
        M = n;
        map = cityMap;
        dp = new int[N][M][2];
        for (int i=0;i <N; i++) {
            for (int j=0; j<M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        if(N==1 && M==1) {
            return 1;
        }
        
        int answer = 0;
        if(N==1) {
            answer = dfs(0, 1, 1);
        } else if(M==1) {
            answer = dfs(1, 0, 1);
        } else{
            answer = (dfs(0, 1, 0) + dfs(1, 0, 1))%MOD;
        }
        return answer;
    }
    
    public int dfs(int y, int x, int dir) {
        if (y==N-1 && x==M-1) {
            return 1;
        }
        
        if(dp[y][x][dir]!=-1) return dp[y][x][dir];
        
        int res = 0;
        for (int i=0; i<2; i++) {
            int ny = y+dy[i];
            int nx = x+dx[i];
            
            if(ny>=0 && ny<N && nx>=0 && nx<M && map[ny][nx]!=1) {
                if (map[y][x]==2 && dir!=i) continue; 
                res += dfs(ny, nx, i);
            }
        }
        return dp[y][x][dir] = res%MOD;
    }
}