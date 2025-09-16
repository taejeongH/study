//package BOJ.운동;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		List<int[]>[] g = new List[V+1]; for(int i=1; i<V+1; i++) g[i] = new ArrayList<>();
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			g[s].add(new int[] {e, c});
		}
		
		int res = Integer.MAX_VALUE;
		for (int i=1; i<V+1; i++) {
			PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
			int[] distance = new int[V+1];
			Arrays.fill(distance, Integer.MAX_VALUE);
			que.add(new int[] {i, 0});
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int node = now[0];
				int dis = now[1];
				
				if(dis > distance[node]) continue;
				
				if (dis!=0 && node==i) {
					res = Math.min(res, dis);
					break;
				}
				
				for (int[]jc : g[node]) {
					if(distance[jc[0]] > dis + jc[1]) {
						distance[jc[0]] = dis+jc[1];
						que.add(new int[] {jc[0], distance[jc[0]]});
					}
				}
			}
		}
		
		if(res==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(res);
	}
}

/* 
*/