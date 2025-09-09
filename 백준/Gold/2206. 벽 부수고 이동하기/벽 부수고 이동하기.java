//package BOJ.벽부수고이동하기;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		int wallIdx = 1;
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j)-'0';
//				if(map[i][j]==1) map[i][j] = wallIdx++;
			}
		}
		
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		boolean[][][] v = new boolean[N][M][2];
		que.add(new int[] {0, 0, 1, 0});
		v[0][0][0] = true;
		
		int result = -1;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int dis = now[2];
			int wall = now[3];
			
			if(y==N-1&&x==M-1) {
				result = dis;
				break;
			}
			for (int i=0; i<4; i++) {
				int ny=y+dy[i];
				int nx=x+dx[i];
				
				if(ny<0 || ny>=N || nx<0 || nx>=M) continue;
				if(v[ny][nx][wall]) continue;
				if(map[ny][nx]==0 && !v[ny][nx][wall]) {
					v[ny][nx][wall] = true;
					que.add(new int[] {ny, nx, dis+1, wall});
				} 
				
				if (map[ny][nx]==1 && wall==0 && !v[ny][nx][1]){
					v[ny][nx][1] = true;
					que.add(new int[] {ny, nx, dis+1, 1});
				}
			}
		}
		System.out.println(result);
	}
}
