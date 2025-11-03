//package BOJ.보물섬;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[N][M];
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				if (input.charAt(j)=='L') map[i][j]=true;
			}
		}
		
		int res = 0;
		ArrayDeque<int[]> que = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if(!map[i][j]) continue;
				que.add(new int[] {i, j, 0});
				boolean[][] v = new boolean[N][M];
				v[i][j]=true;
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int y = now[0];
					int x = now[1];
					int dis = now[2];
					
					res = Math.max(res, dis);
					
					for (int k=0; k<4; k++) {
						int ny = y+dy[k];
						int nx = x+dx[k];
						if(ny<0 || ny>=N || nx<0 || nx>=M || v[ny][nx] || !map[ny][nx]) continue;
						v[ny][nx]=true;
						que.add(new int[] {ny, nx, dis+1});
					}
				}
			}
		}
		
		System.out.println(res);
	}
}
