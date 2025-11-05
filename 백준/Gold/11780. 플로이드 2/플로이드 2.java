//package BOJ.플로이드2;

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 1_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] g = new int[N+1][N+1];
		
		for (int i=0; i<N+1; i++) Arrays.fill(g[i], INF);
		
		int M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			g[s][e] = Math.min(c, g[s][e]);
		}
//		for (int i=1; i<N+1; i++) System.out.println(Arrays.toString(g[i]));
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		
		int[][] prev = new int[N+1][N+1];
		int[][] distance = new int[N+1][N+1];
		for (int i=0; i<N+1; i++) Arrays.fill(distance[i], INF);
		for (int i=1; i<N+1; i++) {
			pq.add(new int[] {i, 0, 0});
			distance[i][i] = 0;
			while(!pq.isEmpty()) {
				int[] now = pq.poll();
				int node = now[0];
				int dis = now[1];
				int pre = now[2];
				
				if(dis > distance[i][node]) continue;
				prev[i][node] = pre;
				
				for (int j=1; j<N+1; j++) {
					if (dis+g[node][j] < distance[i][j]) {
						distance[i][j] = dis+g[node][j];
						pq.add(new int[] {j, dis+g[node][j], node});
					}
					
				}
			}
			
		}
		
//		for (int i=1; i<N+1; i++) {
//			for (int j=1; j<N+1; j++) {
//				System.out.print(prev[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<N+1; i++) {
			for (int j=1; j<N+1; j++) {
				if(distance[i][j]==INF) sb.append(0).append(" ");
				else sb.append(distance[i][j]).append(" ");
			}
			sb.append("\n");
		}
		ArrayDeque<Integer> que = new ArrayDeque<>();
		for (int i=1; i<N+1; i++) {
			for (int j=1; j<N+1; j++) {
				int nxt = prev[i][j];
				if(nxt == 0) sb.append(0);
				else {
					que.add(j);
					while (nxt != 0) {
						que.add(nxt);
						nxt = prev[i][nxt];
					}
					
					sb.append(que.size()).append(" ");
					while(!que.isEmpty()) {
						sb.append(que.pollLast()).append(" ");
					}
				}
				sb.append("\n");
			}
		}
		
		System.out.print(sb);
	}
	
}

/*
	노드 100
	간선 100,000
	
	v^3 플로이드워셜 하면 간단하긴한데 거쳐간 도시를 어케구함
	
*/