import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class Main{
	public static int N;
	public static int result;
	public static String[] moveDir;
	public static int[][] mapOrg;
	public static int[][][] mapOrigin;	//이전 계산들을 저장해놓을 map
	public static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //보드의 크기
		map = new int[N][N]; //계산할 보드 저장
		mapOrg = new int[N][N];
		mapOrigin = new int[4][N][N]; //원래 보드 저장
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				mapOrg[i][j] = map[i][j];
			}
		}
		
		moveDir = new String[] {"up", "down", "right", "left"}; //up, down, left, right
		result = 0;
		
		if (N == 1) {
			System.out.println(map[0][0]);
		} else {
			bt(0, 0);
			System.out.println(result);
		}
		
		
	}
	
	//백트래킹
	public static void bt(int num, int maxValue) {
		if (num == 5) {	//최대 5번 이동, 최대 값 저장
			result = Math.max(result, maxValue);
		} else {
			for (int i = 0; i < 4; i++) {
				int max = move(moveDir[i]);
//				System.out.println(num);
//				System.out.println(max);
//				System.out.println(Arrays.deepToString(map));
				if(num != 4) {
					for (int j = 0; j < N; j++) {
						for (int k = 0; k < N; k++) {
							mapOrigin[num][j][k] = map[j][k];
						}
					}
				}
//				System.out.println(Arrays.deepToString(mapOrigin));
//				System.out.println(Arrays.deepToString(map));
				
				bt(num+1, max);
				if(num > 0) {
					for (int j = 0; j < N; j++) {
						for (int k = 0; k < N; k++) {
							map[j][k] = mapOrigin[num-1][j][k];
						}
					}
				} else {
					for (int j = 0; j < N; j++) {
						for (int k = 0; k < N; k++) {
							map[j][k] = mapOrg[j][k];
						}
					}
				}
			}
		}
	}
	
	//상, 하, 좌, 우 이동, 이동 후 얻을 수 있는 max값 반환
	public static int move(String direction) {
		int maxVal = 0;
		boolean[][] visited = new boolean[N][N]; //이미 합쳐진 것들은 안 합치도록 방문 처리
		//합쳐지지 
		for (int i = 0; i < N; i++) {
			maxVal = Math.max(maxVal, map[0][i]);
			maxVal = Math.max(maxVal, map[N-1][i]);
			maxVal = Math.max(maxVal, map[i][N-1]);
			maxVal = Math.max(maxVal, map[i][0]);
		}
		
		if (direction.equals("left")) {
			for (int i = 1; i < N; i++) {	//x
				for (int j = 0; j < N; j++) {
					if (map[j][i] != 0) {
						int nextX = i-1;
						while(nextX >= 0) {
							if (map[j][nextX] != 0) {
								if (map[j][nextX] == map[j][nextX+1]) {
									if (!visited[j][nextX]) {
										map[j][nextX] *= 2;
										map[j][nextX+1] = 0;
										visited[j][nextX] = true;
									}
									maxVal = Math.max(maxVal, map[j][nextX]);
									maxVal = Math.max(maxVal, map[j][nextX+1]);
									break;
								} else {
									maxVal = Math.max(maxVal, map[j][nextX]);
									maxVal = Math.max(maxVal, map[j][nextX+1]);
									break;
								}
							} else {
								map[j][nextX] = map[j][nextX+1];
								map[j][nextX+1] = 0;
							}
							maxVal = Math.max(maxVal, map[j][nextX]);
							nextX -= 1;

						}
					}
					
				}
			}
		} else if(direction.equals("right")) {
			for (int i = N-2; i >= 0; i--) {	//x
				for (int j = 0; j < N; j++) {
					if (map[j][i] != 0) {
						int nextX = i+1;
						while(nextX < N) {
							if (map[j][nextX] != 0) {
								if (map[j][nextX] == map[j][nextX-1]) {
									if (!visited[j][nextX]) {
										map[j][nextX] *= 2;
										map[j][nextX-1] = 0;
										visited[j][nextX] = true;
									}
									maxVal = Math.max(maxVal, map[j][nextX]);
									maxVal = Math.max(maxVal, map[j][nextX-1]);
									break;
								} else {
									maxVal = Math.max(maxVal, map[j][nextX]);
									maxVal = Math.max(maxVal, map[j][nextX-1]);
									break;
								}
							} else {
								map[j][nextX] = map[j][nextX-1];
								map[j][nextX-1] = 0;
							}
							maxVal = Math.max(maxVal, map[j][nextX]);
							nextX += 1;

						}
					}
					
				}
			}
			
		} else if(direction.equals("up")) {
			for (int i = 1; i < N; i++) {	//y
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0) {
						int nextY = i-1;
						while(nextY >= 0) {
							if (map[nextY][j] != 0) {
								if (map[nextY][j] == map[nextY+1][j]) {
									if (!visited[nextY][j]) {
										map[nextY][j] *= 2;
										map[nextY+1][j] = 0;
										visited[nextY][j] = true;
									}
									maxVal = Math.max(maxVal, map[nextY][j]);
									maxVal = Math.max(maxVal, map[nextY+1][j]);
									break;
								} else {
									maxVal = Math.max(maxVal, map[nextY][j]);
									maxVal = Math.max(maxVal, map[nextY+1][j]);
									break;
								}
							} else {
								map[nextY][j] = map[nextY+1][j];
								map[nextY+1][j] = 0;
							}
							maxVal = Math.max(maxVal, map[nextY][j]);
							nextY -= 1;
						}
					}
					
				}
			}
		} else {
			for (int i = N-2; i >= 0; i--) {	//y
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0) {
						int nextY = i+1;
						while(nextY < N) {
							if (map[nextY][j] != 0) {
								if (map[nextY][j] == map[nextY-1][j]) {
									if (!visited[nextY][j]) {
										map[nextY][j] *= 2;
										map[nextY-1][j] = 0;
										visited[nextY][j] = true;
									}
									
									maxVal = Math.max(maxVal, map[nextY][j]);
									maxVal = Math.max(maxVal, map[nextY-1][j]);
									break;
								} else {
									maxVal = Math.max(maxVal, map[nextY][j]);
									maxVal = Math.max(maxVal, map[nextY-1][j]);
									break;
								}
							} else {
								map[nextY][j] = map[nextY-1][j];
								map[nextY-1][j] = 0;
							}
							maxVal = Math.max(maxVal, map[nextY][j]);
							nextY += 1;
						}
					}
					
				}
			}
			
		}

		return maxVal;

	}
}