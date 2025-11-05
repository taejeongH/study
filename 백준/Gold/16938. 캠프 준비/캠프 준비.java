//package BOJ.캠프준비;

import java.io.*;
import java.util.*;

public class Main {
	static int N, L, R, X;
	static int[] level;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		level = new int[N];
		for (int i=0; i<N; i++) level[i] = Integer.parseInt(st.nextToken());
		
		System.out.println(dfs(0, Integer.MAX_VALUE, 0, 0, 0));
	}
	
	public static int dfs(int depth, int min, int max, int sum, int cnt) {
		if (sum > R) return 0;
		
		if (depth == N) {
			if (cnt < 2) return 0;
			
			if(max - min < X) return 0;
			if(sum < L || sum > R) return 0;
			
			return 1;
		}
		
		int res = 0;
		res += dfs(depth+1, Math.min(level[depth], min), Math.max(level[depth], max), sum+level[depth], cnt+1);
		res += dfs(depth+1, min, max, sum, cnt);
		return res;
	}
}

/*
	N개의 문제
	i번째 문제의 난이도는 Ai
	문제 난이도의 합은 L이상 R이하여야 함
	
	가장어려운 문제와 가장 쉬운 문제의 차이는 X이상이어야함
	
	캠프에 사용할 문제를 고르는 방법의 수
	
	NLRX
	Ai
		
*/