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
		
		int[] dp = new int[K+1];
		for (int i=0; i<N; i++) {
		    int w = map[i][0];
		    int v = map[i][1];
		    for (int j=K; j>=w; j--) {
		        dp[j] = Math.max(dp[j], dp[j-w] + v);
		    }
		}
		System.out.println(dp[K]);
	}
	
}
