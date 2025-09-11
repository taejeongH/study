//package BOJ.음악프로그램;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] g = new List[N+1]; for(int i=1; i<N+1; i++) g[i]=new ArrayList<>();
		
		int[] inDegree = new int[N+1];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			for (int j=0; j<K-1; j++) {
				int e = Integer.parseInt(st.nextToken());
				g[s].add(e);
				inDegree[e]++;
				s = e;
			}
		}
		
		ArrayDeque<Integer> que = new ArrayDeque<>();
		for (int i=1; i<N+1; i++) {
			if (inDegree[i]==0) que.add(i);
		}
		
		
		while(!que.isEmpty()) {
			int now = que.poll();
			sb.append(now).append("\n");
			for (int j=0; j<g[now].size(); j++) {
				if (--inDegree[g[now].get(j)]==0) que.add(g[now].get(j));
			}
		}
		
		boolean isCircle = false;
		for (int i=1; i<N+1; i++) {
			if (inDegree[i]!=0) {
				isCircle = true;
			}
		}
		
		if (isCircle) System.out.println(0);
		else System.out.println(sb.toString());
	}
}
