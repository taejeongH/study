import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); //endy
		int M = Integer.parseInt(st.nextToken()); //endx
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		int[] start = {0, 0, 1};
		int[] end = {N-1, M-1};
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		boolean[][] visited = new boolean[N][M];
		
		Queue<int[]> que = new LinkedList<>();
		que.add(start);
		
		int minCount = N*M;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int count = now[2];
			
			if(end[0] == y && end[1] == x) {
				minCount = count;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nextY = y+dy[i];
				int nextX = x+dx[i];
				if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < M) {
					if (map[nextY][nextX] == 1 && !visited[nextY][nextX]) {
						visited[nextY][nextX] = true;
						que.add(new int[] {nextY, nextX, count+1});
					}
				}
			}
			
		}
		
		System.out.println(minCount);
		
	}


}