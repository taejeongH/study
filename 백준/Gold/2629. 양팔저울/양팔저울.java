//package BOJ.양팔저울;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] weight;
	static boolean[][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine()); //추의 개수
		
		weight = new int[N];
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		for (int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
			sum += weight[i];
		}
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		dp = new boolean[N+1][40001];
		for (int i=0; i<M; i++) {
			int ball = Integer.parseInt(st.nextToken());
			if(ball > sum) sb.append("N").append(" ");
			else {
				dfs(0, 0);
				sb.append(dp[N][ball]?"Y":"N").append(" ");
			}
		}
		System.out.println(sb);
	}
	
	public static void dfs(int depth, int w) {
		if(depth > N || dp[depth][w]) return;
		dp[depth][w] = true;
		if(depth==N) return;
		int cur = weight[depth];
		
		dfs(depth+1, w);
		dfs(depth+1, w+cur);
		dfs(depth+1, Math.abs(w-cur));
	}
}

/*
	추가 주어졌을 때 구슬의 무게를 알아낼 수 있는지?
	
	N<=30
	추의 무게가 가벼운것부터 주어짐 같은 무게가 있을 수 있고 500g이하
	M<=7
	구슬의 무개 <=40000
	
	
	1. 추들의 조합으로 구슬의 무게를 만들 수 있는 가
	2. 
	
*/