//package BOJ.가장큰정사각형;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] dx = {1, 1, 0}; //우, 우하, 하
	static int[] dy = {0, 1, 1};
	static boolean[][] map;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		map = new boolean[N][M];
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				if (input.charAt(j)=='1')map[i][j] = true;
			}
		}
		
		int res = 0;
		dp = new int[N][M];
		for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]) res = Math.max(res, dfs(i, j));
			}
		}
		
		System.out.println(res * res);
	}
	
	public static int dfs(int y, int x) {
		if (y >= N || x >= M || !map[y][x]) return 0;
		
		if (dp[y][x]!=-1) return dp[y][x];
		
		int res = Integer.MAX_VALUE;
		for (int i=0; i<3; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			res = Math.min(dfs(ny, nx), res);
		}
		
		return dp[y][x]=res+1;
	}
}


/*
	1과 0으로 이루어진 N*M배열에서 
	1로된 가장 큰 정사각형의 크기를 구하는 문제
	
	
	가장 작은 크기 2로 설정하고 찾을 때마다 ++, 
	결과는 찾은 값 - 1로 


*/