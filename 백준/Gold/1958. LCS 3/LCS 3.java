//package BOJ.LCS3;

import java.io.*;
import java.util.*;

public class Main {
	static String s1,s2,s3;
	static int[][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s1 = br.readLine();
		s2 = br.readLine();
		s3 = br.readLine();
		
		int N = s1.length();
		int M = s2.length();
		int K = s3.length();
		dp = new int[N][M][K];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				for (int k=0; k<K; k++) {
					dp[i][j][k]=-1;
				}
			}
		}
		System.out.println(dfs(0, 0, 0));
	}
	
	public static int dfs(int i, int j, int k) {
		if (i==s1.length() || j==s2.length() || k==s3.length()) return 0;
		if(dp[i][j][k]!=-1) return dp[i][j][k];
		int res = 0;
		if (s1.charAt(i)==s2.charAt(j) && s2.charAt(j)==s3.charAt(k)) {
			res = Math.max(dfs(i+1, j+1, k+1)+1, res);
		} else {
			res = Math.max(dfs(i+1, j, k), res);
			res = Math.max(dfs(i, j+1, k), res);
			res = Math.max(dfs(i, j, k+1), res);
		}
		
		return dp[i][j][k] = res;
	}
}
