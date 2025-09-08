//package BOJ.내리막길;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[][] dp, map;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static boolean[][] visited;
	
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
		
		dp= new int[N][M];
		visited = new boolean[N][M];
		System.out.println(recur(N-1, M-1));
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(dp[i]));
//		System.out.println();
	}
	
	public static int recur(int y, int x) {
		if(y==0 && x==0) return 1;
		if(dp[y][x]!=0)return dp[y][x];
		if(visited[y][x]) return 0;
		visited[y][x] = true;
		
		for (int i=0; i<4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			
			if(ny>=0 && ny<N && nx>=0 && nx<M && map[ny][nx]>map[y][x]) {
				dp[y][x] += recur(ny, nx);
			}
		}
		return dp[y][x];
	}
}
