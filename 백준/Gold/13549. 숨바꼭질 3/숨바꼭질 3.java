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
		int K = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[K+N+1];
		
		Queue<int[]> que = new LinkedList<>();
		
		que.add(new int[] {N, 0});
		int result = 10000000;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int pos = now[0];
			if (pos == K) {
				result = Math.min(now[1], result);
			}
			
			if (pos*2 < visited.length && !visited[pos*2]) {
				visited[pos*2] = true;
				que.add(new int[] {pos*2, now[1]});
			}
			
			if (pos-1 >= 0 && !visited[pos-1]) {
				visited[pos-1] = true;
				que.add(new int[] {pos-1, now[1]+1});
			}

			
			if (pos+1 < visited.length && !visited[pos+1]) {
				visited[pos+1] = true;
				que.add(new int[] {pos+1, now[1]+1});
			}
			
			
		}
		
		System.out.println(result);
	}
}