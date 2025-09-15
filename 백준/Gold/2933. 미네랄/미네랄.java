//package BOJ.미네랄;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static int N, M;
	static char[][] org;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		org = new char[N][M];
		map = new int[N][M];
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				org[i][j] = input.charAt(j);
			}
		}
		int preCluster = floodFill();
		
		int K = Integer.parseInt(br.readLine());
		int[] order = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
			int y = N - Integer.parseInt(st.nextToken());
			
			int curCluster = -1;

			if (i%2==0) {
				for (int j=0; j<M; j++) {
					if (org[y][j] == 'x') {
						org[y][j]='.';
						break;
					}
				}
			} else {
				for (int j=M-1; j>=0; j--) {
					if (org[y][j]=='x') {
						org[y][j]='.';
						break;
					}
				}
			}
			curCluster = floodFill();
			gravity(curCluster);
			
			preCluster = curCluster;
//			for (int j=0; j<N; j++) System.out.println(Arrays.toString(org[j]));
//			System.out.println();
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				sb.append(org[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
		
	}
	
	public static int floodFill() {
		boolean[][] v = new boolean[N][M];
		int idx = 1;
		ArrayDeque<int[]> que = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (org[i][j]!='x') {
					map[i][j]=0;
					continue;
				}
				
				if(v[i][j]) continue;
				v[i][j]=true;
				que.add(new int[] {i, j});
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int y = now[0];
					int x = now[1];
					
					map[y][x] = idx;
					for (int k=0; k<4; k++) {
						int ny = y+dy[k];
						int nx = x+dx[k];
						if(ny>=0 && ny<N && nx>=0 && nx<M && !v[ny][nx] && org[ny][nx] == 'x') {
							v[ny][nx]=true;
							que.add(new int[] {ny, nx});
						}
					}
				}
				
				idx++;
			}
		}
		
		return idx-1;
	}
	
	public static void gravity(int cluster) {
		int[] minDown = new int[cluster+1];
		Arrays.fill(minDown, Integer.MAX_VALUE);
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]==0) continue;
				int down = 0;
				int curCluster = map[i][j];
				int y = i;
				
				while(y<N-1) {
					if(map[y+1][j] == curCluster) {
						down = Integer.MAX_VALUE;
						break;
					}
					if (map[y+1][j]!=0) break;
					y++;
					down++;
				}
				minDown[curCluster] = Math.min(minDown[curCluster], down);
			}
		}
		
		
		for (int i=N-1; i>=0; i--) {
			for (int j=0; j<M; j++) {
				if (map[i][j]==0) continue;
				int curCluster = map[i][j];
				int down = minDown[curCluster];
				if(down == Integer.MAX_VALUE) continue;
				if(down == 0) continue;
				map[i+down][j] = map[i][j];
				org[i+down][j] = 'x';
				map[i][j] = 0;
				org[i][j] = '.';
			}
		}
		
	} 
}
