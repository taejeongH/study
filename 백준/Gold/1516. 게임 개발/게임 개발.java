//package BOJ.게임개발;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = null;
		int N = Integer.parseInt(br.readLine());
		List<Integer>[] g = new List[N+1]; for(int i=1; i<=N; i++) g[i] = new ArrayList<>();
		int[] inDegree = new int[N+1];
		int[] cost = new int[N+1];
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i] = Integer.parseInt(st.nextToken());
			while(true) {
				int nxt = Integer.parseInt(st.nextToken());
				if(nxt==-1) break;
				
				g[nxt].add(i);
				inDegree[i]++;
			}
		}
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		int[] res = new int[N+1];
		for (int i=1; i<=N; i++) {
			if (inDegree[i]==0) {
				res[i] = cost[i];
				que.add(new int[] {i, cost[i]}); 
			}
		}
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int id = now[0];
			int time = now[1];
			
			for (int nxt : g[id]) {
				res[nxt] = Math.max(time+cost[nxt], res[nxt]);
				if(--inDegree[nxt]==0) {
					que.add(new int[] {nxt, res[nxt]});
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			sb.append(res[i]).append("\n");
		}
		
		System.out.print(sb);
	}
}

/*
	위상정렬
	
*/