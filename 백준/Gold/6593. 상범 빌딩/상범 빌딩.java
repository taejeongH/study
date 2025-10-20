//package BOJ.상범빌딩;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());//층의 개수
			int R = Integer.parseInt(st.nextToken());//y
			int C = Integer.parseInt(st.nextToken());//x
			
			if(L==0) break;
			
			char[][][] map = new char[L][R][C];
			
			
			ArrayDeque<int[]> que = new ArrayDeque<>();
			int endi = -1;
			int endj = -1;
			int endk = -1;
			for (int i=0; i<L; i++) {
				for (int j=0; j<R; j++) {
					String input = br.readLine();
					for (int k=0; k<C; k++) {
						map[i][j][k] = input.charAt(k);
						if(map[i][j][k]=='S') que.add(new int[] {i, j, k, 0});
						else if(map[i][j][k]=='E') {
							endi=i;
							endj=j;
							endk=k;
						}
					}
				}
				br.readLine();
			}
			boolean[][][] visited = new boolean[L][R][C];
			int[] start = que.peek();
			visited[start[0]][start[1]][start[2]] = true;
			
			int res = -1;
			
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int z = now[0];
				int y = now[1];
				int x = now[2];
				int dis = now[3];
				
				if(z==endi && y==endj && x==endk) {
					res = dis;
					break;
				}
				
				for (int i=0; i<4; i++) {
					int nx = x+dx[i];
					int ny = y+dy[i];
					
					if(ny<0 || ny>=R || nx<0 || nx>=C || visited[z][ny][nx] || map[z][ny][nx]=='#') continue;
					
					visited[z][ny][nx] = true;
					que.add(new int[] {z, ny, nx, dis+1});
				}
				
				if(z+1<L && !visited[z+1][y][x] && map[z+1][y][x]!='#') {
					visited[z+1][y][x] = true;
					que.add(new int[] {z+1, y, x, dis+1});
				}
				
				if(z-1 >= 0 && !visited[z-1][y][x] && map[z-1][y][x]!='#') {
					visited[z-1][y][x] = true;
					que.add(new int[] {z-1, y, x, dis+1});
				}
			}
			
			if(res==-1) {
				sb.append("Trapped!").append("\n");
			} else {
				sb.append("Escaped in ").append(res).append(" minute(s).\n");
			}
		}
		System.out.println(sb);
	}
}

/*
	
*/