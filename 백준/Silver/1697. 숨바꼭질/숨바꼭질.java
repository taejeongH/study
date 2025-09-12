//package BOJ.숨바꼭질2;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {N, 0});
		
		int[] distance = new int[Math.max(K*2+1, N+1)];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[N] = 0;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int n = now[0];
			int d = now[1];
			
			if(n==K) break;

			
			if (n*2<K*2 && n*2>=0 && distance[n*2] >= d+1) {
				distance[n*2] = d+1;
				que.add(new int[] {n*2, d+1});
			} 
			
			if(n+1<K*2 && n+1>=0 && distance[n+1] >= d+1) {
				distance[n+1] = d+1;
				que.add(new int[] {n+1, d+1});
			} 
			
			if(n-1>=0 && distance[n-1] >= d+1) {
				distance[n-1] = d+1;
				que.add(new int[] {n-1, d+1});
			}

		}
		System.out.println(distance[K]);
	}
}
