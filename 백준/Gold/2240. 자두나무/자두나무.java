//package BOJ.자두나무;

import java.io.*;
import java.util.*;

public class Main {
	static int T, W;
	static int[] map;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		map = new int[T];
		for (int i=0; i<T; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[W+1][T+1];
		for (int i=0; i<W+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(dfs(0, 0));
	}
	public static int dfs(int count, int time) {
		if (time==T) return 0;
		
		if(dp[count][time]!=-1)return dp[count][time];
		
		int pos = (count%2)==0?1:2;
		if(map[time]==pos) {
			return dp[count][time] = dfs(count, time+1)+1;
		} else if(count<W) {
			return dp[count][time] = Math.max(dfs(count+1, time+1)+1, dfs(count, time+1));
		} else {
			return dp[count][time] = dfs(count, time+1);
		}
	}
}
