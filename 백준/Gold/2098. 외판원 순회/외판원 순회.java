//package BOJ.외판원순회;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] g;
	static int[][] dp;
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		g = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][1<<N];
		for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);
		
		int result = dfs(0, 0, 1<<0);
		System.out.println(result);
	}
	
	public static int dfs(int node, int start, int mask) {
		if (mask == (1<<N)-1) {
			if(g[node][start] == 0) return INF;
			return g[node][start];
		}
		
		if(dp[node][mask] != -1) return dp[node][mask];
		
		int res = INF;
		for (int i=0; i<N; i++) {
			if((mask & (1 << i)) == 0 && g[node][i] != 0) {
				res = Math.min(res, dfs(i, start, mask|(1<<i)) + g[node][i]);
			}
		}
		
		return dp[node][mask] = res;
	}
}
