//package BOJ.알약;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			dp = new long[N+1][N+1];
			for (int i=0; i<N+1; i++) Arrays.fill(dp[i], -1);
			sb.append(dfs(0, 0)).append("\n");
		}
		
		
		System.out.println(sb);
	}
	
	public static long dfs(int W, int H) {
		if(W==N && H==N) return 1;
		if(dp[W][H]!=-1) return dp[W][H];
		
		long res = 0;
		if(W<N) {
			res += dfs(W+1, H);
		}
		if(H < W && H < N) {
			res += dfs(W, H+1);
		}
		
		return dp[W][H]=res;
	}
}

/*
	약을 하나 꺼냄 -> 1개면 반을 쪼개서 다시 넣고(W), 반개면 다 먹음(H)
	
	이때 가능한 서로 다른 문자열의 개수
	
	W N개, H N개의 순열
	WWWWWWHHHHHH
	
	무조건 W가 앞에 와야하는구나 
	
*/