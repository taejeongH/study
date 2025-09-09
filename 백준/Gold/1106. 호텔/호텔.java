//package BOJ.호텔;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][2];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken()); //비용
			map[i][1] = Integer.parseInt(st.nextToken()); //고객의 수
		}
		
		int[][] dp = new int[N+1][C];
		for (int i=0; i<N+1; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
		for (int i=1; i<N+1; i++) {
			int curPrice = map[i-1][0];
			int curCount = map[i-1][1];
			for (int j=0; j<C; j++) {
				if (curCount > j) dp[i][j] = Math.min(curPrice, dp[i-1][j]);
				else dp[i][j] = Math.min(dp[i-1][j], dp[i][j-curCount]+curPrice);

			}
		}
		
		System.out.println(dp[N][C-1]);
	}
}
