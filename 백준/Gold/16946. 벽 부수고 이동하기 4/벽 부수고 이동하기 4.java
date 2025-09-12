//package BOJ.벽부수고이동하기4;

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
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j]=input.charAt(j)-'0';
			}
		}
		
		StringBuilder sb = new StringBuilder();
		ArrayDeque<int[]> que = new ArrayDeque<>();
		int[][] res = new int[N][M];
		boolean[][] v = new boolean[N][M];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]==1) {
					res[i][j] += 1;
					continue;
				}
				if(v[i][j]) continue;
				que.add(new int[] {i, j});
				v[i][j]=true;
				int dis = 1;
				ArrayList<int[]> pos = new ArrayList<>();
				Set<Integer> check = new HashSet<>();
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int y = now[0];
					int x = now[1];
					
					for (int k=0; k<4; k++) {
						int ny = y+dy[k];
						int nx = x+dx[k];
						if(ny>=0 && ny<N && nx>=0 && nx<M && !v[ny][nx]) {
							if(map[ny][nx] == 1 && !check.contains(ny*M+nx)) {
								check.add(ny*M+nx);
								pos.add(new int[] {ny, nx});
							} else if (map[ny][nx]==0){
								v[ny][nx]=true;
								que.add(new int[] {ny, nx});
								dis++;
							}
						}
					}
				}
				for (int[] p : pos) {
					res[p[0]][p[1]] += dis;
				}
			}
		}
		
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				sb.append(res[i][j]%10);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
