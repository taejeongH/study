//package BOJ.뿌요뿌요11559;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N = 12;
	static int M = 6;
	static char[][] map;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./src/BOJ/뿌요뿌요11559/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[N][M];
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		ArrayDeque<int[]> startQue = new ArrayDeque<>();
		int result = 0;
		while(true) {
			boolean[][] visited = new boolean[N][M];
			//터질애들 찾기
			for (int i=N-1; i>=0; i--) {
				int find = 0;
				for (int j=0; j<M; j++) {
					if(map[i][j] != '.') {
						find++;
						if(visited[i][j]) continue;
						char startWord = map[i][j];
						que.add(new int[] {i, j});
						visited[i][j] = true;
						int count = 1;
						while(!que.isEmpty()) {
							int[] now = que.poll();
							int y = now[0];
							int x = now[1];
							for(int k=0; k<4; k++) {
								int nextY = y+dy[k];
								int nextX = x+dx[k];
								if(nextY>=0 && nextY<N && nextX>=0 && nextX<M && !visited[nextY][nextX] && map[nextY][nextX]==startWord) {
									visited[nextY][nextX] = true;
									count++;
									que.add(new int[] {nextY, nextX});
								}
							}
						}
						if(count >= 4) {
							startQue.add(new int[] {i, j});
						}
					}
				}
				if (find == 0) break;
			}
			if(startQue.size() == 0) break;
			result++;
			
			//터트리기
			ArrayDeque<int[]> popQue = new ArrayDeque<>();
			while(!startQue.isEmpty()) {
				int[] start = startQue.poll();
				int startY = start[0];
				int startX = start[1];
				char startWord = map[startY][startX];
				popQue.add(new int[] {startY, startX});
				map[startY][startX] = '.';
				while (!popQue.isEmpty()) {
					int[] now = popQue.poll();
					int y = now[0];
					int x = now[1];
					for (int i=0; i<4; i++) {
						int nextY = y+dy[i];
						int nextX = x+dx[i];
						if(nextY>=0 && nextY<N && nextX>=0 && nextX<M && map[nextY][nextX]==startWord) {
							map[nextY][nextX] = '.';
							popQue.add(new int[] {nextY, nextX});
						}
					}
				}
				
			}
			fall();
//			for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
//			System.out.println();
		}
		System.out.println(result);
	}
	
	
	
	public static void fall() {
		for (int i=N-1; i>=0; i--) {
			for (int j=0; j<M; j++) {
				if (map[i][j]!='.') {
					int y = i;
					while(true) {
						if(y+1 >= N || map[y+1][j] != '.') break;
						map[y+1][j] = map[y][j];
						map[y][j] = '.';
						y++;
					}
				}
			}
		}
	}
}
