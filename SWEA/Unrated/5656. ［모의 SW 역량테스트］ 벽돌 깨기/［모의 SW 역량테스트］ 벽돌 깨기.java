//package SWEA.벽돌깨기5656;

import java.util.*;
import java.io.*;

public class Solution {

	static int N, W, H, result;
	static int[][][] prevMap;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/SWEA/벽돌깨기5656/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test=1; test<=testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			prevMap = new int[N+1][H][W];
			int[][] map = new int[H][W];
			for (int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					prevMap[0][i][j] = map[i][j];
				}
			}	
			
			result = Integer.MAX_VALUE;
			bt(0, 0);
			
			if(result==Integer.MAX_VALUE) result = 0;
			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void bt(int num, int count) {
		if(num == N) {
			result = Math.min(count, result);
			return;
		}
		
		for (int i=0; i<W; i++) {
			int startY = 0;
			boolean can = true;
			while(prevMap[num][startY][i]==0) {
				startY++;
				if(startY >= H) {
					can = false;
					break;
				}
			}
			
			if(can) {
				for (int j=0; j<H; j++) {
					for (int k=0; k<W; k++) {
						prevMap[num+1][j][k] = prevMap[num][j][k];
					}
				}
				crush(startY, i, prevMap[num+1][startY][i], prevMap[num+1]);
				bt(num+1, gravity(prevMap[num+1]));
			}
			
		}
	}
	
	public static void crush(int startY, int startX, int iter, int[][] map) {
		map[startY][startX] = 0;
		for (int i=0; i<4; i++) {
			int nextY = startY;
			int nextX = startX;
			for (int j=1; j <iter; j++) {
				nextY += dy[i];
				nextX += +dx[i];
				
				if(nextY>=0 && nextY<H && nextX>=0 && nextX<W && map[nextY][nextX] != 0) {
					int nextIter = map[nextY][nextX];
					map[nextY][nextX] = 0;
					crush(nextY, nextX, nextIter, map);
				}
			}
			
		}
		
		
	}
	
	public static int gravity(int[][] map) {
		int cnt = 0;
		for (int i=H-1; i>=0; i--) {
			for (int j=0; j<W; j++) {
				if(map[i][j] != 0) {
					cnt++;
					int y = i;
					while(true) {
						int nextY = y+1;
						if (nextY >= H || map[nextY][j] != 0) break;
						map[nextY][j] = map[y][j];
						map[y][j]=0;
						y = nextY;
					}
				}
			}
		}
		
		return cnt;
	}
	
	
	
}
