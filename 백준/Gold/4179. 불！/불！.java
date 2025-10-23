//package BOJ.불;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		int startY = -1;
		int startX = -1;
		ArrayDeque<int[]> que = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j]=='J') {
					map[i][j]='.';
					startY = i;
					startX = j;
				} else if(map[i][j]=='F') {
					que.add(new int[] {i, j, -1});
				}
			}
		}
		
		que.add(new int[] {startY, startX, 1});
		
		int res = -1;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int dis = now[2];
			
			if(dis!=-1 && (y==N-1 || y==0 || x==0|| x==M-1)) {
				res = dis;
				break;
			}
			
			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if(ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx]!='.') continue;
				
				map[ny][nx] = 'F';
				if(dis==-1) {
					que.add(new int[] {ny, nx, -1});
				} else {
					que.add(new int[] {ny, nx, dis+1});
				}
			}
		}
		
		System.out.println(res==-1?"IMPOSSIBLE":res);
	}
}
