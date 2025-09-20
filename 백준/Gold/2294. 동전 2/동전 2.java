//package BOJ.동전2;

import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] map, dp;
	static final int INF = 10_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N];
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[K+1];
		Arrays.fill(dp, -1);
		int res = dfs(0);
		System.out.println(res!=INF ? res : -1);
	}
	
	public static int dfs(int sum) {
		if(sum == K) return 0;
		if(dp[sum]!=-1) return dp[sum];
		
		int res = INF;
		for (int i=0; i<N; i++) {
			if(sum+map[i]<=K) {
				res = Math.min(res, 1+dfs(sum+map[i]));
			}
		}
		
		return dp[sum]= res;
	}
}
