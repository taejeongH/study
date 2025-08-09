import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int M;
	static int D;
	static int[] select = new int[3]; //궁수 3명의 x좌표
	static boolean[] visited;
	static int[] dx = {-1,  0, 1};
	static int[] dy = {0, -1, 0};
	static int[][] map;
	static int result;
	
	public static void main(String[] args) throws Exception{
//		File file = new File("src/sample_input (3).txt");
//		BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); //y크기
		M = Integer.parseInt(st.nextToken()); //x크기
		D = Integer.parseInt(st.nextToken()); //궁수의 거리
		visited = new boolean[M];
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bt(0, 0);
		
		System.out.println(result);
	}

	
	public static void bt(int num, int start) {
		if (num == 3) {
			result = Math.max(calAttack(), result);
			
		} else {
			for (int i = start; i < M; i++) {
				if(!visited[i]) {
					select[num] = i;
					visited[i] = true;
					bt(num+1, i);
					select[num] = 0;
					visited[i] = false;
				}
			}
		}
	}
	
	public static int calAttack() {
		int[][] enemy = new int[3][2];
		for (int i = 0; i < enemy.length; i++) {
			enemy[i][0] = -1;
		}
		int score = 0;
		int castlePos = N;
		boolean[][] visited = new boolean[N][M];
		while (castlePos > 0) {
			//배치한 궁수별 가장 가까운 적 찾기
			for (int k = 0; k < 3; k++) {
				ArrayDeque<int[]> que = new ArrayDeque<>();
				que.add(new int[] {castlePos, select[k], 0});
				while(!que.isEmpty()) {
					int[] now = que.poll();
					int y = now[0];
					int x = now[1];
					int dis = now[2];
					
					if(y < N && x < M && map[y][x] == 1 && !visited[y][x]) {
						enemy[k][0] = y;
						enemy[k][1] = x;
						break;
					}
					
					for (int i = 0; i < 3; i++) {
						int nextY = y + dy[i];
						int nextX = x + dx[i];
						if(nextY != castlePos && nextY >= 0 && nextY < N && nextX >= 0 && nextX < M) {
							if (dis+1 <= D) {
								que.add(new int[] {nextY, nextX, dis+1});
							}
						}
					}
					
				}
			
			}
			//적 제거
			for (int i = 0; i < enemy.length; i++) {
				if (enemy[i][0] != -1 && !visited[enemy[i][0]][enemy[i][1]]) {
					visited[enemy[i][0]][enemy[i][1]] = true;
					score++;
				}
				enemy[i][0] = -1;
			}
			
			// 적을 앞으로 땡긴다.
			castlePos--;
		}
		return score;
		
	}
}
