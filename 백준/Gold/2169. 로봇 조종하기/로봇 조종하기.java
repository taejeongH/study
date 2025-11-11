//package BOJ.로봇조종하기;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static final int INF = -100_000_000;
	static int[][] map;
	static int[] dx = {1, 0, -1};
	static int[] dy = {0, 1, 0};
	static int[][][] dp;
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
		dp = new int[N][M][3];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				Arrays.fill(dp[i][j], INF);
			}
		}
		System.out.println(dfs(0, 0, 0));
	}
	
	static int dfs(int y, int x, int dir) {
		if (y==N-1 && x==M-1) return map[N-1][M-1];
		if (dp[y][x][dir]!=INF) return dp[y][x][dir];
		
		for (int i=0; i<3; i++) {
			if (dir==0 && i==2) continue;
			if (dir==2 && i==0) continue;
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(ny<0 || ny>=N || nx<0 || nx>=M) continue;
			dp[y][x][dir] = Math.max(dfs(ny, nx, i)+map[y][x], dp[y][x][dir]);
		}
		return dp[y][x][dir];
	}
}
