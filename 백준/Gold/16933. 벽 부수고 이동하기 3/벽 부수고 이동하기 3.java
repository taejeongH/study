//package BOJ.벽부수고이동하기3;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[N][M];
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				if(input.charAt(j)=='1') map[i][j]=true;
			}
		}
		
		boolean[][][] visited = new boolean[N][M][K+1];
		
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		que.add(new int[] {0, 0, 1, 0, 0}); //y, x, dis, day
		visited[0][0][0]=true;
		int res = -1;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int dis = now[2];
			int day = now[3];
			int broken = now[4];
			
			if(y==N-1 && x==M-1) {
				res = dis;
				break;
			}
			
			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				if(ny<0 || ny>=N || nx<0 || nx>=M) continue;
				
				if(map[ny][nx] && broken < K && !visited[ny][nx][broken+1]) {
					visited[ny][nx][broken+1] = true;
					if(day == 0) {
						que.add(new int[] {ny, nx, dis+1, 1, broken+1});
					} else {
						que.add(new int[] {ny, nx, dis+2, 1, broken+1});
					}
				} else if(!map[ny][nx] && !visited[ny][nx][broken]) {
					visited[ny][nx][broken]=true;
					que.add(new int[] {ny, nx, dis+1, 1-day, broken});
				}
			}
		}
		
		System.out.println(res);
		
	}
}
