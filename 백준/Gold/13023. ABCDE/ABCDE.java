//package BOJ.ABCDE;

import java.io.*;
import java.util.*;

public class Main {
	static int res;
	static boolean[] visited;
	static List<Integer>[] g;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		g = new List[N]; for (int i=0; i<N; i++) g[i] = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s].add(e);
			g[e].add(s);
		}
		res = 0;
		for (int i=0; i<N; i++) {
			visited = new boolean[N];
			if(dfs(i, 0)) break;
		}
		System.out.println(res);
	}
	
	public static boolean dfs(int node, int depth) {
		if (depth==5) {
			res = 1;
			return true;
		}
		
		for (int i=0; i<g[node].size(); i++) {
			int nxt = g[node].get(i);
			if(!visited[nxt]) {
				visited[nxt]=true;
				if (dfs(nxt, depth+1)) return true;
				visited[nxt]=false;			}
		}
		
		return false;
	}
}
