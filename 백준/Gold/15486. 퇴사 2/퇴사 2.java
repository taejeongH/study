//package BOJ.퇴사2;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N+1][2];
		for (int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		int result = 0;
		for (int i=1; i<N+1; i++) {
			int end = i + map[i][0] - 1;
			dp[i]=Math.max(dp[i], dp[i-1]);
			if(end <= N) {
				dp[end] = Math.max(dp[end], dp[i-1]+map[i][1]);
			}
//			result = Math.max(dp[i], result);
			
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[N]);
	}
}
