import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //vertex 개수
		int M = Integer.parseInt(st.nextToken()); //edge 개수
		int[][] map = new int[N+1][N+1]; //인접행렬
		boolean[] visited = new boolean[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[start][end] = 1;
			map[end][start] = 1;	//무방향 그래프
		}
		
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				Queue<Integer> que = new LinkedList<>();
				que.add(i);
				
				while(!que.isEmpty()) {
					int now = que.poll();
					for (int j = 1; j <= N; j++) {
						if (map[now][j] != 0 && !visited[j]) {
							visited[j] = true;
							que.add(j);
						}
					}
					
				}
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}