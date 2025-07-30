import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<Integer, String> changeDir = new HashMap<>();
		int N = Integer.parseInt(br.readLine());	//보드의 사이즈
		int K = Integer.parseInt(br.readLine());	//사과의 개수
		boolean[][] visited = new boolean[N][N];
		int[][] map = new int[N][N];
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y-1][x-1] = 1;
		}
		
		int L = Integer.parseInt(br.readLine());	//방향 변환 횟수
		Queue<String> dirList = new LinkedList<>();
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			changeDir.put(time, dir);
			dirList.add(dir);
		}
		
		//꼬리와 머리의 현재 위치를 저장
		int[] headPos = {0, 0};
		int[] tailPos = {0, 0};
		
		//방향 전환을 위한 direction 배열
		int[] clockDx = {1, 0, -1, 0};
		int[] clockDy = {0, -1, 0, 1};
		Queue<int[]> dirQue = new LinkedList<>();
		//dirQue.add(new int[] {0, 1});
		
		int second = 0; //초계산
		int dir = 0; //머리 방향 인덱스
		int tailDir = 0; //꼬리 방향 인덱스
		while(true) {
			int nextHeadY = headPos[0] + clockDy[dir];
			int nextHeadX = headPos[1] + clockDx[dir];

			//몸에 부딪히거나, 벽에 부딪히면 종료
			if (nextHeadY < 0 || nextHeadY >= N || nextHeadX < 0 || nextHeadX >= N || visited[nextHeadY][nextHeadX]) {
				break;
			}
			
			//머리 이동
			headPos[0] = nextHeadY;
			headPos[1] = nextHeadX;
			dirQue.add(new int[] {clockDy[dir], clockDx[dir]});
			visited[headPos[0]][headPos[1]] = true;
			
			second++;
			
			//머리 방향 전환
			if (changeDir.containsKey(second)) {
				if (changeDir.get(second).equals("L")) {
					dir++;
					dir = dir % 4;
				} else {
					//오른 쪽으로 회전
					dir--;
					if(dir == -1) {
						dir = 3;
					}
				}
				
			}

			//이동한 곳에 사과가 있다면 -> 꼬리이동 X
			if (map[nextHeadY][nextHeadX] == 1) {
				map[nextHeadY][nextHeadX] = 0;
			} else {
				//이동한 곳에 사과가 없다면 -> 꼬리이동 O
				// 꼬리가 어느 방향으로 회전해야 하는가? -> 
				visited[tailPos[0]][tailPos[1]] = false; //꼬리가 이동하는 경우 현재 있던 자리를 False
				
				int[] nextTailDir = dirQue.poll();
				//꼬리 이동
				tailPos[0] += nextTailDir[0];
				tailPos[1] += nextTailDir[1];			
				}
				
			
			
//			System.out.println("Head Position: " + headPos[0] + " " + headPos[1]);
//			System.out.println("Tail Position: " + tailPos[0] + " " + tailPos[1]);
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(visited[i]));
//			}
			
		}
		
		System.out.println(second+1);
		
		
			
	}
}