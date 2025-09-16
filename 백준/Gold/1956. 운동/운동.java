//package BOJ.운동;

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int[][] g = new int[V+1][V+1];
		for (int i=1; i<V+1; i++) Arrays.fill(g[i], INF);
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			g[s][e] = c;
		}
		
//		for (int i=1; i<V+1; i++) System.out.println(i + " " + Arrays.toString(g[i]));
		
		int res = INF;
		
		for (int k=1; k<V+1; k++) {
			for (int i=1; i<V+1; i++) {
				for (int j=1; j<V+1; j++) {
					g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
				}
			}
		}
//		for (int i=1; i<V+1; i++) System.out.println(i + " " + Arrays.toString(g[i]));
		
		
		for (int i=1; i<V+1; i++) {
			for (int j=1; j<V+1; j++) {
				if (g[i][j] != INF && g[j][i] != INF) {
					res = Math.min(g[i][j]+g[j][i], res);
				}
			}
		}
		
		
		if(res==INF) System.out.println(-1);
		else System.out.println(res);
	}
}

/* 
*/