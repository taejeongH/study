//package BOJ.문제집;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] g = new List[N+1]; for(int i=1; i<N+1; i++) g[i] = new ArrayList<>();
		
		int[] inDegree = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			g[start].add(end);
			inDegree[end]++;
		}
		PriorityQueue<Integer> que = new PriorityQueue<>();
		for (int i=1; i<N+1; i++) {
			if (inDegree[i]==0) 
				que.add(i);
		}
		StringBuilder sb = new StringBuilder();
		while(!que.isEmpty()) {
			int now = que.poll();
			sb.append(now).append(" ");
			for (int i=0; i<g[now].size(); i++) {
				int nxt = g[now].get(i);
				if(--inDegree[nxt]==0) que.add(nxt);
			}
		}
		System.out.println(sb.toString());
	}
}
