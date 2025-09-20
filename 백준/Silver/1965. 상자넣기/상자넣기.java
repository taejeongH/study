//package BOJ.상자넣기;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] map, dp;
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
		dp = new int[N];
		Arrays.fill(dp, -1);
		int res = 0;
		for (int i=0; i<N; i++) res = Math.max(dfs(i), res);
		System.out.println(res);
	}
	
	public static int dfs(int idx) {
		if(dp[idx]!=-1) return dp[idx];
		
		int res = 1;
		for (int i=idx+1; i<N; i++) {
			if(map[idx] < map[i]) res = Math.max(res, 1+dfs(i));
		}
		
		return dp[idx]=res;
	}
}
