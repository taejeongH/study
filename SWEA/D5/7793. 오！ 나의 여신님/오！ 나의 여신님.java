//package SWEA.오나의여신님7793;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/SWEA/오나의여신님7793/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //y
			int M = Integer.parseInt(st.nextToken()); //x
			
			char[][] map = new char[N][M];
			boolean[][] visited = new boolean[N][M];
			int[] yeonPos = new int[3];
			int[] godPos = new int[2];
			ArrayDeque<int[]> que = new ArrayDeque<>();
			for (int i=0; i<N; i++) {
				String input = br.readLine();
				for (int j=0; j<M; j++) {
					map[i][j] = input.charAt(j);
					if(map[i][j]=='S') {
						yeonPos[0] = i;
						yeonPos[1] = j;
					} else if(map[i][j]=='*') {
						que.add(new int[] {i, j, -1});
						visited[i][j] = true;
					} else if(map[i][j]=='D') {
						godPos[0] = i;
						godPos[1] = j;
					}
				}
			}
			int result = 0;
			que.add(yeonPos);
			visited[yeonPos[0]][yeonPos[1]] = true;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				int d = now[2];
				
				if(y==godPos[0] && x==godPos[1] && d!=-1) {
					result = d;
					break;
				}
				
				for (int i=0; i<4; i++) {
					int nextY = y+dy[i];
					int nextX = x+dx[i];
					
					if (nextY>=0 && nextY<N && nextX>=0 && nextX<M && !visited[nextY][nextX] && map[nextY][nextX]!='X') {
						if (d!=-1) {
							visited[nextY][nextX] = true;
							que.add(new int[] {nextY,nextX,d+1});
						} else {
							if (map[nextY][nextX]!='D') {
								visited[nextY][nextX] = true;
								que.add(new int[] {nextY,nextX, d});
							}
						}
					}
				}
			}
//			for (int i=0; i<N; i++) System.out.println(Arrays.toString(visited[i]));
			if (result==0) sb.append("#").append(test).append(" ").append("GAME OVER").append("\n");
			else sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
}
