import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main{
	static boolean visited[][];
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int N;
	static int[][] map;
	static int L;
	static int R;
	static Stack<int[]> visitedArea;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //map의 크기 = NxN
		L = Integer.parseInt(st.nextToken()); //인구차이의 최소값
		R = Integer.parseInt(st.nextToken()); //인구차이의 최대값
		visited = new boolean[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int day = 0;
		while (true) {
			int canOpen = 0;
			visitedArea = new Stack<int[]>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited[i][j] = true;
					canOpen = Math.max(bfs(new int[] {i, j}), canOpen);
				}
			}
			
			if(canOpen == 0) {
				break;
			}
			
			//경계를 연 후 값을 평균으로 맞추는 작업.. 
			int avg = 0;
			while (!visitedArea.isEmpty()) {
				int[] now = visitedArea.pop();
				if (now[0] == -1) {
					avg = now[1];
					continue;
				}
				
				map[now[0]][now[1]] = avg;
				
			}
			day++;
			
			//visited 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited[i][j] = false;
				}
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		System.out.println(day);
		
		
	}
	
	public static int bfs(int[] start) {
		Queue<int[]> que = new LinkedList<>();
		
		visitedArea.add(start);
		que.add(start);
		int sum = map[start[0]][start[1]];
		int cnt = 1;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nextY = y + dy[i];
				int nextX = x + dx[i];
				if (nextY < N && nextY >= 0 && nextX < N && nextX >= 0 && !visited[nextY][nextX]) {
					int sub = Math.abs(map[y][x] - map[nextY][nextX]);
					if (sub >= L && sub <= R) {
						visited[nextY][nextX] = true;
						sum += map[nextY][nextX];
						cnt++;
						que.add(new int[] {nextY, nextX});
						visitedArea.add(new int[] {nextY, nextX});
					}
				}
			}
		}
		
		visitedArea.add(new int[] {-1, sum/cnt});
		
		if (cnt == 1) {
			return 0;
		}
		
		
		return 1;
	}

}