//package SWEA.키순서5643;

import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/SWEA/키순서5643/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int test=1; test<=testCase; test++) {
			int N = Integer.parseInt(br.readLine().trim());
			int M = Integer.parseInt(br.readLine().trim());
			List<Integer>[] g = new List[N+1]; for(int i=1; i<N+1; i++) g[i] = new ArrayList<>();
			for (int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				g[start].add(end);
			}
			int[] distance = new int[N+1];
			ArrayDeque<int[]> que = new ArrayDeque<>();
			
			for (int i=1; i<N+1; i++) {
				que.add(new int[] {i,0});
				boolean[] visited = new boolean[N+1];
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int node = now[0];
					int dis = now[1];
					
					for (int j=0; j<g[node].size(); j++) {
						if(!visited[g[node].get(j)]) {
							visited[g[node].get(j)]=true;
							distance[g[node].get(j)]++;
							que.add(new int[] {g[node].get(j), dis+1});
						}
					}
				}
			}
			
			for (int i=1; i<N+1; i++) {
				que.add(new int[] {i,0});
				boolean[] visited = new boolean[N+1];
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int node = now[0];
					int dis = now[1];
					
					for (int j=0; j<g[node].size(); j++) {
						if(!visited[g[node].get(j)]) {
							visited[g[node].get(j)]=true;
							distance[i]++;
							que.add(new int[] {g[node].get(j), dis+1});
						}
					}
				}
			}
			int result = 0;
			for(int i=1; i<N+1; i++) {
				if(distance[i] >= N-1) result++;
			}
			
			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
}
