//package BOJ.도시분할계획1647;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //집 개수
		int M = Integer.parseInt(st.nextToken()); //길 개수
		List<int[]>[] g = new List[N+1]; for (int i=1; i<N+1; i++) g[i] = new ArrayList<int[]>();
		boolean[] visited = new boolean[N+1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			g[start].add(new int[] {end, cost});
			g[end].add(new int[] {start, cost});
		}
		
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		int max = 0, mst = 0;
		que.add(new int[] {1, 0});
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int minVertex = now[0];
			int min = now[1];
			
			if(visited[minVertex]) continue;
			mst += min;
			visited[minVertex] = true;
			max = Math.max(min, max);
			
			for (int[] vc : g[minVertex]) {
				if (!visited[vc[0]]) {
					que.add(new int[] {vc[0], vc[1]});
				}
			}
		}
		
		
		
		System.out.println(mst-max);
	}
}
