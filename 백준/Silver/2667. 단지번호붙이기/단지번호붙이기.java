import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
	public static int N;
	public static int[][] map;
	public static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //size
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		int complexCount = 0;
		PriorityQueue<Integer> resultQueue = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					int houseCount = bfs(i, j);
					if (houseCount > 0) {
						complexCount += 1;
						resultQueue.add(houseCount);
					}
					
				}
			}
		}
		
		
		System.out.println(complexCount);
		while(!resultQueue.isEmpty()) {
			System.out.println(resultQueue.poll());
		}
		
	}
	
	public static int bfs(int startY, int startX) {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {startY, startX});
		visited[startY][startX] = true;
		
		int houseCount = 1;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];

			
			for (int i = 0; i < 4; i++) {
				int nextY = y+dy[i];
				int nextX = x+dx[i];
				if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < N) {
					if (!visited[nextY][nextX] && map[nextY][nextX] == 1) {
						visited[nextY][nextX] = true;
						que.add(new int[] {nextY, nextX});
						houseCount++;
					}
				}
			}
			
		}
		return houseCount;
	}


}