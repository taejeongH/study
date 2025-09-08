//package BOJ.퇴사2;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][2];
		List<int[]>[] g = new List[N+1]; for (int i=0; i<N+1; i++) g[i] = new ArrayList<>();
		for (int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = i;
			int end = start+Integer.parseInt(st.nextToken())-1;
			int price = Integer.parseInt(st.nextToken());
			if(end>N) continue; 
			g[end].add(new int[] {start, price});
		}
		
		int[] dp = new int[N+1];
		int result = 0;
		for (int i=1; i<N+1; i++) {
			dp[i] = dp[i-1];
			for (int j=0; j<g[i].size(); j++) {
				int curStart = g[i].get(j)[0];
				dp[i] = Math.max(dp[curStart-1]+g[i].get(j)[1], dp[i]);
			}
			result = Math.max(dp[i], result);
			
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(result);
	}
}
