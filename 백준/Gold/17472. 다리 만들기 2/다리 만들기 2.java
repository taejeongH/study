//package BOJ.다리만들기투17472;

import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("./src/BOJ/다리만들기투17472/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); //y크기
		int M = Integer.parseInt(st.nextToken()); //x크기
		
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//섬의 개수 찾기
		int landCount = 0;
		ArrayDeque<int[]> que = new ArrayDeque<>();
		boolean[][] findLand = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !findLand[i][j]) {
					que.add(new int[] {i, j});
					map[i][j] = ++landCount;
					while (!que.isEmpty()) {
						int[] now = que.poll();
						int y = now[0];
						int x = now[1];
						
						for (int k = 0; k < 4; k++) {
							int nextY = y + dy[k];
							int nextX = x + dx[k];
							if(nextY < N && nextY >= 0 && nextX < M && nextX >= 0 && map[nextY][nextX] == 1 && !findLand[nextY][nextX]) {
								map[nextY][nextX] = landCount;
								findLand[nextY][nextX] = true;
								que.add(new int[] {nextY, nextX});
							}
						}
					}
					
				}
			}
		}
		
		int[][] landList = new int[landCount+1][landCount+1];
		for (int i = 0; i < landList.length; i++) {
			Arrays.fill(landList[i], Integer.MAX_VALUE);
		}
		//섬에서 다른 섬으로 이동할 수 있는 경우, 최소 거리르 계산해서 인접리스트에 넣기
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					int curLand = map[i][j];
					for (int k = 0; k < 4; k++) {
						int dis = 0;
						int y = i;
						int x = j;
						while (true) {
							int nextY = y + dy[k];
							int nextX = x + dx[k];
							
							if(nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || map[nextY][nextX] == curLand) {
								break;
							}
							
							if(map[nextY][nextX] != 0) {
								if(dis >= 2) {
									landList[curLand][map[nextY][nextX]] = Math.min(landList[curLand][map[nextY][nextX]], dis);
									landList[map[nextY][nextX]][curLand] = Math.min(landList[map[nextY][nextX]][curLand], dis);
								}
								break;
							}
							
							dis++;
							y = nextY;
							x = nextX;
						}
						
					}
				}
					
			}
		}
		
		
		//인접리스트를 사용해서 최소신장 트리 구하기
		int mst = 0, cnt = 0;
		int[] prim = new int[landCount+1];
		Arrays.fill(prim, Integer.MAX_VALUE);
		boolean[] v = new boolean[landCount+1];
		prim[1] = 0;
		for (int i = 1; i <= landCount; i++) {
			int minVertex = -1;
			int min = Integer.MAX_VALUE;
			
			for (int j = 1; j <= landCount; j++) {
				if (!v[j] && prim[j] < min) {
					minVertex = j;
					min = prim[j];
				}
			}
			if (minVertex==-1) {
				mst = -1;
				break;
			}
			mst += min;
			v[minVertex] = true;
			if(cnt++ == landCount-1) break;
			
			for (int j = 1; j <= landCount; j++) {
				if(!v[j] && landList[minVertex][j] < prim[j]) {
					prim[j] = landList[minVertex][j];
				}
			}
		}
		
		
		System.out.println(mst);
	}
}
