//package SWEA.미생물격리2382;

import java.util.*;
import java.io.*;

class Microbe {
	int y;
	int x;
	int size;
	int dir;
	
	public Microbe(int y, int x, int size, int dir) {
		super();
		this.y = y;
		this.x = x;
		this.size = size;
		this.dir = dir;
	}
	
}

public class Solution {

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int[] oposite = {1, 0, 3, 2};
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/SWEA/미생물격리2382/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int test=1; test<=testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			int N = Integer.parseInt(st.nextToken()); //맵 사이즈
			int M = Integer.parseInt(st.nextToken()); //격리시간
			int K = Integer.parseInt(st.nextToken()); //미생물 개수
			
			Microbe[] microbs = new Microbe[K+1];
			int[][] map = new int[N][N];
			for (int i=1; i<=K; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
				Microbe m = new Microbe(y, x, size, dir);
				microbs[i] = m;
				map[y][x] = i;
			}
			
			int[][] nextMap = new int[N][N];
			for (int time=0; time<M; time++) {
				for (int i=0; i<N; i++) {
					for (int j=0; j<N; j++) {
						//현재 위치에서 상하좌우를 확인 올 수 있는 애들 확인
						int maxM = -1;
						int sum = 0;
						for (int k=0; k<4; k++) {
							int nextY=i+dy[k];
							int nextX=j+dx[k];
							if(nextY>=0 && nextY<N && nextX>=0 && nextX<N) {
								if(map[nextY][nextX] != 0 && oposite[microbs[map[nextY][nextX]].dir] == k) {
									sum += microbs[map[nextY][nextX]].size;
									if(maxM == -1 || microbs[maxM].size < microbs[map[nextY][nextX]].size) {
										maxM = map[nextY][nextX];
										map[nextY][nextX] = 0;
									}
								} else {
								}
							}
						}
						if(maxM != -1) {
							nextMap[i][j] = maxM;
							microbs[maxM].size = sum;
							if(i==0 || j==0|| i==N-1 || j==N-1) {
								microbs[maxM].size /= 2;
								if(microbs[maxM].size==0) {
									nextMap[i][j] = 0;
									continue;
								}
								microbs[maxM].dir = oposite[microbs[maxM].dir];
							}
						} else {
							nextMap[i][j] = 0;
						}
					}
				}
				
				for (int i=0; i<N; i++) {
					for (int j=0; j<N; j++) {
						map[i][j] = nextMap[i][j];
					}
				}
				
			}
			int result = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if(map[i][j] != 0) {
						result += microbs[map[i][j]].size;
					}
				}
			}
			sb.append("#").append(test).append(" ").append(result).append("\n");
			
		}
		System.out.println(sb.toString());
		
	}

	
}
