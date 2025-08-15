//package BOJ.탈출3055;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./src/BOJ/탈출3055/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		int[] start = new int[3];
		int[] end = new int[2];
		List<int[]> water = new ArrayList<>();
		for (int i=0; i<R; i++) {
			String input = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j]=='D') {
					end[0] = i;
					end[1] = j;
				} else if(map[i][j]=='S') {
					start[0] = i;
					start[1] = j;
				} else if(map[i][j]=='*') {
					water.add(new int[] {i, j, 0});
				}
			}
		}
		
		int[][] waterMap = new int[R][C];
		for (int i=0; i<R; i++) {
			Arrays.fill(waterMap[i], Integer.MAX_VALUE);
		}
		for (int i=0; i<water.size(); i++) {
			ArrayDeque<int[]> waterQue = new ArrayDeque<>();
			boolean[][] waterVisited = new boolean[R][C];
			waterQue.add(water.get(i));
			while(!waterQue.isEmpty()) {
				int[] now = waterQue.poll();
				int y = now[0];
				int x = now[1];
				int c = now[2];
				
				if(y!= end[0] || x != end[1])
				waterMap[y][x] = Math.min(waterMap[y][x], c);
				
				for (int j=0; j<4; j++) {
					int nextY = y+dy[j];
					int nextX = x+dx[j];
					if(nextY>=0 && nextY<R && nextX>=0 && nextX<C && !waterVisited[nextY][nextX] && map[nextY][nextX] == '.') {
						waterVisited[nextY][nextX] = true;
						waterQue.add(new int[] {nextY, nextX, c+1});
					}
				}
			}
		}
//		for (int i=0; i<R; i++) System.out.println(Arrays.toString(waterMap[i]));
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(start);
		int result = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[R][C];
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int c = now[2];
			
			if(y == end[0] && x==end[1]) {
				result = Math.min(c, result);
			}
			for (int i=0; i<4; i++) {
				int nextY = y+dy[i];
				int nextX = x+dx[i];
				if(nextY>=0 && nextY<R && nextX>=0 && nextX<C && !visited[nextY][nextX]) {
					if (map[nextY][nextX] != 'X' && c+1 < waterMap[nextY][nextX]) {
						visited[nextY][nextX] = true;
						que.add(new int[] {nextY, nextX, c+1});
					}
					
				}
			}
		}
		if(result==Integer.MAX_VALUE) System.out.println("KAKTUS");
		else System.out.println(result);
		
		
		
	}
}
