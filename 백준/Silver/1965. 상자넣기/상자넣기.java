//package BOJ.상자넣기;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] map;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = 0;
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			max = Math.max(map[i], max);
		}
		dp = new int[max+1][N];
		for (int i=0; i<max+1; i++) Arrays.fill(dp[i], -1);
		System.out.println(dfs(0, 0));
	}
	
	public static int dfs(int select, int depth) {
		if (depth==N) return 0;
		
		if(dp[select][depth]!=-1) return dp[select][depth];
		
		int res = 0;
		for (int i=depth; i<N; i++) {
			if (select<map[i]) {
				res = Math.max(res, dfs(map[i], i+1)+1);
			} else {
				res = Math.max(res, dfs(select, i+1));
			}
		}
		
		return dp[select][depth]=res;
	}
}
