import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int M;
	static int K;
	static int[] map;
	static int[][] operation;
	static boolean[] visited;
	static int[] selected;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int result;
	
	public static void main(String[] args) throws Exception{
//		File file = new File("src/sample_input (3).txt");
//		BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); //y크기
		M = Integer.parseInt(st.nextToken()); //x크기
		K = Integer.parseInt(st.nextToken()); //회전 연산의 개수
		
		selected = new int[K];
		visited = new boolean[K];
		map = new int[N*M];
		
		int idx = 0;
		for (int i = 0; i <N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[idx++] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		operation = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			operation[i][0] = Integer.parseInt(st.nextToken()); //r
			operation[i][1] = Integer.parseInt(st.nextToken()); //c
			operation[i][2] = Integer.parseInt(st.nextToken()); //s
		}
		
		result = Integer.MAX_VALUE;
		bt(0);
		System.out.println(result);
	}

	
	public static void bt(int num) {
		if (num == K) {
			result = Math.min(turnMap(), result);
		} else {
			for (int i = 0; i < K; i++) {
				if(!visited[i]) {
					selected[num] = i;
					visited[i] = true;
					bt(num+1);
					selected[num] = 0;
					visited[i] = false;
				}
			}
		}
	}
	
	public static int turnMap() {
		int score = Integer.MAX_VALUE;
		
		int[][] turnedMap = new int[N][M];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				turnedMap[i][j] = idx++;
			}
		}
		
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < operation[selected[i]][2]; j++) {
				
				int startY = operation[selected[i]][0] - operation[selected[i]][2] - 1 + j;
				int startX = operation[selected[i]][1] - operation[selected[i]][2] - 1 + j;
				int endY = operation[selected[i]][0] + operation[selected[i]][2] - 1 - j;
				int endX = operation[selected[i]][1] + operation[selected[i]][2] - 1 - j;
				int temp = turnedMap[startY][startX];
				
				int curY = startY;
				int curX = startX;
				int dir = 0;
				while(true) {
					int nextY = curY + dy[dir];
					int nextX = curX + dx[dir];
					
					if (nextY == startY && nextX == startX) break;
					
					if (nextY > endY || nextY < startY || nextX > endX || nextX < startX) {
						dir++;
						continue;
					}
					
					turnedMap[curY][curX] = turnedMap[nextY][nextX];
					
					curY = nextY;
					curX = nextX;
				}
				turnedMap[startY][startX+1] = temp;
			}
			
			
		}
		
		

		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++ ) {
				sum += map[turnedMap[i][j]];
				
			}
			score = Math.min(score, sum);
		}
		
		
		return score;
	}
	
	
}
