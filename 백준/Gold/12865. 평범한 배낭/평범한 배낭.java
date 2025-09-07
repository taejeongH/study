//package BOJ.평범한배낭;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][2];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N+1][K+1];
		
		for (int i=1; i<=N; i++) {
			int curWeight = map[i-1][0];
			int curValue = map[i-1][1];
			for (int j=0; j<=K; j++) {
				if (j < curWeight) dp[i][j] = dp[i-1][j];
				else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-curWeight]+curValue);
			}
		}
		//for (int i=0; i<N+1; i++) System.out.println(Arrays.toString(dp[i]));
		
		System.out.println(dp[N][K]);
	}
	
}
