import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int maxCore;
	static int minLength;
	static int[][] map;
	static boolean[][] visited;
	static List<int[]> corePos;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		//File file = new File("src/sample_input (3).txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader(file));
		
		int testCase = Integer.parseInt(br.readLine());
		for (int test = 1; test <= testCase; test++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			corePos = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						corePos.add(new int[] {i, j});
					}
				}
			}
			
			maxCore = 0;
			minLength = Integer.MAX_VALUE;
			bt(0, 0, 0);
			System.out.println("#" + test + " " + minLength);
		}
		
	}
	
	public static void bt(int num, int connectCount, int length) {
		if (num == corePos.size()) {
			if (maxCore <= connectCount) {
				if (maxCore == connectCount) {
					minLength = Math.min(minLength, length);
				} else {
					maxCore = connectCount;
					minLength = length;
				}
			}
//			for (int i = 0; i < visited.length; i++) {
//				System.out.println(Arrays.toString(visited[i]));
//			}
//			System.out.println();
		} else {
			int y = corePos.get(num)[0];
			int x = corePos.get(num)[1];
			if (y == 0 || x == 0 || y == N-1 || x == N-1) {
				bt(num+1, connectCount+1, length);
			} else {
				for (int i = 0; i < 4; i++) {
					int len = check(new int[] {y, x}, new int[] {dy[i],dx[i]}, true);
					if (len != 0) {
						bt(num+1, connectCount+1, length+len);
						check(new int[] {y, x}, new int[] {dy[i], dx[i]}, false);
					} else {
						bt(num+1, connectCount, length);
					}
				}
			}
		}
	}
	
	public static int check(int[] start, int[] dir, boolean check) {
		
		int y = start[0];
		int x = start[1];
		int len = 0;
		
		if (check) {
			boolean cant = true;
			while (true) {
				int nextY = y + dir[0];
				int nextX = x + dir[1];
				
				if (nextY > N-1 || nextY < 0 || nextX > N-1 || nextX < 0 ) {
					break;
				}
				if (map[nextY][nextX] == 1 || visited[nextY][nextX]) {
					cant = false;
					break;
				}
				
				y = nextY;
				x = nextX;
			}
			
			y = start[0];
			x = start[1];
			if(cant) {
				while (true) {
					int nextY = y + dir[0];
					int nextX = x + dir[1];
					if (nextY > N-1 || nextY < 0 || nextX > N-1 || nextX < 0) {
						break;
					}
					y = nextY;
					x = nextX;
					visited[y][x] = check;
					len++;
				}
			}
//			System.out.println(start[0] + " " + start[1] + " " + len);
			return len;
		} else {
			while (true) {
				int nextY = y + dir[0];
				int nextX = x + dir[1];
				
				if (nextY > N-1 || nextY < 0 || nextX > N-1 || nextX < 0) {
					break;
				}
				
				y = nextY;
				x = nextX;
				
				visited[y][x] = false;
			}
			return 0;
		}
		
	}
}
