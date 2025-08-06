import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		ArrayList<Integer>[] map = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			map[i] = new ArrayList<>();
		}
		
		int[] inDegree = new int[N+1];
		for (int i = 0; i < M; i++) { 
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			map[start].add(end);
			inDegree[end]++;
		}
		
		Queue<int[]> que = new LinkedList<>();
		for (int i = 1 ; i < N+1; i++) {
			if (inDegree[i] == 0) {
				que.add(new int[] {i, 1});
			}
		}
		
		int[] cnt = new int[N+1];
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int num = now[0];
			int c = now[1];
			
			if (inDegree[num] == 0) {
				cnt[num] = c;
			}
			
			for (int i = 0; i < map[num].size(); i++) {
				inDegree[map[num].get(i)]--;
				if (inDegree[map[num].get(i)] == 0) {
					
					que.add(new int[] {map[num].get(i), c+1});
				}
			}
		}
		for (int i = 1; i < cnt.length; i++) {
			System.out.print(cnt[i] + " ");
		}
		
	}
	

}