//package BOJ.백조의호수;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int startY, startX, N, M;
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		startY=-1;
		startX=-1;
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j]=='L') {
					if(startY!=-1) continue;
					startY = i;
					startX = j;
					map[i][j]='S';
				}
			}
		}
		ArrayDeque<int[]> que = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]=='X') continue;
				boolean find=false;
				for (int k=0; k<4; k++) {
					int ny = i+dy[k];
					int nx = j+dx[k];
					if(ny>=0 && ny<N && nx>=0 && nx<M) {
						if(map[ny][nx]=='X') {
							find = true;
							break;
						}
					}
				}
				if (find) que.add(new int[] {i, j, 1});
			}
		}
		
		int[][] distance = new int[N][M];
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int t = now[2];
			

			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				if(ny>=0 && ny<N && nx>=0 && nx<M && map[ny][nx]=='X') {
					map[ny][nx] = '.';
					distance[ny][nx] = t;
					que.add(new int[] {ny, nx, t+1});
				}
			}
		}
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(distance[i]));
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2],o2[2]));
		int result = Integer.MAX_VALUE;
		pq.add(new int[] {startY, startX, 0});
		boolean[][] visited = new boolean[N][M];
		visited[startY][startX]=true;
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int y = now[0];
			int x = now[1];
			int max = now[2];
			
			if(map[y][x]=='L') {
				result = max;
				break;
			}

			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				if(ny>=0 && ny<N && nx>=0 && nx<M && !visited[ny][nx]) {
					visited[ny][nx]=true;
					pq.add(new int[] {ny, nx, Math.max(distance[ny][nx], max)});
				}
			}
		}
		
		System.out.println(result);
	}
	
}
