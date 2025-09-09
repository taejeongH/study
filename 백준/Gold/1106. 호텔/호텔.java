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
		
		int[][] dp = new int[N+1][C+1];
		for (int i=1; i<C+1; i++) {
			int cost = map[0][0];
			int count = map[0][1];
			dp[1][i] = ((i-1)/count+1)*cost;
		}
		
//		for (int i=0; i<N+1; i++) System.out.println(Arrays.toString(dp[i]));
		//System.out.println();
		for (int i=2; i<N+1; i++) {
			int curPrice = map[i-1][0];
			int curCount = map[i-1][1];
			for (int j=0; j<C+1; j++) {
				if (curCount > j) dp[i][j] = Math.min(curPrice, dp[i-1][j]);
				else dp[i][j] = Math.min(dp[i-1][j], dp[i][j-curCount]+curPrice);

			}
		}
		
//		for (int i=0; i<N+1; i++) System.out.println(Arrays.toString(dp[i]));
		System.out.println(dp[N][C]);
	}
}
