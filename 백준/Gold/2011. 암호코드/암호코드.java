//package BOJ.암호코드;

import java.io.*;
import java.util.*;

public class Main {
	static String s;
	static int N;
	static int[] dp;
	static final int MOD = 1000000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine().trim();
		N = s.length();
		dp = new int[N];
		Arrays.fill(dp, -1);
		System.out.println(dfs(0));
	}
	
	public static int dfs(int idx) {
		if (idx==N) {
			return 1;
		}
		if(s.charAt(idx)=='0') return 0;
		
		if(dp[idx]!=-1) return dp[idx];
		
		long res = 0;
		if(s.charAt(idx)=='2' && idx+1<N && s.charAt(idx+1)-'0'<=6) {
			res += dfs(idx+2);
		}
		
		if (s.charAt(idx)=='1' && idx+1<N) {
			res += dfs(idx+2);
		}
		
		res += dfs(idx+1);
		
		return dp[idx]= (int) res % MOD;
	}
}


/*
	해석 가능성
	
	1->1x로 해석
	2->2x로 해석 (2다음 숫자가 6이하일 경우만)
*/