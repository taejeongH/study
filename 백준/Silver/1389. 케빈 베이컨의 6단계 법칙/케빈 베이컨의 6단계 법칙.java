import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	public static int N;
	public static int M;
	public static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			map[start][end] = 1;
			map[end][start] = 1;
		}
		
		int minNode = 1;
		int minCnt = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int cnt = bfs(i);
			if (minCnt > cnt) {
				minCnt = cnt;
				minNode = i;
			} else if (minCnt == cnt) {
				if (minNode > i) {
					minNode = i;
				}
			}
		}
		System.out.println(minNode);
		//System.out.println(minCnt);
		
	}
	public static int bfs(int num) {
		boolean visited[] = new boolean[N+1];
		Queue<int[]> que = new LinkedList<>();
		
		que.add(new int[] {num, 0});
		visited[num] = true;
		
		int distanceSum = 0;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int pos = now[0];
			int distance = now[1];
			distanceSum += distance;
			
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && map[pos][i] != 0) {
					que.add(new int[] {i, distance+1});
					
					visited[i] = true;
					
				}
			}
		}
		return distanceSum;
		
	}
}