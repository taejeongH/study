//package BOJ.서강그라운드;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //지역 개수
		int M = Integer.parseInt(st.nextToken()); //수색 범위
		int R = Integer.parseInt(st.nextToken()); //길의 개수
		
		int[] items = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<N+1; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		List<int[]>[] g = new List[N+1]; for (int i=1; i<N+1; i++) g[i] = new ArrayList<>();
		for (int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			g[s].add(new int[] {e, c});
			g[e].add(new int[] {s, c});
		}
		
		PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		int res = 0;
		for (int i=1; i<N+1; i++) {
			int[] distance = new int[N+1];
			Arrays.fill(distance, Integer.MAX_VALUE);
			distance[i] = 0;
			que.add(new int[] {i, 0});
			int score = 0;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int node = now[0];
				int dis = now[1];
				if(dis > M) continue;
				if(dis > distance[node]) continue;
				
				score += items[node];
				
				for (int[] jc : g[node]) {
					if (distance[jc[0]] > dis+jc[1]) {
						distance[jc[0]] = dis+jc[1];
						que.add(new int[] {jc[0], dis+jc[1]});
					}
				}
			}
			res = Math.max(score, res);
		}
		System.out.println(res);
	}
}
