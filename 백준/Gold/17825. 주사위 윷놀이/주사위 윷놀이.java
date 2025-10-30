//package BOJ.주사위윷놀이;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] g;
	static int[] horse;
	static int[] dice;
	static int res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		dice = new int[10];
		for (int i=0; i<10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		g= new int[32][2];
		g[0][0] = 1;
		g[1][1] = 2;
		for (int i=1; i<20; i++) {
			g[i][0] = i+1;
			g[i+1][1] = (i+1)*2;
		}
		
		for (int i=21; i<=25; i++) {
			g[i][0] = i+1;
		}
		g[21][1] = 13;
		g[22][1] = 16;
		g[23][1] = 19;
		g[24][1] = 25;
		g[25][1] = 30;
		g[26][1] = 35;
		
		g[31][0] = 30;
		g[30][0] = 24;
		g[29][0] = 28;
		g[28][0] = 27;
		g[27][0] = 24;
		g[26][0] = 20;

		g[27][1] = 26;
		g[28][1] = 27;
		g[29][1] = 28;
		g[30][1] = 24;
		g[31][1] = 22;
		
		//5시작시 -> 21로
		//10 시작시 31로
		//15 시작시 29로
		horse = new int[4];
//		for (int i=0; i<32; i++) System.out.println(i + " " + Arrays.toString(g[i]));
		res = 0;
		dfs(0, 0);
		System.out.println(res);
	}
	
	public static void dfs(int depth, int score) {
		if(depth == 10) {
			res = Math.max(res, score);
			return;
		}
		for (int i=0; i<4; i++) {
			if (horse[i] != -1) {
				//말이 도착하지 않았다면
				int curPos = horse[i];
				int curscore =calScore(dice[depth], i);
				if(curscore==-1) continue;
				dfs(depth+1, score+curscore);
				horse[i] = curPos;
			}
		}
		
	}
	
	public static int calScore(int size, int horseNum) {
		int score = 0;
		int cur = horse[horseNum];
		if (cur==5) {
			cur = 21;
			size--;
		} else if(cur==10) {
			cur = 31;
			size--;
		} else if(cur==15) {
			cur = 29;
			size--;
		}
		for (int j=0; j<size; j++) {
			cur = g[cur][0]; 
			if (cur==0) {
				horse[horseNum] = -1;
				return score;
			}
		}
		
		score += g[cur][1];
		
		for (int i=0; i<4; i++) {
			if(cur==horse[i]) return -1;
		}
		
		horse[horseNum] = cur;
		return score;
	}
}


/*
	
	4개의 말 중 어떤 말을 이동시킬 것인가?
	만약 도착 지점에 다른 말이 존재한다면 이동시킬 수 없음
	
	


*/