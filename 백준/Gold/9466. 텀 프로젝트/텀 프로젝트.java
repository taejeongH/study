//package BOJ.텀프로젝트;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			int N = Integer.parseInt(br.readLine());
			List<Integer>[] g = new List[N+1]; for (int i=1; i<N+1; i++) g[i]=new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int[] inDegree = new int[N+1];
			for (int i=1; i<N+1; i++) {
				int e = Integer.parseInt(st.nextToken());
				g[i].add(e);
				inDegree[e]++;
			}
			ArrayDeque<Integer> que = new ArrayDeque<>();
			for (int i=1; i<N+1; i++) {
				if (inDegree[i]==0) que.add(i);
			}
			
			int res = 0;
			while(!que.isEmpty()) {
				int now = que.poll();
				res++;
				for (int i=0; i<g[now].size(); i++) {
					if(--inDegree[g[now].get(i)] == 0) {
						que.add(g[now].get(i));
					}
				}
			}
			sb.append(res).append("\n");
		}
		System.out.println(sb.toString());
	}
}
