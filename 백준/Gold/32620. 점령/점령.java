//package BOJ.점령;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		List<int[]>[] g = new List[N+1]; for(int i=1; i<=N; i++) g[i] = new ArrayList<>();
		int[] A = new int[N+1];
		int[] B = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s].add(new int[] {e, A[e], B[e]});
			g[e].add(new int[] {s, A[s], B[s]});
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) return o2[2]-o1[2];
				return o1[1]-o2[1];
			}
		});
		
		pq.add(new int[] {R, A[R], B[R]});
		
		boolean[] visited = new boolean[N+1];
		long energy = 0;
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int node = now[0];
			
			if (energy < now[1] || visited[node]) continue;
			energy+=now[2];
			visited[node]=true;
			
			for (int[] nxt : g[node]) {
				pq.add(new int[] {nxt[0], nxt[1], nxt[2]});
			}
		}
		
		System.out.println(energy);
		
	}
}


/*
	양방향 그래프와 시작 노드 r이 주어짐
	i번 노드를 점령하려면, Ai 이상의 기력을 보유하고 있으면서(감소x) i번 노드의 이웃 노드를 하나 이상 점령하고 있어야 함
	i번 노드를 점령하면 보유한 기력이 Bi만큼 증가
	시작노드는 이미 점령된 상태
	
	노드의개수 N, 간선의 개수 M, 시작노드 r
	A1, A2, ...Ai, i번째 노드를 점령하기 위해 필요한 기력
	
	
*/