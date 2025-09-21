//package BOJ.파일합치기;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] map, sum;
	static int[][] dp;
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N];
			sum = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
				sum[i]=map[i];
				if(i!=0) sum[i]+=sum[i-1];
				
			}
			dp = new int[N][N];
			for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);
			
			sb.append(dfs(0, N-1)).append("\n");
		}
		System.out.println(sb);
	}
	static int dfs(int l, int r) {
		if (l==r) return 0;
		
		if(dp[l][r]!=-1) return dp[l][r];
		
		int res = INF;
		for (int i=l; i<r; i++) {
			res = Math.min(dfs(l, i) + dfs(i+1, r) + sum[r]-(l>0?sum[l-1]:0), res);
		}
		
		return dp[l][r]=res;
	}
}
