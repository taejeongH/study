//package BOJ.친구네트워크;

import java.io.*;
import java.util.*;

public class Main {
	static int[] parents, friends;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			int N = Integer.parseInt(br.readLine());
			HashMap<String, Integer> people = new HashMap<>();
			int[][] query = new int[N][2];
			int idx = 0;
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				String p1 = st.nextToken();
				String p2 = st.nextToken();
				if (!people.containsKey(p1)) {
					people.put(p1, idx++);
				} 
				
				if (!people.containsKey(p2)) {
					people.put(p2, idx++);
				}
				
				query[i][0] = people.get(p1);
				query[i][1] = people.get(p2);
			}
			
			parents = new int[idx];
			friends = new int[idx];
			for (int i=0; i<idx; i++) {
				parents[i]=i;
				friends[i]=1;
			}
			for (int i=0; i<N; i++) {
				sb.append(union(query[i][0], query[i][1])).append("\n");
			}
		}
		System.out.print(sb);
	}
	
	public static int find(int x) {
		if(parents[x]==x) return x;
		
		int xRoot = find(parents[x]);
		
		return parents[x]=xRoot;
	}
	
	public static int union (int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		if (xRoot==yRoot) return friends[yRoot];
		
		parents[xRoot] = yRoot;
		friends[yRoot] += friends[xRoot];
		return friends[yRoot];
	}
}


/*
	map으로 idx 
*/