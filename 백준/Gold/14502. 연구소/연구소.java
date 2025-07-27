import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	public static int N;
	public static int M;
	public static int[][] map;
	public static boolean[][] btVisited;
	public static int[][] btResult;
	public static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		btVisited = new boolean[N][M];
		btResult = new int[3][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result = 0;
		bt(0);
		
		System.out.println(result);
	}
	public static void bt(int num) {
		if (num == 3) {
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
//			System.out.println("----------------------");
			bfs();
		} else { 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!btVisited[i][j] && map[i][j] == 0 ) {
						if (num == 0) {
							map[i][j] = 1;
							btVisited[i][j] = true;
							btResult[num][0] = i;
							btResult[num][1] = j;
							bt(num+1);
							map[i][j] = 0;
							btVisited[i][j] = false;
						} else {
							if (btResult[num-1][0] > i || btResult[num-1][1] > j) {
								map[i][j] = 1;
								btVisited[i][j] = true;
								btResult[num][0] = i;
								btResult[num][1] = j;
								bt(num+1);
								map[i][j] = 0;
								btVisited[i][j] = false;
							}
						}
						
					}
				}
			}
		}
	}
	
	public static void bfs() {
		int[][] calMap = new int[N][M];
		Queue<int[]> que = new LinkedList<>();
		
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		boolean[][] visited = new boolean[N][M];
		
		int safeArea = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				calMap[i][j] = map[i][j];
				if (calMap[i][j] == 2) {
					que.add(new int[] {i, j});
				} else if(calMap[i][j] == 0) {
					safeArea++;
				}
			}
		}
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nextY = y + dy[i];
				int nextX = x + dx[i];
				
				if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < M) {
					if(!visited[nextY][nextX] && calMap[nextY][nextX] == 0) {
						que.add(new int[] {nextY, nextX});
						visited[nextY][nextX] = true;
						safeArea--;
					}
				}
			}
			
		}
		
		result = Math.max(safeArea, result);
	}
}