import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main {
	static int N;
	static ArrayList<ArrayList<Node>> map;
	
	static class Node {
		int idx;
		int cost;
		
		Node (int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken()); //마을의 개수 (정점 개수)
		int M = Integer.parseInt(st.nextToken()); //도로 개수 (간선 개수)
		int X = Integer.parseInt(st.nextToken()); //도착 지점
		
		map = new ArrayList<>();
		
		for (int i = 0; i < N+1; i++) {
			map.add(new ArrayList<Node>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map.get(start).add(new Node(end, cost));
		}
		
		int[] dis = dijkstra(X);
		
		int max = 0;
		for (int i = 1; i < N+1; i++) {
			if (i != X) {
				int[] curDis = dijkstra(i);
				max = Math.max(dis[i] + curDis[X], max);
				
			}
		}
		
		
		System.out.println(max);
	}
	public static int[] dijkstra(int num) {
		int[] distance = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		int max = 0;
		
		PriorityQueue<Node> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		que.add(new Node(num, 0));
		while(!que.isEmpty()) {
			Node curNode = que.poll();
			
			if (distance[curNode.idx] < curNode.cost) continue;
			
			for (int i = 0; i < map.get(curNode.idx).size(); i++) {
				Node nextNode = map.get(curNode.idx).get(i);
				
				if (distance[nextNode.idx] > curNode.cost+nextNode.cost) {
					distance[nextNode.idx] = curNode.cost+nextNode.cost;
					max = Math.max(curNode.cost+nextNode.cost, max);
					que.add(new Node(nextNode.idx, curNode.cost+nextNode.cost));
				}
			}
		}
		
		return distance;
		
	}
}