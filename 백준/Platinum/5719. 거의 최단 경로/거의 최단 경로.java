//package BOJ.거의최단경로;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<int[]>[] g;
	static boolean[][] remove;
	static List<Integer>[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N==0) break;
			
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			g = new List[N]; for(int i=0; i<N; i++) g[i]=new ArrayList<>();
			parent = new List[N]; for(int i=0; i<N; i++) parent[i]=new ArrayList<>();
			remove = new boolean[N][N];
			
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int U = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				g[U].add(new int[] {V, P});
			}
			
			int[] distance = new int[N];
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[S] = 0;
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
			pq.add(new int[] {S, 0});
			
			while(!pq.isEmpty()) {
				int[] now = pq.poll();
				int node = now[0];
				int dis = now[1];
				if(dis > distance[node]) continue;
				
				for (int[] nxt : g[node]) {
					if(distance[nxt[0]] > nxt[1]+dis) {
						distance[nxt[0]]=nxt[1]+dis;
						parent[nxt[0]].clear();
						parent[nxt[0]].add(node);
						pq.add(new int[] {nxt[0], distance[nxt[0]]});
					} else if(distance[nxt[0]] == nxt[1]+dis) {
						parent[nxt[0]].add(node);
					}
				}
			}
			
			findPath(D, S);
			
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[S] = 0;
			pq.add(new int[] {S, 0});
			while(!pq.isEmpty()) {
				int[] now = pq.poll();
				int node = now[0];
				int dis = now[1];
				if(dis > distance[node]) continue;
				
				for (int[] nxt : g[node]) {
					if(remove[node][nxt[0]]) continue;
					
					if(distance[nxt[0]] > nxt[1]+dis) {
						distance[nxt[0]]=nxt[1]+dis;
						pq.add(new int[] {nxt[0], distance[nxt[0]]});
					}
				}
			}
			sb.append(distance[D]==Integer.MAX_VALUE?-1:distance[D]).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void findPath(int cur, int start) {
		ArrayDeque<Integer> que = new ArrayDeque<>();
		que.add(cur);
		while(!que.isEmpty()) {
			int now = que.poll();
			
			for (int nxt : parent[now]) {
				if(!remove[nxt][now]) {
					remove[nxt][now] = true;
					que.add(nxt);
				}
			}
		}
		
	}
	
}

/*
	거의 최단 경로 = 최단 경로가 아닌 길 중 가장 최단 경로
*/