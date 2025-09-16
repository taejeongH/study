//package BOJ.미확인도착지;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static List<int[]>[] g;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //교차로개수
			int M = Integer.parseInt(st.nextToken()); //도로개수
			int T = Integer.parseInt(st.nextToken()); //후보개수
			
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken()); //시작점
			int G = Integer.parseInt(st.nextToken()); //교차로1
			int H = Integer.parseInt(st.nextToken()); //교차로2
			
			g = new List[N+1]; for (int i=1; i<N+1; i++) g[i] = new ArrayList<>();
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				g[s].add(new int[] {e, c});
				g[e].add(new int[] {s, c});
			}
			
			
			int[] sDis = dijkstra(S);
			int[] gDis = dijkstra(G);
			int[] hDis = dijkstra(H);
			
			ArrayList<Integer> arr = new ArrayList<>();
			for (int i=0; i<T; i++) {
				int candidate = Integer.parseInt(br.readLine());
				
				int fromG = gDis[candidate];
				int fromH = hDis[candidate];
				int fromS = sDis[candidate];
				int fromStoH = sDis[H];
				int fromStoG = sDis[G];
				
				if (fromStoH < fromStoG) {
					if(fromStoH+hDis[G]+fromG == fromS) arr.add(candidate);
				} else {
					if(fromStoG+gDis[H]+fromH == fromS) arr.add(candidate);
				}
			}
			
			Collections.sort(arr);
			for (int N : arr) {
				sb.append(N).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static int[] dijkstra(int start) {
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
			for (int[] ec : g[node]) {
				if (distance[ec[0]] > dis+ec[1]) {
					distance[ec[0]] = dis + ec[1];
					que.add(new int[] {ec[0], distance[ec[0]]});
				}
			}
		}
		
		return distance;
	}
}


/* 요란한 옷차림을 한 서커스 예술가 한 쌍이 한 도시의 거리들을 이동
 * 그들이 어디로 가고 있는지 알아내는 것
 * S지점에 출발했다는 것, 목적지 후보들
 * 목적지까지 우회하지 않고 최단거리로 갈 것
 * 
 * 출발지와 목적지 후보들, g와 h교차로 사이에 있는 도로를 이용했다는 것
 * 
 * 
 * 2 -> 5 최단 거리 : 5 (3, 1) 교차로를 지나지 않음
 * 2 -> 6 최단 거리 : 6
 * 
 * 시작점에서 도착지 후보들 까지의 최단 거리를 구할 때 (g, h) 교차로를 지나가는가?
 * 
 * 플로이드 워셜로 각 
*/
