import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //지도의 세로 크기
		int M = Integer.parseInt(st.nextToken()); //지도의 가로 크기
		int y = Integer.parseInt(st.nextToken()); //주사위가 놓인 x좌표
		int x = Integer.parseInt(st.nextToken()); //주사위가 놓인 y좌표
		int K = Integer.parseInt(st.nextToken()); //명령의 개수
		
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] diceNum = new int[7];
		
		int[] row = {1, 4, 6, 3};
		int[] column = {1, 5, 6, 2};
		
		int curY = y;
		int curX = x;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			
			int dir = Integer.parseInt(st.nextToken());
			
			if (dir == 1) {
				//동, 오른쪽
				curX += 1; //위치 이동
				
				if (curX < M) {
					//이동 후의 위치가 맵 안쪽이라면 주사위를 굴린다.
					int[] nextRow = new int[4];
					nextRow[0] = row[1];
					nextRow[1] = row[2];
					nextRow[2] = row[3];
					nextRow[3] = row[0];
					row = nextRow;
					
					if (map[curY][curX] == 0) {
						//도착한 곳이 0이라면  주사위의 바닥면이 지도에 복사된다.
						//주사위를 놓은 칸에 쓰여있는 수는 항상 0이다.

						map[curY][curX] = diceNum[7-row[0]];

					} else {
						//도착한 곳이 0이 아니라면 주사위의 바닥면으로 복사되고, 0이 된다.
						diceNum[7-row[0]] = map[curY][curX];
						map[curY][curX] = 0;
					}
				
					column[0] = row[0];
					column[2] = 7-row[0];
					System.out.println(diceNum[row[0]]);
				} else {
					curX -= 1;
				}
				
				
			} else if(dir == 2) {
				//서, 왼
				curX -= 1;
				
				if (curX >= 0) {
					int[] nextRow = new int[4];
					nextRow[0] = row[3];
					nextRow[1] = row[0];
					nextRow[2] = row[1];
					nextRow[3] = row[2];
					row = nextRow;
					
					if (map[curY][curX] == 0) {
						map[curY][curX] = diceNum[7-row[0]];
						
					} else {
						diceNum[7-row[0]] = map[curY][curX];
						map[curY][curX] = 0;
					}
					column[0] = row[0];
					column[2] = 7-row[0];
					System.out.println(diceNum[row[0]]);
				} else {
					curX += 1;
				}
				
			} else if(dir == 3) {
				//북, 위
				curY -= 1;
				
				if (curY >= 0) {
					int[] nextColumn = new int[4];
					nextColumn[0] = column[1];
					nextColumn[1] = column[2];
					nextColumn[2] = column[3];
					nextColumn[3] = column[0];
					column = nextColumn;
					if (map[curY][curX] == 0) {
						map[curY][curX] = diceNum[7-column[0]];
					} else {
						diceNum[7-column[0]] = map[curY][curX];
						map[curY][curX] = 0;
					}
					row[0] = column[0];
					row[2] = 7-column[0];
					System.out.println(diceNum[column[0]]);
				} else {
					curY += 1;
				}
				
			} else {
				//남, 아래
				curY += 1;
				
				if (curY < N) {
					int[] nextColumn = new int[4];
					nextColumn[0] = column[3];
					nextColumn[1] = column[0];
					nextColumn[2] = column[1];
					nextColumn[3] = column[2];
					column = nextColumn;
					
					if (map[curY][curX] == 0) {
						map[curY][curX] = diceNum[7-column[0]];
					} else {
						diceNum[7-column[0]] = map[curY][curX];
						map[curY][curX] = 0;
					}
					row[0] = column[0];
					row[2] = 7-column[0];
					System.out.println(diceNum[column[0]]);
				} else {
					curY -= 1;
				}
				
			}
			//System.out.println("top: " + row[0]);
		}
		
			
	}
}