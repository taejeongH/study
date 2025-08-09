import java.io.*;
import java.util.*;

public class Solution {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
//		File file = new File("src/sample_input (3).txt");
//		BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for (int test = 1; test <= testCase; test++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] map = new int[N][N];
			int[][] dp = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = input.charAt(j) - '0';
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
			
			Queue<int[]> que = new LinkedList<>();
			
			que.add(new int[] {0, 0, 0});
			int result = Integer.MAX_VALUE;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				int cnt = now[2];
				
				
				if(y == N-1 && x == N-1) {
					result = Math.min(result, cnt);

				}
				for (int i = 0; i < 4; i++) {
					int nextY = y + dy[i];
					int nextX = x + dx[i];
					
					if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < N) {
						if (dp[nextY][nextX] > cnt+map[nextY][nextX]) {
							que.add(new int[] {nextY, nextX, cnt+map[nextY][nextX]});
							dp[nextY][nextX] = cnt + map[nextY][nextX];
						}
					}
				}
				
				
			}
			
//			
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(dp[i]));
//			}
		
			System.out.println("#" + test + " " + result);
		}
		
	}

	

	




}
