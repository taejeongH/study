//package BOJ.미로만들기;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = input.charAt(j)-'0';
				if(map[i][j]==1) map[i][j]=0;
				else map[i][j]=1;
			}
		}
		
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		que.add(new int[] {0, 0, 0});
		int[][] distance = new int[N][N];
		for (int i=0; i<N; i++) Arrays.fill(distance[i], Integer.MAX_VALUE);
		distance[0][0]=0;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int dis = now[2];
			
			if(dis > distance[y][x]) continue;
			
			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if(ny<0 || ny>=N || nx<0 || nx>=N || distance[ny][nx]<=dis+map[ny][nx]) continue;
				
				distance[ny][nx] = dis+map[ny][nx];
				que.add(new int[] {ny, nx, dis+map[ny][nx]});				
			}
		}
		
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(distance[i]));
		
		
		System.out.println(distance[N-1][N-1]);
	}

}
