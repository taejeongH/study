import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //map의 크기 = NxN
		int M = Integer.parseInt(st.nextToken()); //심은 나무의 개수
		int K = Integer.parseInt(st.nextToken()); //반복 횟수
		
		int[][] orgMap = new int[N][N];
		int[][] map = new int[N][N];
		Deque<Integer>[][] stkMap = new ArrayDeque[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = 5; //초기 양분 값은 5이다.
				orgMap[i][j] = Integer.parseInt(st.nextToken()); //A[r][c]
				stkMap[i][j] = new ArrayDeque();
			}
		} 
		
		//기존에 심어져 있던 나무 입력 받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			stkMap[x-1][y-1].add(z);
		}

		int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
		for (int year = 0; year < K; year++) {
			
			//봄, 여름
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int iter = stkMap[i][j].size();
					int deadTreeSum = 0; 
					for (int k = 0; k < iter; k++) {
						//현재 있는 위치의 나무의 개수만큼 반복.
						
						int num = stkMap[i][j].pollLast();
						if (num > map[i][j]) {
							deadTreeSum += num/2;
						} else {
							//먹을 양분이 충분하다면, 나이  + 1을 넣는다.
							stkMap[i][j].addFirst(num+1);
							map[i][j] -= num;
						}
					}
					map[i][j] += deadTreeSum;
				}
			}
			
			//가을, 겨울 (나무 번식, 양분 추가)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int iter = stkMap[i][j].size();
					for (int k = 0; k < iter; k++) {
						//현재 있는 위치의 나무의 개수만큼 반복
						
						int num = stkMap[i][j].pollLast();
						if (num % 5 == 0) {
							for (int d = 0; d < 8; d++) {
								int nextY = i + dy[d];
								int nextX = j + dx[d];
								if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < N) {
									stkMap[nextY][nextX].addLast(1);
								}
							}
						}
						stkMap[i][j].addFirst(num);
					}
						

						
					map[i][j] += orgMap[i][j]; //양분 추가
				}
			}
			
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(stkMap[i][j] + " ");
//				}
//				System.out.println();
//			}
		}//K년 만큼 반복
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result += stkMap[i][j].size();
			}
		}
		System.out.println(result);
	}

}