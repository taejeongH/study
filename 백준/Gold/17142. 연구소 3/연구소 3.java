//package BOJ.연구소3;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, result;
	static int[][] map;
	static ArrayList<int[]> virus;
	static int[] selected;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virus = new ArrayList<>();
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) virus.add(new int[] {i, j});
			}
		}
		
		result = Integer.MAX_VALUE;
		selected = new int[M];
		bt(0, 0);
		if(result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
		
	}
	
	public static void bt(int num, int start) {
		if (num == M) {
			int time = calTime();
			if (time==-1)return;
			result = Math.min(result, time);
			return;
		}
		
		for (int i=start; i<virus.size(); i++) {
			selected[num] = i;
			bt(num+1, i+1);
			selected[num] = 0;
		}
	}
	
	public static int calTime() {
		
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2)-> Integer.compare(o1[2], o2[2]));
//		ArrayDeque<int[]> que = new ArrayDeque<>();
		boolean visited[][] = new boolean[N][N];
		for (int i=0; i<M; i++) {
			que.add(new int[] {virus.get(selected[i])[0], virus.get(selected[i])[1], 0});
			visited[virus.get(selected[i])[0]][virus.get(selected[i])[1]] = true;
		}
		
		int time = 0;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int d = now[2];
			
			if (map[y][x] != 2) time = Math.max(d, time);
			
			for (int i=0; i<4; i++) {
				int nextY = y+dy[i];
				int nextX = x+dx[i];
				if(nextY>=0 && nextY<N && nextX>=0 && nextX<N && map[nextY][nextX] != 1 && !visited[nextY][nextX]) {
					if (map[nextY][nextX]==0) {
						que.add(new int[] {nextY, nextX, d+1});
					} else{
						que.add(new int[] {nextY, nextX, d+1});
					}
					visited[nextY][nextX] = true;
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]==0 && !visited[i][j]) return -1;
			}
		}
	
		
		return time;
	}
}
