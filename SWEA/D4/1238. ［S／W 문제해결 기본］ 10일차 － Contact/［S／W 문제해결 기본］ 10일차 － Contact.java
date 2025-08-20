//package SWEA.콘택트1238;

import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/SWEA/콘택트1238/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = 10;
		for (int test=1; test<=testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //
			int M = Integer.parseInt(st.nextToken()); //시작 정점
			
			ArrayList<Integer>[] g = new ArrayList[101];
			for (int i=1; i<101; i++) g[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				g[from].add(to);
			}
			
			ArrayDeque<int[]> que = new ArrayDeque<>();
			que.add(new int[] {M, 0});
			boolean[] visited = new boolean[101];
			int max = 0;
			int maxNode = -1;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int node = now[0];
				int d = now[1];
				
				if (d >= max) {
					if (max == d) {
						maxNode = Math.max(maxNode, node);
					} else {
						max = d;
						maxNode = node;
					}
				}
				
				for (int i=0; i<g[node].size(); i++) {
					if(!visited[g[node].get(i)]) {
						visited[g[node].get(i)] = true;
						que.add(new int[] {g[node].get(i), d+1});
					}
				}
			}
			
			sb.append("#").append(test).append(" ").append(maxNode).append("\n");
		}
		System.out.println(sb.toString());
	}
}
