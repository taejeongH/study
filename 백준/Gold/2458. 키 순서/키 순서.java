//package BOJ.키순서;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] g = new List[N+1];
		List<Integer>[] reverse = new List[N+1];
		
		for (int i=1; i<=N; i++) {
			g[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s].add(e);
			reverse[e].add(s);
		}
		
		int res=0;
		for (int i=1; i<=N; i++) {
			if(bfs(g, i) + bfs(reverse, i) == N-1) res++;
		}
		System.out.println(res);
	}
	
	public static int bfs(List<Integer>[] g, int start) {
		ArrayDeque<Integer> que = new ArrayDeque<>();
		boolean[] v = new boolean[N+1];
		que.add(start);
		v[start] = true;
		int sum = 0;
		while(!que.isEmpty()) {
			int now = que.poll();
			
			for (int nxt : g[now]) {
				if(v[nxt]) continue;
				sum++;
				v[nxt]=true;
				que.add(nxt);
			}
			
		}
		return sum;
	}
}
