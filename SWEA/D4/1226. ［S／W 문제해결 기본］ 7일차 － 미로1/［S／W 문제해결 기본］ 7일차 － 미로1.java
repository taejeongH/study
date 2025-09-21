//package SWEA.미로;

import java.util.*;
import java.io.*;

public class Solution {
	static int N = 16;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int testCase = 10;
		for (int test=1; test<=testCase; test++) {
			int tc = Integer.parseInt(br.readLine());
			int startY = -1;
			int startX = -1;
			int endY = -1;
			int endX = -1;
			boolean[][] map = new boolean[N][N];
			for (int i=0; i<N; i++) {
				String input =  br.readLine();
				for (int j=0; j<N; j++) {
					int num = input.charAt(j)-'0';
					if(num==0) continue;
					
					if(num==2) {
						startY=i;
						startX=j;
						continue;
					} else if(num==3) {
						endY=i;
						endX=j;
						continue;
					}
					
					map[i][j]=true;
				}
			}
			
			ArrayDeque<int[]> que = new ArrayDeque<>();
			que.add(new int[] {startY, startX});
			map[startY][startX]=false;
			
			int res=0;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				
				if(y==endY && x==endX) {
					res=1;
					break;
				}
				for (int i=0; i<4; i++) {
					int ny = y+dy[i];
					int nx = x+dx[i];
					if(ny<0 || ny>=N || nx<0 || nx>=N || !map[ny][nx]) {
						map[ny][nx]=true;
						que.add(new int[] {ny, nx});
					}
				}
			}
			
			sb.append("#").append(test).append(" ").append(res).append("\n");
			
		}
		System.out.println(sb);
		
	}

	
}
