
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine()); //도시의 개수
		int M = Integer.parseInt(br.readLine()); //버스의 개수
		
		List<int[]>[] g = new List[N+1]; for (int i=1; i<N+1; i++) g[i]=new ArrayList<>();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			g[s].add(new int[] {e, c});
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		que.add(new int[] {start, 0});
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int node = now[0];
			int dis = now[1];
			
			if(dis > distance[node]) continue;
			
			for (int[]jc : g[node]) {
				if (distance[jc[0]] > dis+jc[1]) {
					distance[jc[0]]=dis+jc[1];
					que.add(new int[] {jc[0], dis+jc[1]});
				}
			}
		}
		
		System.out.println(distance[end]);
	}

}


/*
 * 1~N 번호 매겨져 있음
 * 도시들 사이에는 버스가 운행중
 * 버스는 시작 도시와 도착 도시, 비용으로 표현됨
 * 여러 개의 버스가 같은 두 도시를 오갈 수 있으며 비용이 다를 수 있음
 * 가장 적은 비용을 들이고 싶음
 * 출발 도시와 도착 도시가 주어질 때, 삼성이가 도착하는 최소비용
 * 출발점 -> 다익스트라
*/
