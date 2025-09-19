//package BOJ.다리만들기;

import java.io.*;
import java.util.*;

public class Main {
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		findLand(map);
		System.out.println(findDistance(map));
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
	}
 	
	public static void findLand(int[][] map) {
		int N = map.length;
		boolean[][] visited = new boolean[N][N];
		ArrayDeque<int[]> que = new ArrayDeque<>();
		int idx = 1;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(map[i][j]==0 || visited[i][j]) continue;
				que.add(new int[] {i, j});
				visited[i][j] = true;
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int y = now[0];
					int x = now[1];
					
					map[y][x] = idx;
					
					for (int k=0;k<4;k++) {
						int ny = y+dy[k];
						int nx = x+dx[k];
						if(ny<0 || ny>=N || nx<0 || nx>=N || visited[ny][nx] || map[ny][nx]==0) continue;
						visited[ny][nx]=true;
						que.add(new int[] {ny, nx});
					}
				}
				idx++;
			}
		}
	}
	
	public static int findDistance(int[][] map) {
		int N = map.length;
		
		int minDis = Integer.MAX_VALUE;
		ArrayDeque<int[]> que = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(map[i][j]==0) continue;
				boolean[][] visited = new boolean[N][N];
				int curLand = map[i][j];
				que.add(new int[] {i, j, 0});
				visited[i][j]=true;
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int y = now[0];
					int x = now[1];
					int dis = now[2];
					
					if(map[y][x]!=0 && map[y][x]!=curLand) {
						minDis = Math.min(dis-1, minDis);
					}
					
					for (int k=0;k<4;k++) {
						int ny = y+dy[k];
						int nx = x+dx[k];
						if(ny<0 || ny>=N || nx<0 || nx>=N || visited[ny][nx] || map[ny][nx]==curLand) continue;
						visited[ny][nx]=true;
						que.add(new int[] {ny, nx, dis+1});
					}
				}
			}
		}
		return minDis;
	}
}
