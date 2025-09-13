//package BOJ.할로윈의양아치;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] candies = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<N+1; i++) {
			candies[i] = Integer.parseInt(st.nextToken());
		}
		
		List<Integer>[] g = new List[N+1]; for (int i=1; i<N+1; i++) g[i] = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s].add(e);
			g[e].add(s);
		}
		
		boolean[] v = new boolean[N+1];
		ArrayDeque<Integer> que = new ArrayDeque<>();
		ArrayList<int[]> candy = new ArrayList<>(); //{사람 수, 캔디};
		for (int i=1; i<N+1; i++) {
			if(!v[i]) {
				que.add(i);
				int candyCount = 0;
				int peopleCount = 0;
				v[i]=true;
				while(!que.isEmpty()) {
					int now = que.poll();
					
					candyCount+=candies[now];
					peopleCount++;
					for (int nxt : g[now]) {
						if(!v[nxt]) {
							v[nxt]=true;
							que.add(nxt);
						}
					}
				}
				candy.add(new int[] {peopleCount, candyCount});
			}
		}
		
		int[][] dp = new int[candy.size()+1][K];
		
		for (int i=1; i<=candy.size(); i++) {
			int curPeople = candy.get(i-1)[0];
			int curCandy = candy.get(i-1)[1];
			for (int j=0; j<K; j++) {
				if (curPeople>j) dp[i][j] = dp[i-1][j];
				else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-curPeople]+curCandy);
			}
		}
		
		System.out.println(dp[dp.length-1][K-1]);
	}
}
