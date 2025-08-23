//package BOJ.최소스패닝트리1197;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		List<int[]>[] g = new List[V+1];
		for (int i=0; i<V+1; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
//			System.out.println(cost);
			
			g[start].add(new int[] {end, cost});
			g[end].add(new int[] {start, cost});
		}
		int[] prim = new int[V+1];
		Arrays.fill(prim, Integer.MAX_VALUE);
		
		int mst=0, cnt=0;
		boolean[] visited = new boolean[V+1];
		
		prim[1]=0;
		for (int i=1; i<V+1; i++) {
			int minVertex = -1;
			int min = Integer.MAX_VALUE;
			for (int j=1; j<V+1; j++) {
				if (!visited[j] && min > prim[j]) {
					minVertex = j;
					min = prim[j];
				}
			}
			
			mst += min;
			visited[minVertex] = true;
			if(cnt++ == V-1) break;
			
			for (int[] jc : g[minVertex]) {
				if (!visited[jc[0]] && jc[1] < prim[jc[0]]) {
					prim[jc[0]] = jc[1];
				}
			}
		}
		
		System.out.println(mst);
		
	}
}
