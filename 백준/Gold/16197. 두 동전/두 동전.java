//package BOJ.두동전;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		ArrayDeque<int[]> que = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j]=='o') {
					que.add(new int[] {i, j});
				}
			}
		}
		int[] first = que.poll();
		int[] second = que.poll();
		que.add(new int[] {first[0], first[1], second[0], second[1], 0});
		int res = -1;
		nxt: while(!que.isEmpty()) {
			int[] now = que.poll();
			int y1 = now[0];
			int x1 = now[1];
			
			int y2 = now[2];
			int x2 = now[3];
			
			int dis = now[4];
			if(dis >= 10) {
				break;
			}
			
			for (int i=0; i<4; i++) {
				int ny1 = y1+dy[i];
				int nx1 = x1+dx[i];
				int ny2 = y2+dy[i];
				int nx2 = x2+dx[i];
				boolean f = isFall(ny1, nx1);
				boolean s = isFall(ny2, nx2);
				if(f != s) {
					//하나만 빠져나가는 경우
					res = dis+1;
					break nxt;
				} else {
					if (f&&s) {
						//둘 다 안빠져나가는 경우
						if (map[ny1][nx1]=='#') {
							ny1 = y1;
							nx1 = x1;
						}
						if(map[ny2][nx2]=='#') {
							ny2 = y2;
							nx2 = x2;
						}
						que.add(new int[] {ny1, nx1, ny2, nx2, dis+1});
					} else {
						// 둘다 빠져나가는 경우
						continue;
					}
				}

			}
		}
		
		System.out.println(res);
	}
	
	public static boolean isFall(int ny, int nx) {
		if (ny<0 || ny>=N || nx<0 || nx>=M) return false;
		return true;
	}
}


/*
	하나만 빠져나갸아 함
	
	
*/