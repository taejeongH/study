import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int N = Integer.parseInt(br.readLine());
		
		
		int sharkY = 0;
		int sharkX = 0;
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					sharkY = i;
					sharkX = j;
				}
			}
		}
		
		
		int time = 0;
		int sharkSize = 2;
		int sharkEatCount = 0;
		Queue<int[]> que = new LinkedList<int[]>();
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[2] != o2[2]) {
					return Integer.compare(o1[2], o2[2]);
				} else if(o1[0] != o2[0]) {
					return Integer.compare(o1[0], o2[0]);
				} else {
					return Integer.compare(o1[1], o2[1]);
				}
				
				
			}
		});
		while (true) {
			boolean[][] visited = new boolean[N][N];
			que.add(new int[] {sharkY, sharkX, 0});
			visited[sharkY][sharkX] = true;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				int dis = now[2];
				for (int i = 0; i < 4; i++) {
					int nextY = y+dy[i];
					int nextX = x+dx[i];
					
					if(nextY >= 0 && nextY < N && nextX >= 0 && nextX < N) {
						if(!visited[nextY][nextX] && map[nextY][nextX] <= sharkSize) {
							if (sharkSize == map[nextY][nextX] || map[nextY][nextX] == 0) {
								// 크기가 같고 0이라면 지나가기만 함
								visited[nextY][nextX] = true;
								que.add(new int[] {nextY, nextX, dis+1});
							} else {
								// 크기가 작다면 먹는다.
								visited[nextY][nextX] = true;
								que.add(new int[] {nextY, nextX, dis+1});
								pq.add(new int[] {nextY, nextX, dis+1});

							}
						}
					}
				}
			}
			
			//만약 먹을 수 있는 위치가 없을 경우 엄마부르기
			if (pq.isEmpty()) break;
			
			int[] next = pq.poll();
			
			//원래 있었던 위치는 0으로 바꾸고, 먹이를 먹는 위치는 9로 변경
			map[sharkY][sharkX] = 0;
			sharkY = next[0];
			sharkX = next[1];
			map[sharkY][sharkX] = 9;
			
			//이동한 거리만큼 시간을 더해줌(1초에 1칸)
			time += next[2];
			
			//현재 크기에서 먹은 크기의 개수 + 1
			sharkEatCount++;
			
			//만약 내 크기만큼 먹었다면, 크기를 1 증가시키고, 먹은 개수 초기화
			if (sharkEatCount == sharkSize) {
				sharkSize++;
				sharkEatCount = 0;
			}
			
			//프큐 비우기
			while(!pq.isEmpty()) pq.poll();
			
		}
		
		System.out.println(time);
	}
	

}