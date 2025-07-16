import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[M][N];
		boolean[][] visited = new boolean[M][N];
		Queue<int[]> que = new LinkedList<>();
		
		
		int zeroCount = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					que.add(new int[] {i, j});
					visited[i][j] = true;
				} else if(map[i][j] == 0) {
					zeroCount += 1;
				}
			}
		}
		
		que.add(new int[] {M+1, N+1});
		
		int cnt = 0;
		int toOneCount = 0;
		if(zeroCount > 0) {
			while (!que.isEmpty()) {
				int now[] = que.poll();
				int y = now[0];
				int x = now[1];
				
				//일수를 계산하기 위한 구분리스트를 큐에 추가
				if(y == M+1 && x == N+1) {
					if (que.size() == 0) {
						break;
					} else {
						que.add(now);
						cnt++;
						continue;
					}
				}
				
				//up
				if (y-1 >= 0 && !visited[y-1][x] && map[y-1][x] != -1) {
					visited[y-1][x] = true;
					que.add(new int[] {y-1, x});
					toOneCount += 1;
				}
				
				//down
				if (y+1 < M && !visited[y+1][x] && map[y+1][x] != -1) {
					visited[y+1][x] = true;
					que.add(new int[] {y+1, x});
					toOneCount += 1;
				}
				
				//right
				if (x+1 < N && !visited[y][x+1] && map[y][x+1] != -1) {
					visited[y][x+1] = true;
					que.add(new int[] {y, x+1});
					toOneCount += 1;
				}
				
				//left
				if (x-1 >= 0 && !visited[y][x-1] && map[y][x-1] != -1) {
					visited[y][x-1] = true;
					que.add(new int[] {y, x-1});
					toOneCount += 1;
				}
			}
			
			if(toOneCount != zeroCount) {
				System.out.println(-1);
			} else {
				System.out.println(cnt);
			}
		} else {
			System.out.println(0);
		}
	
	}
}