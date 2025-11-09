//package BOJ.성곽;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] g = new int[N][M];
		ArrayDeque<int[]> que = new ArrayDeque<>();
		boolean[][] v = new boolean[N][M];
		int[] roomSize = new int[2501];
		int maxSize = 0;
		int cnt = 1;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(v[i][j]) continue;
				que.add(new int[] {i, j});
				v[i][j]=true;
				int size = 0;
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int y = now[0];
					int x = now[1];
					
					g[y][x] = cnt;
					size++;
					for (int k=0; k<4; k++) {
						int cur = 1<<k;
						if ((map[y][x] & cur)!=0) continue;
						int ny = y+dy[k];
						int nx = x+dx[k];
						if(!v[ny][nx]) {
							v[ny][nx] = true;
							que.add(new int[] {ny, nx});
						}
					}
				}
				roomSize[cnt] = size;
				maxSize = Math.max(maxSize, size);
				cnt++;
			}
		}
		
		int res = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				for (int k=0; k<4; k++) {
					int ny = i+dy[k];
					int nx = j+dx[k];
					if(ny<0 || ny>=N || nx<0 || nx>=M || g[ny][nx]==g[i][j]) continue;
					
					res = Math.max(res, roomSize[g[i][j]]+roomSize[g[ny][nx]]);
				}
			}
		}
		
		System.out.println(cnt-1);
		System.out.println(maxSize);
		System.out.println(res);
	}
}


/*
	좌상우하
	
	
	map & (1<<i) == 1<<i 갈 수 없는 곳임
	
*/