//package BOJ.작업;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		List<Integer>[] g = new List[N+1]; for (int i=0; i<N+1; i++) g[i]=new ArrayList<>();
		int[] inDegree = new int[N+1];
		int[] time = new int[N+1];
		
		for (int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			
			int beforeCount = Integer.parseInt(st.nextToken());
			
			for (int j=0; j<beforeCount; j++) {
				int before = Integer.parseInt(st.nextToken());
				
				g[before].add(i);
				inDegree[i]++;
			}
		}
		
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));		
		for (int i=1; i<N+1; i++) {
			if (inDegree[i]==0) {
				que.add(new int[] {i, time[i]});
			}
		}
		
		int res = 0 ;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int node = now[0];
			int dis = now[1];
			res = Math.max(dis, res);
			
			for (int nxt : g[node]) {
				if(--inDegree[nxt]==0) {
					que.add(new int[] {nxt, dis+time[nxt]});
				}
				
			}
		}
		
		System.out.println(res);
	}
}
