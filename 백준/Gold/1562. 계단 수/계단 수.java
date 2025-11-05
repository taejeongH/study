//package BOJ.계단수;

import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 1_000_000_000;
	static int N;
	static int[][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int M = (1<<10);
		dp = new int[M][N][10];
		
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) Arrays.fill(dp[i][j], -1); 
		}
		
		System.out.println(dfs(0, 0, 0));
	}
	
	public static int dfs(int mask, int depth, int prev) {
		if(depth == N) {
			if (mask == (1 << 10) - 1) return 1;
			return 0;
		}
		
		if(dp[mask][depth][prev]!=-1) return dp[mask][depth][prev];
		long res = 0;
		
		if(depth==0) {
			for (int i=1; i<=9; i++) {
				res = (res + dfs(1<<i, depth+1, i)) % MOD;
			}
		} else {
			if(prev+1<=9) {
				res = (res + dfs(mask | (1<<(prev+1)), depth+1, prev+1)) % MOD;
			}
			
			if(prev-1>-1) {
				res = (res + dfs(mask | (1<<(prev-1)), depth+1, prev-1)) % MOD;
			}
		}
		
		return dp[mask][depth][prev] = (int) res % MOD;
	}
}

/*
	0~9까지 모든 숫자가 나와야 함.
	
	dfs-> 

*/ 
