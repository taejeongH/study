//package SWEA.최적경로1247;

import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, endY, endX, result;
	static boolean[] visited;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/SWEA/최적경로1247/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test=1; test<=testCase; test++) {
			N = Integer.parseInt(br.readLine().trim());
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			int startY = Integer.parseInt(st.nextToken());
			int startX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			endX = Integer.parseInt(st.nextToken());
			
			map = new int[N][2];
			for (int i=0; i<N; i++) {
				map[i][0] = Integer.parseInt(st.nextToken());
				map[i][1] = Integer.parseInt(st.nextToken());
			}
			visited = new boolean[N];
			result = Integer.MAX_VALUE;
			bt(0, 0, startY, startX);
			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	public static void bt(int num, int disSum, int preY, int preX) {
		if(disSum>result) return;
		
		if (num == N) {
			result = Math.min(disSum+Math.abs(endY - preY) + Math.abs(endX-preX), result);
			return;
		}
		for (int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i]=true;
				bt(num+1, disSum + Math.abs(map[i][0] - preY) + Math.abs(map[i][1]-preX), map[i][0], map[i][1]);
				visited[i]=false;
			}
		}
	}
	
}
