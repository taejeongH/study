import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[][] map = new String[N][M];
		boolean[][] visited = new boolean[N][M];
		int[] start = new int[2];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Character.toString(input.charAt(j));
				if(map[i][j].equals("I")) {
					start = new int[] {i, j};
				}
			}
		}
		
		Queue<int[]> que = new LinkedList<int[]>();
		
		que.add(start);
		
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		int result = 0;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			
			if(map[y][x].equals("P")) {
				result++;
			}
			
			for (int i = 0; i < 4; i++) {
				int nextY = y + dy[i];
				int nextX = x + dx[i];
				
				if(nextY < N && nextY >= 0 && nextX < M && nextX >= 0 && !map[nextY][nextX].equals("X") && !visited[nextY][nextX]) {
					que.add(new int[] {nextY, nextX});
					visited[nextY][nextX] = true;
				}
			}
			
		}
		
		if(result == 0) {
			System.out.println("TT");
		} else {
			System.out.println(result);
		}
		
		
		
		
 	}
}