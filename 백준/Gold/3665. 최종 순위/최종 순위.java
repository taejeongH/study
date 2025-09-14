//package BOJ.최종순위;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(br.readLine());
			
			int [][] g = new int[N+1][N+1];
			st = new StringTokenizer(br.readLine());
			int[] inDegree = new int[N+1];
			int[] map = new int[N+1];
			for (int i=1; i<N+1; i++) {
				int nxt = Integer.parseInt(st.nextToken());
				map[i] = nxt;
			}
			
			for (int i=1; i<N+1; i++) {
				for (int j=i+1; j<N+1; j++) {
					g[map[i]][map[j]] = 1;
					inDegree[map[j]]++;
				}
			}
			
			int M = Integer.parseInt(br.readLine());
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				if(g[s][e] == 1) {
					g[s][e] = 0;
					g[e][s] = 1;
					inDegree[e]--;
					inDegree[s]++;
				} else {
					g[e][s] = 0;
					g[s][e] = 1;
					inDegree[s]--;
					inDegree[e]++;
				}
			}
			
			ArrayDeque<Integer> que = new ArrayDeque<>();
			for (int i=1; i<N+1; i++) {
				if (inDegree[i]==0) que.add(i);
			}
			
			if(que.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				continue;
			}
			
			int find = 0;
			
			while(!que.isEmpty()) {
				int now = que.poll();
				find++;
				sb.append(now).append(" ");
				for (int i=1; i<N+1; i++) {
					if (g[now][i] == 1) {
						if (--inDegree[i]==0) que.add(i);
					}
				}
			}
			sb.append("\n");
			if(find == N) System.out.print(sb);
			else System.out.println("IMPOSSIBLE");
		}
	}
}
