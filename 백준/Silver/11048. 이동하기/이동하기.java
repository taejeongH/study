//package BOJ.이동하기;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[][] map, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][M];
		for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);
		int result = recur(N-1, M-1);
		System.out.println(result);
	}
	
	public static int recur(int y, int x) {
		if (y<0 || x<0) return 0;
		if(dp[y][x] != -1) return dp[y][x];
		if (y==0 && x==0) return map[y][x];
		
		int max = Math.max(recur(y-1, x), recur(y, x-1));
		max = Math.max(max, recur(y-1, x-1));
		
		
		return dp[y][x] = max + map[y][x];
	}
}
