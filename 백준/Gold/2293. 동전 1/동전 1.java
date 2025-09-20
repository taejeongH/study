//package BOJ.동전1;

import java.io.*;
import java.util.*;

public class Main {
	static int N, K, res;
	static int[] map; 
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N];
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[K+1][N];
		for (int i=0; i<K+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(dfs(K, 0));
	}
	
	static int dfs(int sum, int idx) {
	    if (sum == 0) return 1;
	    if (idx == N) return 0;
	    if (dp[sum][idx] != -1) return dp[sum][idx];

	    int res = 0;
	    // 현재 동전을 사용
	    if (sum - map[idx] >= 0) res += dfs(sum - map[idx], idx);
	    // 다음 동전으로 넘어가기
	    res += dfs(sum, idx + 1);

	    return dp[sum][idx] = res;
	}

}
