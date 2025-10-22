//package BOJ.경쟁적전염;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		ArrayDeque<int[]>[] virus = new ArrayDeque[K+1]; for(int i=1; i<=K; i++) virus[i] = new ArrayDeque<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) continue;
				virus[map[i][j]].add(new int[] {i, j});
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken())-1;
		int X = Integer.parseInt(st.nextToken())-1;
		
		
		for (int time=0; time<S; time++) {
			for (int i=1; i<=K; i++) {
				if(virus[i].isEmpty()) continue;
				
				int iter = virus[i].size();
				for (int j=0; j<iter; j++) {
					int[] now = virus[i].poll();
					int y = now[0];
					int x = now[1];
					for (int k=0; k<4; k++) {
						int ny = y+dy[k];
						int nx = x+dx[k];
						if(ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx]!=0) continue;
						
						virus[i].add(new int[] {ny, nx});
						map[ny][nx] = i;
					}
				}
			}
//			for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
//			System.out.println();
		}
		System.out.println(map[Y][X]);
	}
}

/*
`
*/