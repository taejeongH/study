import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //동기의 수
		int M = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] map = new ArrayList[N+1];
		
		for (int i = 0; i < map.length; i++) {
			map[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			map[start].add(end);
			map[end].add(start);
		}
		
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(map[i].toString());
//		}
		
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {1, 0});
		boolean[] visited = new boolean[N+1];
		visited[1] = true;
		int result = 0;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int curr = now[0];
			int dis = now[1];
			
			
			if(dis > 1) continue;
			
			for (int i = 0; i < map[curr].size(); i++) {
				if(!visited[map[curr].get(i)]) {
					visited[map[curr].get(i)] = true;
					que.add(new int[] {map[curr].get(i), dis+1});
					result++;
				}
			}
		}
		System.out.println(result);
		
	}
}
