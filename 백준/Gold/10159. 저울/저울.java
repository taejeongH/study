//package BOJ.저울;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		List<Integer>[] g = new List[N+1]; for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
		List<Integer>[] reverse = new List[N+1]; for (int i=1; i<=N; i++) reverse[i] = new ArrayList<>();
		int M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s].add(e);
			reverse[e].add(s);
		}
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			sb.append(N-1 - (bfs(g, i) + bfs(reverse, i))).append("\n");
		}

		System.out.print(sb);
	}
	
	public static int bfs(List<Integer>[] g, int start) {
		ArrayDeque<Integer> que = new ArrayDeque<>();
		boolean[] v = new boolean[N+1];
		v[start]= true;
		que.add(start);
		int cnt = 0;
		while(!que.isEmpty()) {
			int now = que.poll();
			for (int nxt : g[now]) {
				if(v[nxt]) continue;
				v[nxt]=true;
				que.add(nxt);
				cnt++;
			}
		}
		return cnt;
	}
}
