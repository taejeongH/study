//package BOJ.LCS;

import java.io.*;
import java.util.*;

public class Main {
	static String a, b;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		a = br.readLine();
		b = br.readLine();
		
		dp = new int[a.length()][b.length()];
		for (int i=0; i<a.length(); i++) Arrays.fill(dp[i], -1);
		System.out.println(dfs(0, 0));
	}
	public static int dfs(int i, int j) {
		if(i==a.length() || j==b.length()) return 0;
		
		if(dp[i][j]!=-1) return dp[i][j];
		
		if(a.charAt(i) == b.charAt(j)) {
			dp[i][j] = 1 + dfs(i+1, j+1);
		} else {
			dp[i][j] = Math.max(dfs(i+1, j), dfs(i, j+1));
		}
		return dp[i][j];
	}
}
