//package SWEA.나무베기;

import java.util.*;
import java.io.*;

public class Solution {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String[] args) throws Exception{
		//.setIn(new FileInputStream("res/input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int test=1; test<=testCase; test++) {
			st = new StringTokenizer(br.readLine());
			int N  = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			int startY = -1;
			int startX = -1;
			int endY = -1;
			int endX = -1;
			for (int i=0; i<N; i++) {
				String input = br.readLine();
				for (int j=0; j<N; j++) {
					char c = input.charAt(j);
					if(c=='T') {
						map[i][j]=1;
					}
					else if (c=='X') {
						startY=i;
						startX=j;
					} else if(c=='Y') {
						endY=i;
						endX=j;
					} 
				}
			}
			
			ArrayDeque<int[]> que = new ArrayDeque<>();
			que.add(new int[] {startY, startX, 0, 0, 0}); //y, x, dir, dis;
			boolean[][][][] visited = new boolean[N][N][K+1][4];
			int res = -1;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				int dir = now[2];
				int dis = now[3];
				int key = now[4];
				
//				if(visited[y][x][key][dir]) continue;
//				visited[y][x][key][dir] = true;
				
				if(y==endY && x==endX) {
					res = dis;
					break;
				}
				
				
				
				for (int i=-1; i<=1; i++) {
					//0 -> 직진, -1, 1 -> 좌, 우회전
					int newkey = key;
					int newdir = (dir+i)%4;
					if(newdir==-1) newdir=3;
					int ny = y;
					int nx = x;
					if(i==0) {
						ny += dy[newdir];
						nx += dx[newdir];
					}
					if(ny<0 || ny>=N || nx<0 || nx>=N) continue;
					if(map[ny][nx]==1) {
						if (key<K) newkey++;
						else continue;
					}
					
					if(visited[ny][nx][newkey][newdir]) continue;
					visited[ny][nx][newkey][newdir]=true;
					
					
					que.add(new int[] {ny, nx, newdir, dis+1, newkey});
				}
			}
			sb.append("#").append(test).append(" ").append(res).append("\n");
		}
		System.out.println(sb);
	}

	
}
