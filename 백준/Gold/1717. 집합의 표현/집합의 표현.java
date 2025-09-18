//package BOJ.집합의표현;

import java.io.*;
import java.util.*;

public class Main {
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		for (int i=0; i<N+1; i++) {
			parents[i]=i;
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(order==0) {
				union(x, y);
			} else {
				if(find(x)==find(y)) {
					sb.append("YES\n");
				} else {
					sb.append("NO\n");
				}
			}
		}
		System.out.print(sb);
	}
	
	public static boolean union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		if (xRoot==yRoot) return true;
		
		parents[xRoot]=yRoot;
		return false;
	}
	
	public static int find(int x) {
		if(parents[x]==x) return x;
		int xRoot = find(parents[x]);
		return parents[x]=xRoot;
	}
}
