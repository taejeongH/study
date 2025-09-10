//package BOJ.RGB거리2;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][3];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
			map[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int[][][] dp = new int[3][N][3];
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (i==j) {
					dp[i][0][j] = Integer.MAX_VALUE; 
					continue;
				}
				dp[i][0][j] = map[0][j];
			}
		}
		
		
		for (int k=0; k<3; k++) {
			for (int i=1; i<N; i++) {
				for (int j=0; j<3; j++) {
					int pre = j-1;
					if(pre < 0) pre = 2;
					int nxt = (j+1)%3;
					dp[k][i][j] = Math.min(dp[k][i-1][pre], dp[k][i-1][nxt]);
					dp[k][i][j] += map[i][j];
				}
			}
		}
		
		int res = Integer.MAX_VALUE;
		for (int k=0; k<3; k++) {
			res = Math.min(res, dp[k][N-1][k]);
		}
		System.out.println(res);
//		System.out.println(Arrays.deepToString(dp));
	}
}
