import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main{
	static int[][] map = new int[101][101]; // 100으로 바꾸기
	static int[][] direct = { {0, 1}, {-1, 0}, {0, -1}, {1, 0} };
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //드래곤 커브의 개수
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); //드래곤 커브의 시작점x
			int y = Integer.parseInt(st.nextToken()); //드래곤 커브의 시작점y
			int d = Integer.parseInt(st.nextToken()); //드래곤 커브의 시작 방향
			int g = Integer.parseInt(st.nextToken()); //드래곤 커브의 세대

			//드래곤 세대가 0일 때
			int[] end = { y+direct[d][0], x+direct[d][1] };
			map[y][x] = 1;
			map[end[0]][end[1]] = 1;
			
			if (g > 0) {
				Queue<Integer> que = new LinkedList<Integer>();
				
				que.add(d);
				drawDragon(end, 1, g, que);
			}
		}
		
		System.out.println(countSquare());
		
		
	}
	
	public static void drawDragon(int[] end, int current, int goal, Queue<Integer> que) {
		if (current == goal+1) {
			return;
		} else {
			Stack<Integer> stk = new Stack<Integer>();
			Queue<Integer> orgQueue = new LinkedList<Integer>();
			while(!que.isEmpty()) {
				int pop = que.poll();
				stk.add(pop);
				orgQueue.add(pop);
			}
			
			while(!stk.isEmpty()) {
				int dir = stk.pop();
				dir++;
				dir %= 4;
				
				end[0] += direct[dir][0];
				end[1] += direct[dir][1];
				map[end[0]][end[1]] = 1;
				
				orgQueue.add(dir);
			}

			drawDragon(end, current+1, goal, orgQueue);
		}
	}
	
	public static int countSquare() {
		int result = 0;
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == 1 && map[i][j+1] == 1 && map[i+1][j] == 1 && map[i+1][j+1] == 1) {
					result++;
				}
			}
		}
		
		return result;
	}
}