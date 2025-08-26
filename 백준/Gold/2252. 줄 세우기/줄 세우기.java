//package BOJ.줄세우기2252;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] g = new List[N+1]; for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
		int[] inDegree = new int[N+1];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s].add(e);
			inDegree[e]++;
		}
		
		ArrayDeque<Integer> que = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
			if (inDegree[i]==0) que.add(i);
		}
		
		while(!que.isEmpty()) {
			int now = que.poll();
			
			sb.append(now).append(" ");
			for (int i=0; i<g[now].size(); i++) {
				if(--inDegree[g[now].get(i)] == 0) {
					que.add(g[now].get(i));
				}
			}
		}
		System.out.println(sb.toString());
	}
}
