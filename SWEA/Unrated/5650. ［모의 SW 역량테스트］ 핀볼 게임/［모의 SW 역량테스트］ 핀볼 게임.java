//package SWEA.핀볼게임5650;

import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();
		for (int test = 1; test <= testCase; test++) {
			int N = sc.nextInt(); //맵 사이즈
			
			int[][] map = new int[N][N]; //input 맵
			List<int[]>[] wormholePos = new ArrayList[11]; for (int i = 6; i < 11; i++) wormholePos[i] = new ArrayList<>();
			
			
			ArrayDeque<int[]> que = new ArrayDeque<>(); //시작 포지션 저장

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 0) {
						que.add(new int[] {i, j});
					} else if(map[i][j] >= 6) {
						wormholePos[map[i][j]].add(new int[] {i, j});
					}
				}
			}
			int[] dx = {-1, 1, 0, 0};
			int[] dy = {0, 0, -1, 1};
 			
			Map<Integer,Integer> oposite = new HashMap<Integer, Integer>(); //반대방향 전환
			oposite.put(0, 1);
			oposite.put(1, 0);
			oposite.put(2, 3);
			oposite.put(3, 2);
			
			int result = 0;
			
			//모든 경우의 수 계산
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int startY = now[0];
				int startX = now[1];
				
				for (int i = 0; i < 4; i++) {
					//4가지 방향
					int y = startY;
					int x = startX;
					int dir = i;
					
					int sum = 0;
					while (true) {
						int nextY = y + dy[dir];
						int nextX = x + dx[dir];
			
						if(nextY == startY && nextX == startX) break; //종료 조건 1. 원래 자신의 자리로 돌아왔을 경우
						
						if (nextY >= N || nextY < 0 || nextX >= N || nextX < 0) {
							//벽에 부딪힌 경우
							sum++;
							dir = oposite.get(dir);
							y = nextY;
							x = nextX;
							continue;
						}
						
						if (map[nextY][nextX] == -1) {
							//종료 조건 2. 블랙홀을 만났을 경우
							break;
						}
						
						if (map[nextY][nextX] >= 1 && map[nextY][nextX] <= 5) {
							//블록일 경우
							int blockType = map[nextY][nextX];
							if (blockType == 1) {
								if (dir == 3) {
									dir = 1;
								} else if(dir == 0) {
									
									dir = 2;
								} else {
									dir = oposite.get(dir);
								}
							} else if (blockType == 2) {
								if (dir == 0) {
									dir = 3;
								} else if (dir == 2) {
									dir = 1;
								} else {
									dir = oposite.get(dir);
								}
							} else if (blockType == 3) {
								if (dir == 2) {
									dir = 0;
								} else if (dir == 1) {
									dir = 3;
								} else {
									dir = oposite.get(dir);
								}
							} else if (blockType == 4) {
								if (dir == 1) {
									dir = 2;
								} else if (dir == 3) {
									dir = 0;
								} else {
									dir = oposite.get(dir);
								}
							} else {
								dir = oposite.get(dir);
								
							}
							
							sum++;
							
							
						} else if (map[nextY][nextX] >= 6) {
							//웜홀일 경우
							if (nextY == wormholePos[map[nextY][nextX]].get(0)[0] && nextX == wormholePos[map[nextY][nextX]].get(0)[1]) {
								y = wormholePos[map[nextY][nextX]].get(1)[0];
								x = wormholePos[map[nextY][nextX]].get(1)[1];
							} else {
								y = wormholePos[map[nextY][nextX]].get(0)[0];
								x = wormholePos[map[nextY][nextX]].get(0)[1];
							}
							continue;
						}
						
						y = nextY;
						x = nextX;
						
						
					}
					result = Math.max(result, sum);
				}
				
			}
			
			System.out.println("#" + test + " " + result);
			
			
		}
	}
}
