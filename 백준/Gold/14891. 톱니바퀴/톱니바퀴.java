import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
	static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[4][8]; //톱니바퀴 상태 저장
		int[][] beforeMap = new int[4][8];
		
		for (int i = 0; i < 4; i++) {
			String input = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		int K = Integer.parseInt(br.readLine()); //톱니바퀴 회전 오더 개수
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()); //1 = 시계방향, 0 = 회전 x, -1 = 반시계방향
			
			//시작하기 전 상태를 기준으로 비교해야하기 때문에 배열을 하나 더 만들어준다.
			for (int j = 0; j < 4; j++) {
				beforeMap[j] = Arrays.copyOf(map[j], 8);
			}

			turn(idx, dir); 
			//왼쪽계산
			int curDir = dir;
			for (int j = idx; j >= 1; j--) {
				int curIdx = j;
				int nextIdx = j-1;
				
				if (curDir != 0) {
					if (beforeMap[curIdx][6] != beforeMap[nextIdx][2]) {
						if (curDir == 1) {
							turn(nextIdx, -1);
							curDir = -1;
						} else if(curDir == -1) {
							turn(nextIdx, 1);
							curDir = 1;
						}
					} else {
						curDir = 0;
					}
				} else {
					break;
				}
			}
			
			//오른쪽계산
			curDir = dir;
			for (int j = idx; j < 3; j++) {
				int curIdx = j;
				int nextIdx = j+1;
				
				if (curDir != 0) {
					if (beforeMap[curIdx][2] != beforeMap[nextIdx][6]) {
						if (curDir == 1) {
							turn(nextIdx, -1);
							curDir = -1;
						} else if(curDir == -1) {
							turn(nextIdx, 1);
							curDir = 1;
						} 
					} else {
						curDir = 0;
					}
				} else {
					
					break;
				}
			}

		}
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result += map[i][0] * Math.pow(2, i);
		}
		
		System.out.println(result);
	}
	
	//map을 회전시킨다.
	public static void turn(int idx, int dir) {
		if (dir == 1) {
			//시계 방향 회전(오른쪽으로 이동)
			int[] newMap = new int[8];
			for (int i = 0; i < 7; i++) {
				newMap[i+1] = map[idx][i];
			}
			newMap[0] = map[idx][7];
			map[idx] = newMap;
		} else {
			//반시계 방향 회전
			int[] newMap = new int[8];
			for (int i = 0; i < 7; i++) {
				newMap[i] = map[idx][i+1];
			}
			newMap[7] = map[idx][0];
			map[idx] = newMap;
		}
	}
	

}