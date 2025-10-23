//package BOJ.전력난;

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());//노드 수
			int N = Integer.parseInt(st.nextToken());//길의 수
			
			if(M==0) break;
			List<int[]>[] g = new List[M]; for(int i=0; i<M; i++) g[i] = new ArrayList<>();
			
			long costSum = 0;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				g[s].add(new int[] {e, c});
				g[e].add(new int[] {s, c});
				costSum += c;
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
			pq.add(new int[] {0, 0});
			boolean[] v = new boolean[M];
			long mst = 0;
			while(!pq.isEmpty()) {
				int[] now = pq.poll();
				int node = now[0];
				int dist = now[1];
				
				if(v[node]) continue;
				v[node] = true;
				mst += dist;
				
				for (int[] nxt : g[node]) {
					if (!v[nxt[0]]) {
						pq.add(new int[] {nxt[0], nxt[1]});
					}
				}
			}

			sb.append(costSum-mst).append("\n");
		}
		System.out.print(sb);
	}
}


/*
	원래 켜져있던 가로등 중 일부를 소등
	길의 가로등을 켜두면 미터수만큼 돈이 듦
	두 집을 왕래할 때, 불이 켜져있지 않는 길을 반드시 지나야한다면 위험
	모든 두 집 쌍에 대해, 불이 켜진 길만으로 서로를 왕래할 수 있어야 함
	위 조건을 지키면서 절약할 수 있는 최대 액수
	집의 수 m, 길의 수 n
	

	전체 비용 - 모든 두 집 쌍을 이동할 수 있는 도로의 최소 거리
	prim?
	
	

*/