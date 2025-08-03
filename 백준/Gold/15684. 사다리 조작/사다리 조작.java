import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static int H;
	static int[][] map;
	static int[][] ladder;
	static boolean[][] visited;
	static boolean result = false;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //x 크기
		int M = Integer.parseInt(st.nextToken()); //가로 선의 개수
		H = Integer.parseInt(st.nextToken()); //y크기
		map = new int[H][N];
		ladder = new int[H][N-1];
		visited = new boolean[H][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			ladder[a-1][b-1] = 1;
			
		}
		
		if(calLadder()) {
			System.out.println(0);
		}else {
			int result = 4;
			for (int i = 1; i <= 3; i++) {
				if(bt(0, i)) {
					result = i;
					break;
				}
			}

			
			if(result == 4) {
				result = -1;
			}
			
			System.out.println(result);
		}
		
	}
	
	
	public static boolean bt(int num, int max) {
		if (num == max) {
			if(calLadder()) {
				result = true;
			}
		} else {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < N-1; j++) {
					if(!visited[i][j] && ladder[i][j] != 1) {
						if (j != 0 && ladder[i][j-1] != 1) {
							visited[i][j] = true;
							ladder[i][j] = 1;
							bt(num+1, max);
							ladder[i][j] = 0;
							visited[i][j] = false;
						} else if(j == 0) {
							visited[i][j] = true;
							ladder[i][j] = 1;
							bt(num+1, max);
							ladder[i][j] = 0;
							visited[i][j] = false;
						}
					}
					
					if (result) return true;
				}
				
			}
		}
		
		return false;
	}
	
	public static boolean calLadder() {
		boolean isCorrect = true;
		for (int i = 0; i < N; i++) {
			int y = 0;
			int x = i;
			
			while (y < H) {
				if (x < N-1 && ladder[y][x] == 1) {
					x++;
				} else if(x != 0 && x < N && ladder[y][x-1] == 1) {
					x--;
				}

				y++;

			}
			
			if (x != i) {
				isCorrect = false;
				break;
			}
			
		}
		return isCorrect;
		
	}

}