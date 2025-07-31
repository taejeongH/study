import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	public static int N;
	public static int M;
	public static int[][] map;
	public static int sum = 0;
	public static int result;
	public static int[] dx = {0, 0, -1, 1};
	public static int[] dy = {-1, 1, 0, 0};
	public static boolean[][] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //지도의 세로 크기
		M = Integer.parseInt(st.nextToken()); //지도의 가로 크기

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				bt(1, i, j, map[i][j]);
				bt2(1, i, j, map[i][j]);
				visited[i][j] = false;
			}
		}
//		visited[1][2] = true;
//		bt2(1, 2, 0, map[2][0]);
		System.out.println(result);
			
	}
	
	public static void bt(int num, int y, int x, int cnt) { //num = {num, y, x, cnt};
		if (num == 4) {
			result = Math.max(result, cnt);
		} else {
			for (int i = 0; i < 4; i++) {
				int nextY = y + dy[i];
				int nextX = x + dx[i];
				if (nextY < N && nextY >= 0 && nextX < M && nextX >= 0 && !visited[nextY][nextX]) {
					sum += map[nextY][nextX];
					visited[nextY][nextX] = true;
					bt(num+1, nextY, nextX, cnt + map[nextY][nextX]);
					sum -= map[nextY][nextX];
					visited[nextY][nextX] = false;
				}
			}
		}
	}
	
	//ㅗ모양 계산
	public static void bt2(int num, int y, int x, int cnt) {
		if (num == 4) {
			result = Math.max(result, cnt);
		} else {
			for (int i = 0; i < 4; i++) {
				int nextY = y + dy[i];
				int nextX = x + dx[i];
				if (nextY < N && nextY >= 0 && nextX < M && nextX >= 0 && !visited[nextY][nextX]) {
					visited[nextY][nextX] = true;
					bt2(num+1, y, x, cnt + map[nextY][nextX]);
					visited[nextY][nextX] = false;
				}
			}
		}
	}
}