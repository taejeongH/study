import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] paperCount;
	static int result;
	static List<int[]> onePos;
	
	public static void main(String[] args) throws Exception{
//		File file = new File("src/sample_input (3).txt");
//		BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = 10;
		map = new int[N][N];
		visited = new boolean[N][N];
		paperCount = new int[6]; for (int i = 1; i <= 5; i++) paperCount[i] = 5;
		
		
		onePos = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) onePos.add(new int[] {i, j});
			}
		}

		result = Integer.MAX_VALUE;
		bt(0);
		if (result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
		
	}
	public static void bt(int num) {
		if (num == onePos.size()) {
			
			//종료 기준은 모든 1을 다 붙였거나 .. 더 이상 붙일 종이가 없는 경우?
//			System.out.println(Arrays.toString(paperCount));
			int use = 0;
			for (int i = 1; i <= 5; i++) {
				use += 5 - paperCount[i];
			}
			result = Math.min(use, result);
		} else {
			int y = onePos.get(num)[0];
			int x = onePos.get(num)[1];
			if (!visited[y][x]) {
				for (int k = 5; k >= 1; k--) {
					if (paperCount[k] > 0) {
						if (check(y, x, k)) {
							checkTrue(y, x, k);
							paperCount[k]--;									
							bt(num+1);
							checkFalse(y, x, k);
							paperCount[k]++;
						}
					}
				}
			} else {
				bt(num+1); //이미 붙여진 곳이라면 순서를 넘어간다.
			}
		}
	}
	
	public static boolean check(int startY, int startX, int count) {
		int endY = startY + count;
		int endX = startX + count;
		
		for (int i = startY; i < endY; i++) {
			for (int j = startX; j < endX; j++) {
				if (i < 0 || i > N-1 || j < 0 || j > N-1 || map[i][j] == 0 || visited[i][j]) {
					return false;
				}
			}
		}

		return true;
	}
	
	public static void checkTrue(int startY, int startX, int count) {
		int endY = startY + count;
		int endX = startX + count;
		for (int i = startY; i < endY; i++) {
			for (int j = startX; j < endX; j++) {
				visited[i][j] = true;
			}
		}
	}

	public static void checkFalse(int startY, int startX, int count) {
		int endY = startY + count;
		int endX = startX + count;
		for (int i = startY; i < endY; i++) {
			for (int j = startX; j < endX; j++) {
				visited[i][j] = false;
			}
		}
	}

}
