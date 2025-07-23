import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main{
	public static char[][] map;
	public static char[][] colorMap;
	public static boolean[][] visited;
	public static boolean[][] colorVisited;
	
	public static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		colorMap = new char[N][N];
		visited = new boolean[N][N];
		colorVisited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
				colorMap[i][j] = map[i][j];
				if (map[i][j] == 'G') {
					colorMap[i][j] = 'R';
				}
			}
		}
//		System.out.println(Arrays.deepToString(colorMap));
//		System.out.println(Arrays.deepToString(map));
		
		
		int result = 0;
		int colorResult = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					bfs(i, j);
					result++;
				}
				
				if(!colorVisited[i][j]) {
					colorBfs(i, j);
					colorResult++;
				}
			}
		}
		
		System.out.println(result + " " + colorResult);
		
	}
	
	public static void bfs(int startY, int startX) {
		
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		char currWords = map[startY][startX];
		
		Queue<int[]> que = new LinkedList<int[]>();

		que.add(new int[] {startY, startX});
		
		while (!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nextY = y+dy[i];
				int nextX = x+dx[i];
				
				if(nextY >= 0 && nextY < N && nextX >= 0 && nextX < N) {
					if (!visited[nextY][nextX] && map[nextY][nextX] == currWords) {
						visited[nextY][nextX] = true;
						que.add(new int[] {nextY, nextX});
					}
				}
			}
			
		}

		
	}
	public static void colorBfs(int startY, int startX) {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		char currWords = colorMap[startY][startX];
		
		Queue<int[]> que = new LinkedList<int[]>();

		que.add(new int[] {startY, startX});
		
		while (!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nextY = y+dy[i];
				int nextX = x+dx[i];
				
				if(nextY >= 0 && nextY < N && nextX >= 0 && nextX < N) {
					if (!colorVisited[nextY][nextX] && colorMap[nextY][nextX] == currWords) {
						colorVisited[nextY][nextX] = true;
						que.add(new int[] {nextY, nextX});
					}
				}
			}
			
			}
	
			
	}
	
	

}