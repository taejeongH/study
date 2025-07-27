import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main {
	static ArrayList<ArrayList<Node>> arr;
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
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		arr = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < N+1; i++) {
			arr.add(new ArrayList<Node>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			arr.get(start).add(new Node(end, cost));
			arr.get(end).add(new Node(start, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		int u = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		int[] uDist = new int[N+1];
		int[] vDist = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			
			uDist[i] = Integer.MAX_VALUE;
			vDist[i] = Integer.MAX_VALUE;
			
			if(i == u) {
				uDist[i] = 0;
			} else if(i == v) {
				vDist[i] = 0;
			}
		}
		
		dijkstra(u, uDist);
		dijkstra(v, vDist);
		
//		System.out.println(Arrays.toString(uDist));
//		System.out.println(Arrays.toString(vDist));
		int min = Integer.MAX_VALUE;
		
		if (uDist[1] != Integer.MAX_VALUE && uDist[v] != Integer.MAX_VALUE && vDist[N] != Integer.MAX_VALUE) {
			min = Math.min(uDist[1] + uDist[v] + vDist[N], min);
		}
		
		if (vDist[1] != Integer.MAX_VALUE && vDist[u] != Integer.MAX_VALUE && uDist[N] != Integer.MAX_VALUE) {
			min = Math.min(vDist[1] + vDist[u] + uDist[N], min);
		}
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
		
	}
	
	public static void dijkstra(int start, int[] distance) {
		PriorityQueue<Node> que = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		
		que.add(new Node(start, 0));
		
		while(!que.isEmpty()) {
			Node curNode = que.poll();
			//if(curNode.idx == N || curNode.idx == 1) continue;
			
			
			if(distance[curNode.idx] < curNode.cost) {
				continue;
			}
			
			
			
			for (int i = 0; i < arr.get(curNode.idx).size(); i++) {
				Node nextNode = arr.get(curNode.idx).get(i);
				
				if (distance[nextNode.idx] > curNode.cost + nextNode.cost) {
					distance[nextNode.idx] = curNode.cost + nextNode.cost;
					que.add(new Node(nextNode.idx, curNode.cost+nextNode.cost));
				}
			}
			
		}
		
	}

	
}