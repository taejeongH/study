//package BOJ.청소년상어;

import java.io.*;
import java.util.*;

class Fish{
	int num;
	int dir;
	
	public Fish(int num, int dir) {
		super();
		this.num = num;
		this.dir = dir;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shark [num=").append(num).append(", dir=").append(dir).append("]");
		return builder.toString();
	}
}

public class Main {
	
	static int N = 4, res;
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static Fish[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		map = new Fish[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
				map[i][j] = new Fish(num, dir);
			}
		}
		int num = map[0][0].num;
		map[0][0].num=0;
		res = 0;
		bt(0, 0, map[0][0].dir, num, map);
		System.out.println(res);
	}
	
	public static void bt(int y, int x, int dir, int sum, Fish[][] org){
		moveFish(org, y, x);
		res = Math.max(sum, res);
		int ny = y+dy[dir];
		int nx = x+dx[dir];
		
		while(ny>=0 && ny<N && nx>=0 && nx<N) {
			if (org[ny][nx].num!=0) {
				int newdir = org[ny][nx].dir;
				int num = org[ny][nx].num;
				org[ny][nx].num=0;
				Fish[][] map = new Fish[N][N];
				for (int i=0; i<N; i++) {
					for (int j=0; j<N; j++) {
						map[i][j]=new Fish(org[i][j].num, org[i][j].dir);
					}
				}
				bt(ny, nx, newdir, sum+num, map);
				org[ny][nx].num=num;
			}
			ny+=dy[dir];
			nx+=dx[dir];
		}
	}
	
	public static void moveFish(Fish[][] map, int sharkY, int sharkX) {
		for (int num=1; num<=N*N; num++) {
			nxt: for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if(num==map[i][j].num) {
						int dir = map[i][j].dir;
						for (int k=0; k<8; k++) {
							int ny = i+dy[dir];
							int nx = j+dx[dir];
							
							if(ny>=0 && ny<N && nx>=0 && nx<N) {
								//이동 가능한 경우
								if(ny!=sharkY||nx!=sharkX) {
									Fish tmp = map[ny][nx];
									map[i][j].dir = dir;
									map[ny][nx] = map[i][j];
									map[i][j]=tmp;
									break;
								}
							}
							dir = (dir+1)%8;
						}
						break nxt;
					}
				}
			}
		}
	}
}

/* 4*4크기의 공간, 한 칸에는 물고기가 한 마리 존재
 * 물고기는 번호와 방향을 가짐. 1<=번호<=16, 방향=8가지(상하좌우, 대각선)
 * 청소년 상어는 (0,0)에 있는 물고기를 먹고 -> (0,0)에 위치 -> 방향은 (0,0)의 물고기 방향
 * 이후 물고기가 이동
 * 물고기는 번호가 작은 물고기부터 순서대로 이동. 
 * 이동할 수 있는 경우 : 빈칸이거나 다른 물고기가 있는 칸
 * 이동할 수 없는 경우 : 상어가 있거나, 맵밖으로 나가는 경우
 * 이동할 수 있을 때 까지 45도 회전하고, 만약 이동할 수 없는 경우 가만히 있음
 * 상어는 방향에 있는 칸으로 이동 가능, 여러 개 칸 이동 가능, 상어가 먹을 수 있는 최댓값
 * 
 * 1. 번호가 작은 순서대로 이동하기
 * 2. 상어 이동하기(백트래킹)
 * 
*/
