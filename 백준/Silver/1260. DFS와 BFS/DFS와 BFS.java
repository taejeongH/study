import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int vertexCount = Integer.parseInt(st.nextToken());
		int edgeCount = Integer.parseInt(st.nextToken());
		int startVertex = Integer.parseInt(st.nextToken());
		int[][] map = new int[vertexCount+1][vertexCount+1];
		boolean[] dfsVisited = new boolean[vertexCount+1];
		boolean[] bfsVisited = new boolean[vertexCount+1];
		

		for (int i = 0; i < edgeCount; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[start][end] = 1;
			map[end][start] = 1;
		}

		Queue<Integer> que = new LinkedList<Integer>();
		Stack<Integer> stk = new Stack<>();
		stk.add(startVertex);
		que.add(startVertex);
		
		
		while(!stk.isEmpty()) {
			int now = stk.pop();
			if(dfsVisited[now]) continue;
			
			dfsVisited[now] = true;
			
			System.out.print(now + " ");
			
			for (int i = map.length-1; i >= 0; i--) {
				if (map[now][i] == 1) {
					stk.add(i);
				}
			}
			
		}
		
		System.out.println();
		
		
		while(!que.isEmpty()) {
			int now = que.poll();
			if(bfsVisited[now]) continue;
			
			bfsVisited[now] = true;
			
			System.out.print(now + " ");
			
			for (int i = 1; i < map.length; i++) {
				if (map[now][i] == 1) {
					que.add(i);
				}
			}
			
		}
	}

}