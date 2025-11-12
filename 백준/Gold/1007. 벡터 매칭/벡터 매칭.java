//package BOJ.벡터매칭;

import java.io.*;
import java.util.*;

public class Main {
	static double res;
	static int N;
	static int[][] map;
	static boolean[] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int tc = Integer.parseInt(br.readLine());
		for (int t=1; t<=tc; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][2];
			int ySum=0;
			int xSum=0;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				map[i][0] = Integer.parseInt(st.nextToken());
				map[i][1] = Integer.parseInt(st.nextToken());
				ySum+=map[i][0];
				xSum+=map[i][1];
			}
			
			res = Double.MAX_VALUE;
			dfs(0, 0, new int[N/2]);
			sb.append(res).append("\n");
		}
		System.out.print(sb);
		
		
	}
	
	public static void dfs(int depth, int start, int[] choice) {
		if (depth == N/2) {
			res = Math.min(res, calLength(choice));
			return;
		}
		
		for (int i=start; i<N; i++) {
			choice[depth] = i;
			dfs(depth+1, i+1, choice);
		}
	}
	
	
	
	
	public static double calLength(int[] choice) {
		int ySum = 0;
		int xSum = 0;
		
		for (int i=0; i<N; i++) {
			boolean flag = true;
			for (int j=0; j<N/2; j++) {
				if (i==choice[j]) {
					ySum -= map[i][0];
					xSum -= map[i][1];
					flag = false;
					break;
				}
			}
			if(flag) {
				ySum+=map[i][0];
				xSum+=map[i][1];
			}
		}
		
		double length = Math.sqrt(Math.pow(ySum, 2) + Math.pow(xSum, 2));
		return length;
	}
}

/*
	N<=20
	평면 상에 N개의 점이 모인 집합 P
	벡터 매칭 : P집합 
*/