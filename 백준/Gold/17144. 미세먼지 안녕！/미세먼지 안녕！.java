import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main {
	
	static int R;
	static int C;
	static int[][] map;
	static int[][] orgMap;
	static int[] upAir;
	static int[] downAir;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); //ySize
		C = Integer.parseInt(st.nextToken()); //xSize
		int T = Integer.parseInt(st.nextToken()); //Time
		
		//에어컨의 위치를 위 아래 나누어서 받는다
		upAir = new int[2];
		downAir = new int[2];
		
		//input 저장
		map = new int[R][C];
		orgMap = new int[R][C];
		
		for (int i = 0 ; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				orgMap[i][j] = map[i][j];
				
				//에어컨 위치 저장
				if(map[i][j] == -1) {
					if(upAir[0] == 0) {
						upAir[0] = i;
						upAir[1] = j;
					} else {
						downAir[0] = i;
						downAir[1] = j;
					}
				}
			}
		}

		
		for (int i = 1; i <= T; i++) {
			diffusion();
			workAir();
			//미세먼지의 흩어짐을 계산하기 위해 계산하기 전단계의 map을 저장
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					orgMap[j][k] = map[j][k];
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum += map[i][j];
			}
		}
		
		System.out.println(sum + 2);
		
		
		
		
	}
	
	//미세먼지의 흩어짐 계산
	public static void diffusion() {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (orgMap[i][j] > 0) {
					int sum = 0;
					int div = orgMap[i][j]/5;
					for (int k = 0; k < 4; k++) {
						int nextY = i + dy[k];
						int nextX = j + dx[k];
						if (nextX >= 0 && nextX < C && nextY >= 0 && nextY < R) {
							if (orgMap[nextY][nextX] != -1 && div >= 1) {
								map[nextY][nextX] += div;
								sum += div;
							}
						}
					}
					map[i][j] -= sum;
				}
			}
		}
		
		
	}
	
	//공기청정기 작동 위와 아래를 구분해서 만들긴 했는데....
	public static void workAir() {
		// 공기청정기 아랫 부분 계산
		int dx[] = {0, 1, 0, -1};
		int dy[] = {1, 0, -1, 0};
		
		int y = downAir[0];
		int x = downAir[1];
		int i = 0;
		int nextY = y + dy[i];
		int nextX = x + dx[i];
		
		while (true) {
			if (nextY == R || nextY == downAir[0]-1 || nextX == C) {
				i++;
				nextY = y + dy[i];
				nextX = x + dx[i];
				continue;
			}
			
			if (map[nextY][nextX] == -1) {
				break;
			}
			
			if (map[y][x] != -1) {
				map[y][x] = map[nextY][nextX];
			}
			map[nextY][nextX] = 0;
			
			y = nextY;
			x = nextX;
			nextY = y+dy[i];
			nextX = x+dx[i];

		}
		
		
		// 공기 청정기 윗부분 계산
		
		dx = new int[] {0, 1, 0, -1};
		dy = new int[] {-1, 0, 1, 0};
		
		y = upAir[0];
		x = upAir[1];
		i = 0;
		nextY = y + dy[i];
		nextX = x + dx[i];
		while (true) {
			if (nextY == -1 || nextY == upAir[0]+1 || nextX == C) {
				i++;
				nextY = y + dy[i];
				nextX = x + dx[i];
				continue;
			}
			
			if (map[nextY][nextX] == -1) {
				break;
			}
			
			if (map[y][x] != -1) {
				map[y][x] = map[nextY][nextX];
			}
			map[nextY][nextX] = 0;
			
			y = nextY;
			x = nextX;
			nextY = y+dy[i];
			nextX = x+dx[i];
		}
	}

}