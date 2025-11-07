//package BOJ.중량제한;

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //섬 개수
		int M = Integer.parseInt(st.nextToken()); //다리 개수
		
		List<int[]>[] g = new List[N+1]; for (int i=1; i<N+1; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			g[s].add(new int[] {e, c});
			g[e].add(new int[] {s, c});
		}
		
		st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());
		
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[2], o1[2]));
		pq.add(new int[] {from, 0, INF});
		
		int[] distance = new int[N+1];
		Arrays.fill(distance, -1);
		distance[from] = 0;
		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			int node = now[0];
			int dis = now[1];
			int min = now[2];
			//System.out.println(node);
			if(node == to) {
				System.out.println(min);
				break;
			}
			
			for (int[] nxt : g[node]) {
				if (nxt[1] > distance[nxt[0]]) {
					distance[nxt[0]] = nxt[1];
					pq.add(new int[] {nxt[0], dis+nxt[1], Math.min(min, nxt[1])});
				}
				
				
			}
			
			
		}
 	}
}


/*
	N개의 노드
	
	A -> B 최대 거리로 이동해야 하고, 이동한 간선 들 중 최소 간선을 찾아야 함 
	
	
	
	
*/