//package BOJ.로봇청소기;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			if(N==0 && M==0) break;
			int[][] map = new int[N][M];
			int[] start = new int[2];
			int dirtyCnt = 0;
			for (int i=0; i<N; i++) {
				String input = br.readLine();
				for (int j=0; j<M; j++) {
					char c = input.charAt(j);
					if(c == 'o') {
						map[i][j] = 0;
						start[0]=i;
						start[1]=j;
					} else if(c == '*') {
						map[i][j] = ++dirtyCnt;
					} else if(c == 'x') {
						map[i][j] = -1;
					}
				}
			}
			ArrayDeque<int[]> que = new ArrayDeque<>();
			que.add(new int[] {start[0], start[1], 0, 0, 0}); 
			boolean[][][] v = new boolean[N][M][1<<dirtyCnt];
			v[start[0]][start[1]][0] = true; 
			int res=-1;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				int dis = now[2];
				int cnt = now[3];
				
				if(cnt==(1 << dirtyCnt) - 1) {
					res = dis;
					break;
				}
				
				for (int i=0; i<4; i++) {
					int ny = y+dy[i];
					int nx = x+dx[i];
					
					if(ny>=0 && ny<N && nx>=0 && nx<M) {
						if (map[ny][nx]>=1) {
							int newCnt = cnt | (1 << (map[ny][nx]-1));
							if(!v[ny][nx][newCnt]) {
								v[ny][nx][newCnt] = true;
								que.add(new int[] {ny, nx, dis+1, newCnt});
							}
						} else if(map[ny][nx]==0 && !v[ny][nx][cnt]) {
							v[ny][nx][cnt] = true;
							que.add(new int[] {ny, nx, dis+1, cnt});
						}
					}
				}
				
			}
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}
	
}
