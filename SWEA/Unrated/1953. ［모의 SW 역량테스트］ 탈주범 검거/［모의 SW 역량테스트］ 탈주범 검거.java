//package SWEA.탈주범검거1953;

import java.util.*;
import java.io.*;

public class Solution {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0,};
	static int[][] can = { {0, 1, 1, 0, 0, 1, 1, 0}, 
						   {0, 1, 1, 0, 1, 0, 0, 1},
						   {0, 1, 0, 1, 1, 1, 0, 0},
						   {0, 1, 0, 1, 0, 0, 1, 1}
	};
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/SWEA/탈주범검거1953/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int test=1; test<=testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int startX = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ArrayDeque<int[]> que = new ArrayDeque<>();
			que.add(new int[] {startY, startX, 1});
			int result = 0;
			boolean[][] visited = new boolean[N][M];
			visited[startY][startX] = true;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				int t = now[2];
				if(t>time) break;
				result++;
				if(map[y][x]==1) {
					for (int i=0; i<4; i++) {
						int nextY = y+dy[i];
						int nextX = x+dx[i];
						if(nextY>=0 && nextY<N && nextX>=0 && nextX<M && map[nextY][nextX]!=0 && !visited[nextY][nextX] && can[i][map[nextY][nextX]]==1) {
							visited[nextY][nextX] = true;
							que.add(new int[] {nextY, nextX, t+1});
						}
					}
				} else if(map[y][x]==2) {
					for (int i=0; i<2; i++) {
						int nextY = y+dy[i];
						int nextX = x+dx[i];
						if(nextY>=0 && nextY<N && nextX>=0 && nextX<M && map[nextY][nextX]!=0 && !visited[nextY][nextX] && can[i][map[nextY][nextX]]==1) {
							visited[nextY][nextX] = true;
							que.add(new int[] {nextY, nextX, t+1});
						}
					}
				} else if(map[y][x]==3) {
					for (int i=2; i<4; i++) {
						int nextY = y+dy[i];
						int nextX = x+dx[i];
						if(nextY>=0 && nextY<N && nextX>=0 && nextX<M && map[nextY][nextX]!=0 && !visited[nextY][nextX] && can[i][map[nextY][nextX]]==1) {
							visited[nextY][nextX] = true;
							que.add(new int[] {nextY, nextX, t+1});
						}
					}
				} else if(map[y][x]==4) {
					for (int i=0; i<4; i+=3) {
						int nextY = y+dy[i];
						int nextX = x+dx[i];
						if(nextY>=0 && nextY<N && nextX>=0 && nextX<M && map[nextY][nextX]!=0 && !visited[nextY][nextX] && can[i][map[nextY][nextX]]==1) {
							visited[nextY][nextX] = true;
							que.add(new int[] {nextY, nextX, t+1});
						}
					}
				} else if(map[y][x]==5) {
					for (int i=1; i<4; i+=2) {
						int nextY = y+dy[i];
						int nextX = x+dx[i];
						if(nextY>=0 && nextY<N && nextX>=0 && nextX<M && map[nextY][nextX]!=0 && !visited[nextY][nextX] && can[i][map[nextY][nextX]]==1) {
							visited[nextY][nextX] = true;
							que.add(new int[] {nextY, nextX, t+1});
						}
					}
				} else if(map[y][x]==6) {
					for (int i=1; i<3; i++) {
						int nextY = y+dy[i];
						int nextX = x+dx[i];
						if(nextY>=0 && nextY<N && nextX>=0 && nextX<M && map[nextY][nextX]!=0 && !visited[nextY][nextX] && can[i][map[nextY][nextX]]==1) {
							visited[nextY][nextX] = true;
							que.add(new int[] {nextY, nextX, t+1});
						}
					}
				} else if(map[y][x]==7) {
					for (int i=0; i<3; i+=2) {
						int nextY = y+dy[i];
						int nextX = x+dx[i];
						if(nextY>=0 && nextY<N && nextX>=0 && nextX<M && map[nextY][nextX]!=0 && !visited[nextY][nextX] && can[i][map[nextY][nextX]]==1) {
							visited[nextY][nextX] = true;
							que.add(new int[] {nextY, nextX, t+1});
						}
					}
				}
			}
			
//			for (int i=0; i<N; i++) {
//				System.out.println(Arrays.toString(visited[i]));
//			}
//			System.out.println();
			
			
			
			
			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
}
