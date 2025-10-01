//package BOJ.구슬탈출2;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int N, M, res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		res = Integer.MAX_VALUE;
		bt(1, map);
		System.out.println(res==Integer.MAX_VALUE?-1:res);
	}
	
	public static void bt(int depth, char[][] org) {
		if (depth>=res) return;
		if (depth>10) return;
		
		for (int i=0; i<4; i++) {
			char[][] map = new char[N][M];
			for (int j=0; j<N; j++) {
				for (int k=0; k<M; k++) {
					map[j][k] = org[j][k];
				}
			}
			
			int move = move(map, i);
			if(move==0) continue;
			else if (move==1) {
				res = Math.min(res, depth);
				return;
			} else {
				bt(depth+1, map);
			}
		}
	}
	
	public static int move(char[][] map, int dir) {
		//만약 빨간 공만 들어갔다면 return 1
		//만약 파란공이 들어갔다면 return 0
		//아직 모든 공이 안들어 갔다면 return 2
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
		
		boolean isGoalRed = false;
		boolean isGoalBlue = false;
		
		int[] RPos = new int[2];
		int[] BPos = new int[2];
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]=='R') {
					RPos[0] = i;
					RPos[1] = j;
				} else if(map[i][j]=='B') {
					BPos[0] = i;
					BPos[1] = j;
				}
			}
		}
		
		int[] firstPos = RPos;
		int[] secondPos = BPos;
		char first = 'R';
		char second = 'B';
		if (dir==0) {
			//상
			if(RPos[0] > BPos[0]) {
				firstPos = BPos;
				secondPos = RPos;
				first = 'B';
				second = 'R';
			} 
		} else if(dir==1) {
			if(RPos[0] < BPos[0]) {
				firstPos = BPos;
				secondPos = RPos;
				first = 'B';
				second = 'R';
			}
		} else if(dir==2) {
			if(RPos[1] > BPos[1]) {
				firstPos = BPos;
				secondPos = RPos;
				first = 'B';
				second = 'R';
			}
		} else {
			if(RPos[1] < BPos[1]) {
				firstPos = BPos;
				secondPos = RPos;
				first = 'B';
				second = 'R';
			}
		}
		
		//first 부터 움직이기
		int y = firstPos[0];
		int x = firstPos[1];
		while(true) {
			int ny = y+dy[dir];
			int nx = x+dx[dir];
			
			if(ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx]=='#') break;
			
			if (map[ny][nx]=='O') {
				map[y][x]='.';
				if(first=='R') {
					isGoalRed = true;
				} else {
					isGoalBlue = true;
				}
				break;
			}
			
			map[y][x]='.';
			map[ny][nx]=first;
			
			y = ny;
			x = nx;
		}
		
		y = secondPos[0];
		x = secondPos[1];
		while(true) {
			int ny = y+dy[dir];
			int nx = x+dx[dir];
			
			if(ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx]=='#' || map[ny][nx]==first) break;
			
			if (map[ny][nx]=='O') {
				map[y][x]='.';
				if(second=='R') {
					isGoalRed = true;
				} else {
					isGoalBlue = true;
				}
				break;
			}
			
			map[y][x]='.';
			map[ny][nx]=second;
			
			y = ny;
			x = nx;
		}
		
			
//		System.out.println();
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
//		System.out.println("===============================================");
		if(isGoalBlue) return 0;
		else if (isGoalRed) return 1;
		else return 2;
	}
}


/* 빨간 구슬과 파란 구슬을 하나씩 넣고, 빨간 구슬을 구멍을 통해 빼내는 게임
 * 보드의 세로 크기는 N, 가로 크기는 M, 가장자리는 막혀있음
 * 목표는 빨간 구슬을 구멍을 통해서 빼내는 것, 파란 구슬을 구멍에 들어가면 안됨
 * 공은 손으로 건드리는 것이 아니라, 상,하,좌,우로 기울여야 함
 * 
 * 파란 구슬이 구멍에 빠지거나, 동시에 구멍에 빠지면 실패
 * 
 * 보드의 상태가 주어졌을 때, 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하시오.
 * 
 * 3 <= N, M <= 10 // 보드의 최대 크기는 10 * 10 = 100
 * 
 * bfs/백트래킹
 * 
 * 최대 10번 움직이는 것, 경우의 수 4^10? == 100만 * 보드 탐색 100만 == 가능할듯. 
 */
