//package BOJ.게리맨더링17471;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] map;
	static int[] selected;
	static int N;
	static int[] population;
	static int result;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./src/BOJ/게리맨더링17471/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		population = new int[N+1];
		map = new int[N+1][N+1];
		for (int i=1; i<N+1; i++) population[i]=Integer.parseInt(st.nextToken());
		for (int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			for (int j=0; j<c; j++) {
				int end = Integer.parseInt(st.nextToken());
				map[i][end]=1;
				map[end][i]=1;
			}
		}
		selected = new int[N+1];
		result = Integer.MAX_VALUE;
		for (int i=1; i<=N/2; i++) {
			bt(0, 0, i);
		}
		if(result==Integer.MAX_VALUE)System.out.println(-1);
		else System.out.println(result);
	}
	public static void bt(int num, int start, int max) {
		if(num==max) {
			if(isConnected()) {
				calPopulation();
			}
		}
		
		for(int i=start+1; i<N+1; i++) {
			selected[i]=1;
			bt(num+1, i, max);
			selected[i]=0;
		}
	}
	
	public static boolean isConnected() {
		int[] start = new int[2];
		for (int i=1; i<N+1; i++) {
			if(selected[i]==1) {
				start[1]=i;
			}else {
				start[0]=i;
			}
			
			if(start[0]!=0 && start[1]!=0)break; 
		}
		boolean[] visited = new boolean[N+1];
		for(int i=0; i<2; i++) {
			ArrayDeque<Integer> que = new ArrayDeque<>();
			que.add(start[i]);
			visited[start[i]]=true;
			while(!que.isEmpty()) {
				int now = que.poll();
				for (int j=1; j<N+1; j++) {
					if(map[now][j]==1&&!visited[j]&&selected[j]==i) {
						visited[j]=true;
						que.add(j);
					}
				}
			}
		}
		
		for (int i=1; i<N+1; i++) {
			if(!visited[i]) return false;
		}
		return true;
	}
	
	public static void calPopulation() {
		int aSum=0;
		int bSum=0;
		for(int i=1; i<N+1; i++) {
			if(selected[i]==1) {
				aSum += population[i];
			} else {
				bSum += population[i];
			}
		}
		
		result = Math.min(Math.abs(aSum-bSum), result);
	}
}
