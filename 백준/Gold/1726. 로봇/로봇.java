//package BOJ.로봇;

import java.io.*;
import java.util.*;

public class Main {
	static int[] convert = {-1, 2, 0, 3, 1};
	static int[] dx = {-1, 0, 1, 0}; //(i+1)%4 -> 우회전, (i+3)%4 -> 좌회전
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				if(Integer.parseInt(st.nextToken())==1) map[i][j]=true;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int startY = Integer.parseInt(st.nextToken())-1;
		int startX = Integer.parseInt(st.nextToken())-1;
		int startDir = Integer.parseInt(st.nextToken());
		startDir = convert[startDir];
		
		st = new StringTokenizer(br.readLine());
		int endY = Integer.parseInt(st.nextToken())-1;
		int endX = Integer.parseInt(st.nextToken())-1;
		int endDir = Integer.parseInt(st.nextToken());
		endDir = convert[endDir];
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		boolean[][][] v = new boolean[N][M][4];
		
		que.add(new int[] {startY, startX, startDir, 0});
		v[startY][startX][startDir]=true;
		
		int res = -1;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int dir = now[2];
			int dis = now[3];
			
			if (y==endY && x==endX && dir==endDir) {
				res = dis;
				break;
			}
			
			//최대 3칸 이동
			for (int i=1; i<=3; i++) {
				int ny = y+(dy[dir]*i);
				int nx = x+(dx[dir]*i);
				
				if(ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx]) break;
				
				if(v[ny][nx][dir])continue;
				v[ny][nx][dir]=true;
				que.add(new int[] {ny, nx, dir, dis+1});
			}
			
			//좌우 회전
			int leftDir = (dir+3)%4;
			if (!v[y][x][leftDir]) {
				v[y][x][leftDir]=true;
				que.add(new int[] {y, x, leftDir, dis+1});
			}
			
			int rightDir = (dir+1)%4;
			if(!v[y][x][rightDir]) {
				v[y][x][rightDir]=true;
				que.add(new int[] {y, x, rightDir, dis+1});
			}
			
		}
		
		System.out.println(res);
	}
}


/*
	order
	1. 현재 방향으로 1/2/3칸 이동
	2. 방향을 왼쪽/오른쪽으로 turn
	
	


*/