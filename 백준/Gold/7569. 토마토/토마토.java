import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][][] map = new int[H][N][M];
		boolean[][][] visited = new boolean[H][N][M];
		
		Queue<int[]> que = new LinkedList<>();
		int zeroCount = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if (map[i][j][k] == 1) que.add(new int[] {i, j, k}); // H, N(y), M(x)
					else if (map[i][j][k] == 0) zeroCount++;
				}
			}
		}
		
		//0이 없다면 계산할 필요 없음
		if (zeroCount == 0) {
			System.out.println(0);
		} else {
			int[] dx = {0, 0, -1, 1};
			int[] dy = {-1, 1, 0, 0};
			int[] dh = {1, -1};
			que.add(new int[] {-1});
			int day = 0;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				
				if (now[0] == -1) {
					if (que.size() != 0) {
						day += 1;
						que.add(new int[] {-1});
					}
					continue;
				}
				
				int h = now[0];
				int y = now[1];
				int x = now[2];
				
				//y, x 방향 계산
				for (int i = 0; i < 4; i++) {
					int nextY = y + dy[i];
					int nextX = x + dx[i];
					
					if(nextY < N && nextY >= 0 && nextX < M && nextX >= 0) {
						if (!visited[h][nextY][nextX] && map[h][nextY][nextX] != -1) {
							visited[h][nextY][nextX] = true;
							que.add(new int[] {h, nextY, nextX});
							map[h][nextY][nextX] = 1;
						}
					}
				}
				
				//h 계산
				for (int i = 0; i < 2; i++) {
					int nextH = h + dh[i];
					if(nextH < H && nextH >= 0) {
						if (!visited[nextH][y][x] && map[nextH][y][x] != -1) {
							visited[nextH][y][x] = true;
							que.add(new int[] {nextH, y, x});
							map[nextH][y][x] = 1;
						}
					}
				}
				
				
			}
			
			//map에 0이 있다면 = 못 가는 곳이 있다면 -1출력 아닐경우 day 출력
			boolean flag = false;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < M; k++) {
						if (map[i][j][k] == 0) {
							flag = true;
							break;
						}
					}
					if (flag) break;
				}
				if (flag) break;
			}
			
			if (flag) {
				System.out.println(-1);
			} else {
				System.out.println(day);
			}
		}
		
	}


	

}