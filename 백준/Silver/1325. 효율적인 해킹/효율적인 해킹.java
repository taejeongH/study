import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static ArrayList<Integer>[] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //동기의 수
		int M = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			map[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int end = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			map[start].add(end);
		}
		
		int[] result = new int[N+1];
		
		int max = 0; // 한 번에 가장 많이 해킹할 수 있는 개수
		for (int i = 1; i <= N; i++) {
			if (map[i].size() > 0) {
				result[i] = bfs(i); //해킹할 수 있는 개수
				max = Math.max(max, result[i]);
			}
		}
		
//		System.out.println(Arrays.toString(result));
		for (int i = 1 ; i <= N; i++) {
			if (result[i] == max) {
				System.out.print(i + " ");
			}
		}
	}
	
	//return start를 해킹했을 때 해킹할 수 있는 총 컴퓨터 수
	public static int bfs(int start) {
		Queue<Integer> que = new LinkedList<>();
		que.add(start);
		int cnt = 0;
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		while(!que.isEmpty()) {
			int now = que.poll();
			for (int i = 0; i < map[now].size(); i++) {
				if (!visited[map[now].get(i)]) {
					visited[map[now].get(i)] = true;
					que.add(map[now].get(i));
					cnt++;
				}
			}
		}
		return cnt;
	}
}
