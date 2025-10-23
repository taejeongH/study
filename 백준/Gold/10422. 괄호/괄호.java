//package BOJ.괄호;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] dp;
	static final int MOD = 1_000_000_007;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			N = Integer.parseInt(br.readLine());
			dp = new int[N/2+1][N/2+1];
			for (int i=0; i<N/2+1; i++) Arrays.fill(dp[i], -1);
			sb.append(dfs(0, 0)).append("\n");
		}
		System.out.println(sb);
	}
	
	
	public static int dfs(int open, int close) {
		if (open+close == N && open==close) {
			return 1;
		}
		
		if(dp[open][close]!=-1) return dp[open][close];
		
		long res = 0;
		if (open < N/2) {
			res += dfs(open+1, close);
		}
		
		if (open > close && close < N/2) {
			res += dfs(open, close+1);
		}
		
		return dp[open][close]= (int) (res % MOD);
	}
}
