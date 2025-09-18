//package BOJ.빙산;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
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
		
		int res = 0;
		int time = 0;
		int[][] nxtMap = new int[N][M];
		while(true) {
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if(map[i][j]==0) {
						nxtMap[i][j]=0;
						continue;
					}
					int sum = 0;
					for (int k=0; k<4; k++) {
						int ny = i+dy[k];
						int nx = j+dx[k];
						if(ny<0 || ny>=N || nx<0 || nx>=M) continue;
						if (map[ny][nx]==0) {
							sum++;
						}
					}
					nxtMap[i][j]=Math.max(0, map[i][j]-sum);
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					map[i][j] = nxtMap[i][j];
				}
			}
			time++;
			int cluster = floodFill();
			if(cluster==0) break;
			if(cluster>=2) {
				res=time;
				break;
			}
			
		}
		
		System.out.println(res);
	}
	
	public static int floodFill() {
		int idx = 0;
		boolean[][] visited = new boolean[N][M];
		ArrayDeque<int[]> que = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(map[i][j]==0 || visited[i][j]) continue;
				idx++;
				visited[i][j] = true;
				que.add(new int[] {i, j});
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int y = now[0];
					int x = now[1];
					
					for (int k=0; k<4; k++) {
						int ny = y+dy[k];
						int nx = x+dx[k];
						if(ny<0 || ny>=N || nx<0 || nx>=M || visited[ny][nx]||map[ny][nx]==0) continue;
						visited[ny][nx]=true;
						que.add(new int[] {ny, nx});
					}
				}
			}
		}
		
		return idx;
	}
}
