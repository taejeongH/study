//package BOJ.스타트택시19238;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {0, -1, 1, 0};
	static int[] dy = {-1, 0, 0, 1};
	static boolean[] isDrived;
	static int N;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/BOJ/스타트택시19238/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());//맵크기
		int M = Integer.parseInt(st.nextToken());//손님수
		int K = Integer.parseInt(st.nextToken());//초기연료양
		
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int driverStartY = Integer.parseInt(st.nextToken())-1;
		int driverStartX = Integer.parseInt(st.nextToken())-1;
		
		int[][] customers = new int[M+2][4];
		//손님정보
		for (int i=2; i<M+2; i++) {
			st = new StringTokenizer(br.readLine());
			customers[i][0] = Integer.parseInt(st.nextToken())-1;
			customers[i][1] = Integer.parseInt(st.nextToken())-1;
			customers[i][2] = Integer.parseInt(st.nextToken())-1;
			customers[i][3] = Integer.parseInt(st.nextToken())-1;
			
			map[customers[i][0]][customers[i][1]] = i;
			
		}
		
		
		isDrived = new boolean[M+2];
		int curFuel = K;
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {driverStartY, driverStartX});
		int customerCount = 0;
		while(!que.isEmpty()) {
			int[] next = bfs(que.poll());
			int nextDistance = next[2];
			if(nextDistance == -1 || curFuel < nextDistance) {
				curFuel = -1;
				break;
			}
			int nextCustomer = map[next[0]][next[1]];
			
			curFuel -= nextDistance;
			int destination = findDestination(customers[nextCustomer]);
			if(destination == -1 || curFuel < destination) {
				curFuel = -1;
				break;
			}
			curFuel += destination;
			que.add(new int[] {customers[nextCustomer][2], customers[nextCustomer][3]});
			isDrived[nextCustomer] = true;
			if(++customerCount == M) break;
		}
		
		System.out.println(curFuel);
	}
	
	public static int[] bfs(int[] start) {
		int startY = start[0];
		int startX = start[1];
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {startY, startX, 0});
		boolean[][] visited = new boolean[N][N];
		visited[startY][startX] = true;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[]o2) {
				if(o1[2]==o2[2]) {
					if (o1[0]==o2[0]) {
						return o1[1]-o2[1];
					}
					return o1[0]-o2[0];
				}
				return o1[2]-o2[2];
			}
		});
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int c = now[2];
			if(map[y][x] != 0 && !isDrived[map[y][x]]) {
				pq.add(new int[] {y, x, c});
			}
			
			for (int i=0; i<4; i++) {
				int nextY = y+dy[i];
				int nextX = x+dx[i];
				if(nextY>=0 && nextY<N && nextX>=0 && nextX<N && !visited[nextY][nextX] && map[nextY][nextX] != 1) {
					visited[nextY][nextX] = true;
					que.add(new int[] {nextY, nextX, c+1});
				}
			}
		}
		
		if(!pq.isEmpty()) return pq.peek();
		
		return new int[] {-1, -1, -1};
	}
	
	public static int findDestination(int[] customersPos) {
		int startY = customersPos[0];
		int startX = customersPos[1];
		int endY = customersPos[2];
		int endX = customersPos[3];
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {startY, startX, 0});
		boolean[][] visited = new boolean[N][N];
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int c = now[2];
			
			if(y==endY && x==endX) {
				return c;
			}
			
			for (int i=0; i<4; i++) {
				int nextY = y+dy[i];
				int nextX = x+dx[i];
				if(nextY>=0 && nextY<N && nextX>=0 && nextX<N && !visited[nextY][nextX] && map[nextY][nextX] != 1) {
					visited[nextY][nextX] = true;
					que.add(new int[] {nextY, nextX, c+1});
				}
			}
		}
		return -1;
	}
}
