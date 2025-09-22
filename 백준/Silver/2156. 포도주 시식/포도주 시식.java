//package BOJ.포도주시식;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] map;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		dp = new int[3][N];
		for (int i=0; i<3; i++) Arrays.fill(dp[i], -1);
		System.out.println(dfs(0, 0));
	}
	
	public static int dfs(int conti, int idx) {
		if (idx==N) return 0;
		if(dp[conti][idx]!=-1) return dp[conti][idx];
		int res = 0;
		if(conti==2) {
			res = dfs(0, idx+1);
		} else {
			res = Math.max(dfs(conti+1, idx+1)+map[idx], dfs(0, idx+1));
		}
		
		return dp[conti][idx]=res;
	}
}




/* 포도주 잔이 일렬로 놓여있음
 * 포도주 시식 규칙
 * 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 함, 마신 후 제자리에 다시 두기
 * 연속으로 놓여있는 3잔을 모두 마실 수 없음
 * 
*/
