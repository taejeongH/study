import java.io.*;
import java.util.*;

class Shark {
	int y;
	int x;
	int velocity;
	int direction;
	int scale;
	
	public Shark(int y, int x, int velocity, int direction, int scale) {
		super();
		this.y = y;
		this.x = x;
		this.velocity = velocity;
		this.direction = direction;
		this.scale = scale;
	}

	@Override
	public String toString() {
		return "Shark [y=" + y + ", x=" + x + ", velocity=" + velocity + ", direction=" + direction + ", scale=" + scale
				+ "]";
	}
	
}

public class Main {

	public static void main(String[] args) throws Exception{
//		File file = new File("src/sample_input (3).txt");
//		BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int R = Integer.parseInt(st.nextToken()); //y크기
		int C = Integer.parseInt(st.nextToken()); //x크기
		int M = Integer.parseInt(st.nextToken()); //상어의 수
		
		int[][] map = new int[R][C];
		Shark[] shark = new Shark[M+1]; //상어를 1~M까지 번호를 붙인다.
		boolean[] isLive = new boolean[M+1]; for(int i = 1; i < M+1; i++) isLive[i] = true;
		
		int sharkNum = 1;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int vel = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int scale = Integer.parseInt(st.nextToken());
			
			map[y][x] = sharkNum++;
			shark[i] = new Shark(y, x, vel, dir, scale);
		}
		
		int result = 0;
		int fishingKingPos = -1;
		for (int i = 0; i < C; i++) {
			//1. 낚시왕 오른쪽으로 이동
			fishingKingPos++; 
			
			//2. 만약 해당 열에 상어가 있다면 잡는다.
			for (int j = 0; j < R; j++) {
				if(map[j][fishingKingPos] != 0) {
					result += shark[map[j][fishingKingPos]].scale;
					isLive[map[j][fishingKingPos]] = false;
					map[j][fishingKingPos] = 0;
					break;
				}
			}
			
			
			//3. 상어가 이동한다.
			if (i == C-1) break; //마지막 턴은 이동시킬 필요가 없다.
			
			for (int j = 1; j <= M; j++) {
				if (isLive[j]) {
					//상어가 살아있다면 움직인다.
					Shark curShark = shark[j];
					
					map[curShark.y][curShark.x] = 0; // 맵의 모든 값은 0이 된다(초기화)
					int y = curShark.y;
					int x = curShark.x;
					int dir = curShark.direction;
					int vel = curShark.velocity;
					int mapY = R - 1;
					int mapX = C - 1;
					if(dir == 1) {
						if (vel > y) {
							int change = (vel - y)/mapY + 1;
							if (change % 2 == 1) {
								y = (vel-y) % mapY;
								dir++;
							} else {
								y = mapY - ((vel - y) % mapY);
							}
						} else {
							y -= vel;
						}
					} else if(dir == 2) {
						if (vel > mapY - y) {
							int change = (vel - (mapY - y))/mapY + 1;
							if (change % 2 == 1) {
								y = mapY - ((vel - (mapY - y)) % mapY);
								dir--;
							} else {
								y = (vel - (mapY - y)) % mapY;
							}
						} else {
							y += vel;
						}
					} else if (dir == 3) {
						if (vel > mapX - x) {
							int change = (vel - (mapX - x))/mapX + 1;
							if (change % 2 == 1) {
								x = mapX - ((vel - (mapX - x)) % mapX);
								dir++;
							} else {
								x = (vel - (mapX - x)) % mapX;
							}
						} else {
							x += vel;
						}
					} else {
						if (vel > x) {
							int change = (vel - x)/mapX + 1;
							if (change % 2 == 1) {
								x = (vel-x) % mapX;
								dir--;
							} else {
								x = mapX - ((vel - x) % mapX);
							}
						} else {
							x -= vel;
						}
					}
					
					curShark.y = y;
					curShark.x = x;
					curShark.direction = dir;
				}
			}
			
			
			for (int j = 1; j <= M; j++) {
				if (isLive[j]) {
					Shark curShark = shark[j];
					
					if (map[curShark.y][curShark.x] == 0) {
						map[curShark.y][curShark.x] = j;
					} else {
						if (shark[map[curShark.y][curShark.x]].scale < curShark.scale) {
							isLive[map[curShark.y][curShark.x]] = false;
							map[curShark.y][curShark.x] = j;
						} else {
							isLive[j] = false;
						}
					}
				}
			}
			
//			for (int j = 0; j < R; j++) {
//				System.out.println(Arrays.toString(map[j]));
//			}
//			System.out.println();
		}
		
		System.out.println(result);
		
	}
}
