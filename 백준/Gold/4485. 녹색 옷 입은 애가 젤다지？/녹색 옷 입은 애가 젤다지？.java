import java.io.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = 1;
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			int[][] map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
			int[][] dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			
			que.add(new int[] {0, 0, map[0][0]});
			
			int[] dx = {0, 0, -1, 1};
			int[] dy = {-1, 1, 0, 0};
			
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				int c = now[2];
				
				if(y == N-1 && x == N-1) break;
				
				for (int i = 0; i < 4; i++) {
					int nextY = y+dy[i];
					int nextX = x+dx[i];
					if(nextY >= 0 && nextY < N && nextX >= 0 && nextX < N) {
						if (dp[nextY][nextX] > c + map[nextY][nextX]) {
							que.add(new int[] {nextY, nextX, c+map[nextY][nextX]});
							dp[nextY][nextX] = c+map[nextY][nextX];
						}
					}
				}
			}
			
			int result = dp[N-1][N-1];
			
			
			
			sb.append("Problem ").append(test).append(": ").append(result).append("\n");
			test++;
		}
		System.out.println(sb.toString());
	}
}
