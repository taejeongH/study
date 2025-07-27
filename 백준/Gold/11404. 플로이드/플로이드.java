import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main {
	static ArrayList<ArrayList<Node>> map;
	static int N;
	
	static class Node {
		int idx;
		int cost;
		
		Node (int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", cost=" + cost + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //도시의 개수
		int M = Integer.parseInt(br.readLine()); //버스의 개수
		
		map = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < N+1; i++) {
			map.add(new ArrayList<Node>());
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			map.get(start).add(new Node(end, cost));
		}
		
		int[][] distance = new int[N+1][N+1];
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < N+1; i++) {
			distance[i] = dijkstra(i);
			
			for (int j = 1; j < distance[i].length; j++) {
				if (distance[i][j] == Integer.MAX_VALUE) {
					distance[i][j] = 0;
				}
				sb.append(distance[i][j]).append(" ");
			}
			
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
		
	}
	
	public static int[] dijkstra(int start) {
		PriorityQueue<Node> que = new PriorityQueue<Node>(((o1, o2) -> Integer.compare(o1.cost, o2.cost)));
		int[] distance = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			distance[i] = Integer.MAX_VALUE;
			if (i == start) {
				distance[i] = 0;
			}
		}
		
		que.add(new Node(start, 0));
		
		while(!que.isEmpty()) {
			Node curNode = que.poll();
			
			if(distance[curNode.idx] < curNode.cost) continue;
			
			for (int i = 0; i < map.get(curNode.idx).size(); i++) {
				Node nextNode = map.get(curNode.idx).get(i);
				
				if (distance[nextNode.idx] > curNode.cost + nextNode.cost) {
					distance[nextNode.idx] = curNode.cost + nextNode.cost;
					que.add(new Node(nextNode.idx, curNode.cost+nextNode.cost));
				}
			}
		}
		
		return distance;
		
		
	}

	
}