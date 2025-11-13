//package BOJ.불;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int tc = Integer.parseInt(br.readLine());
		for (int t=1; t<=tc; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			boolean[][] map = new boolean[N][M];
			ArrayDeque<int[]> que = new ArrayDeque<>();
			for (int i=0; i<N; i++) {
				String input = br.readLine();
				for (int j=0; j<M; j++) {
					char cur = input.charAt(j);
					if (cur == '#') {
						map[i][j]=true;
					} else if(cur =='@') {
						que.addLast(new int[] {i, j, 1});
					} else if(cur=='*') {
						map[i][j] = true;
						que.addFirst(new int[] {i, j, -1});
					}
				}
			}
			
			int res = -1;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				int dis = now[2];
				
				if(dis!=-1 && (y==N-1 || y==0 || x==M-1 || x==0)) {
					res = dis;
					break;
				}
				
				for (int i=0; i<4; i++) {
					int ny = y+dy[i];
					int nx = x+dx[i];
					
					if(ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx]) continue;
					
					map[ny][nx]=true;
					que.add(new int[] {ny, nx, dis==-1?-1:dis+1});
				}
			}
			
			sb.append(res==-1?"IMPOSSIBLE":res).append("\n");
		}
		System.out.print(sb.toString());
	}
}
