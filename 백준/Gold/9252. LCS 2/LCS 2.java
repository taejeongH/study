//package BOJ.LCS2;

import java.io.*;
import java.util.*;

public class Main {
	static String s1, s2;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s1 = br.readLine();
		s2 = br.readLine();
//		for (int i=0; i<dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
		
		int N = s1.length()+1;
		int M = s2.length()+1;
		dp = new int[N][M];
		for (int i=1; i<N; i++) {
			for (int j=1; j<M; j++) {
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1]+1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		Stack<Character> stk = new Stack<>();
		int i = N-1;
		int j = M-1;
		while (i>0 && j>0) {
			
			if (dp[i][j] == dp[i-1][j]) {
				i--;
			} else if (dp[i][j]==dp[i][j-1]) {
				j--;
			} else {
				stk.push(s1.charAt(i-1));
				i--;
				j--;
			}
		}
		System.out.println(dp[N-1][M-1]);
		while(!stk.isEmpty()) {
			System.out.print(stk.pop());
		}
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(dp[i]));
	}
	
//	public static int dfs(int i, int j) {
//		if (i==s1.length() || j==s2.length()) return 0;
//		
//		if(dp[i][j]!=-1) return dp[i][j];		
//		if (s1.charAt(i) == s2.charAt(j)) {
//			dp[i][j] = dfs(i+1, j+1)+1;
//			System.out.println(i);
//		} else {
//			dp[i][j] = Math.max(dfs(i+1, j), dfs(i, j+1));
//		}
//		
//		return dp[i][j];
//	}
}
