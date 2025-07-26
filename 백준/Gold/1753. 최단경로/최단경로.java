import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main {
	public static int V;
	
	static class Node {
		int idx, cost;
		
		Node(int idx, int cost) {
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
	    V = Integer.parseInt(st.nextToken());
	    int E = Integer.parseInt(st.nextToken());
	    int startNode = Integer.parseInt(br.readLine());
	    
	    ArrayList<ArrayList<Node>> arr = new ArrayList<ArrayList<Node>>();
	    for (int i = 0; i < V+1; i++) {
	    	arr.add(new ArrayList<Node>());
	    }
	    
	    for (int i = 0; i < E; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int start = Integer.parseInt(st.nextToken());
	    	int end = Integer.parseInt(st.nextToken());
	    	int cost = Integer.parseInt(st.nextToken());
	    	
	    	arr.get(start).add(new Node(end, cost));
	    }
	    
	    //
	    PriorityQueue<Node> que = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.cost, o2.cost)));
	    que.add(new Node(startNode, 0));
	    
	    //최소 거리를 저장할 배열 초기화
	    int[] distance = new int[V+1];
	    for (int i = 1; i < V+1; i++) {
	    	if(i != startNode) {
	    		distance[i] = Integer.MAX_VALUE;
	    	}
	    }
	    
	    while(!que.isEmpty()) {
	    	Node curNode = que.poll();
	    	
	    	//distance에 저장된 현재 노드의 거리가, 이 때까지 계산한 거리보다 작다면 계산할 필요 x
	    	if (distance[curNode.idx] < curNode.cost) {
	    		continue;
	    	}
	    	
	    	// 현재 거리에서 갈 수 있는 노드들 탐색
	    	for (int i = 0; i < arr.get(curNode.idx).size(); i++) {
	    		Node nextNode = arr.get(curNode.idx).get(i);
	    		
	    		//distance에 저장된 다음 노드의 거리가 다음 노드 까지의 거리보다 크다면, 최소값 업데이트하고 큐에 넣어줌
	    		if (distance[nextNode.idx] > curNode.cost + nextNode.cost) { 
	    			distance[nextNode.idx] = curNode.cost + nextNode.cost;
	    			que.add(new Node(nextNode.idx, curNode.cost+nextNode.cost));
	    		}
	    	}
	    	
	    }
	    
	    for (int i = 1; i < V+1; i++) {
	    	if (distance[i] == Integer.MAX_VALUE) { 
	    		System.out.println("INF");
	    	} else {
	    		System.out.println(distance[i]);
	    	}
	    }
	}
	
}