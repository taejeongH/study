//package BOJ.알고스팟;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		int[][] dis = new int[N][M];
		for (int i=0; i<N; i++) Arrays.fill(dis[i], Integer.MAX_VALUE);
		
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		pq.add(new int[] {0, 0, 0});
		dis[0][0] = 0;
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int y = now[0];
			int x = now[1];
			int dist = now[2];
//			System.out.println(y + " " + x + " " + dist);
			
			if(y==N-1 && x==M-1) {
				System.out.println(dist);
				break;
			}
			
			if(dist > dis[y][x]) continue;
			
			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if(ny<0 || ny>=N || nx<0 || nx>=M) continue;
				if(dis[ny][nx] <= dist+map[ny][nx]) continue;
				dis[ny][nx] = dist+map[ny][nx];
				pq.add(new int[] {ny, nx, dis[ny][nx]});
			}
		}
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(dis[i]));
		
	}
	

}

/*
	(1,1) -> (N,M) 벽을 몇 개 부셔야 하는가?
*/