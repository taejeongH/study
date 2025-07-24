import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main{
	public static int N;
	public static int M;
	public static int[][] map;
	public static boolean[] visited;
	public static int min;
	public static int[][] house;
	public static int[][] chicken;
	public static int[] choice;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		choice = new int[M];
		
		
		
		int houseCount = 0;
		int chickenCount = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) chickenCount++;
				if (map[i][j] == 1) houseCount++;
			}
		}
		
		//집과 치킨집의 위치를 모두 저장
		house = new int[houseCount][2];
		chicken = new int[chickenCount][2];
		int houseCnt = 0;
		int chickenCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					house[houseCnt][0] = i;
					house[houseCnt++][1] = j;
				} else if(map[i][j] == 2) {
					chicken[chickenCnt][0] = i;
					chicken[chickenCnt++][1] = j;
				}
			}
		}
		
		min = Integer.MAX_VALUE;
		visited = new boolean[chickenCount];
		bt(0);
		
		System.out.println(min);
		
	}
	
	public static void bt(int num) {
		if (num == M) {
			int dis = 0;
			for (int i = 0; i < house.length; i++) {
				int minDis = Integer.MAX_VALUE;
				for (int j = 0; j < choice.length; j++) {
					minDis = Math.min(Math.abs(chicken[choice[j]][0] - house[i][0]) + Math.abs(chicken[choice[j]][1] - house[i][1]), minDis);
				}
				//System.out.println(minDis);
				dis += minDis;
			}
			//System.out.println(dis);
			min = Math.min(min, dis);
		} else {
			for (int i = 0; i < chicken.length; i++) {
				if (!visited[i]) {
					if (num == 0) {
						visited[i] = true;
						choice[num] = i;
						bt(num+1);
						visited[i] = false;
						choice[num] = 0;
					} else {
						if (choice[num-1] < i) {	//중복제거
							visited[i] = true;
							choice[num] = i;
							bt(num+1);
							visited[i] = false;
							choice[num] = 0;
						}
					}
					
				}
			}
		}
	}
	

}