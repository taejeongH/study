//package BOJ.ACMCraft;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input4.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //건물 개수
			int K = Integer.parseInt(st.nextToken()); //건설 순서 규칙 개수
			
			int[] map = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<N+1; i++) map[i] = Integer.parseInt(st.nextToken());
			
			int[] inDegree = new int[N+1];
			List<Integer>[] g = new List[N+1]; for(int i=1; i<N+1; i++) g[i] = new ArrayList<>();
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				g[s].add(e);
				inDegree[e]++;
			}
			
			int W = Integer.parseInt(br.readLine());
			ArrayDeque<int[]> que = new ArrayDeque<>();
			for (int i=1; i<N+1; i++) {
				if (inDegree[i]==0) que.add(new int[] {i, 0});
			}
			
			int[] distance = new int[N+1];
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int node = now[0];
				int dis = now[1];
				for (int i=0; i<g[node].size(); i++) {
					int nxt = g[node].get(i);
					distance[nxt] = Math.max(dis+map[node], distance[nxt]);
					if (--inDegree[nxt]==0) que.add(new int[] {nxt, distance[nxt]});
				}
			}
			sb.append(distance[W]+map[W]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
