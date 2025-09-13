//package BOJ.치즈;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ArrayDeque<int[]> que = new ArrayDeque<>();
		int time = 0;
		while(true) {
			boolean isRemoved = true;
			que.add(new int[] {0, 0});
			boolean[][][] v = new boolean[N][M][4];
			for (int i=0; i<4; i++) v[0][0][i]=true;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				
				for (int i=0; i<4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					
					if(ny>=0 && ny<N && nx>=0 && nx<M){
						if (map[ny][nx]==0 && !v[ny][nx][0]) {
							v[ny][nx][0] = true;
							que.add(new int[] {ny, nx});
						} else if(map[ny][nx]==1 && !v[ny][nx][i]) {
							v[ny][nx][i]=true;
							isRemoved = false;
							for (int j=0; j<4; j++) {
								if(i==j) continue;
								if (v[ny][nx][j]) {
									map[ny][nx]=0;
									v[ny][nx][0]=true;
									break;
								}
							}
						}
					}
				}
			}
//			for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
			if(isRemoved) break;
			time++;
		}
		System.out.println(time);
	}
}
