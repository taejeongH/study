//package main;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {	
	public static int R;
	public static int C;
	public static boolean[] alphabetCheck;
	public static int[][] map;
	public static boolean[][] visited;
	public static int max;
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		alphabetCheck = new boolean[26];
		visited  = new boolean[R][C];
		
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j) - 65;
			}
		}		
		max = 0;
		visited[0][0] = true;
		alphabetCheck[map[0][0]] = true;
		bt(new int[] {0, 0, 0});
		System.out.println(max+1);
	}
	
	public static void bt(int[] now) {
		int[] dy = {-1, 1, 0, 0}; //up, down, left, right
		int[] dx = {0, 0, -1, 1};
		int y = now[0];
		int x = now[1];
		int cnt = now[2];
		
		for (int i = 0; i < 4; i++) {
			int nextY = y+dy[i];
			int nextX = x+dx[i];
			if (nextY < R && nextY >= 0 && nextX < C && nextX >= 0) {
				if (!alphabetCheck[map[nextY][nextX]] && !visited[nextY][nextX]) {
					alphabetCheck[map[nextY][nextX]] = true;
					visited[nextY][nextX] = true;
					bt((new int[] {nextY, nextX, cnt+1}));
					alphabetCheck[map[nextY][nextX]] = false;
					visited[nextY][nextX] = false;
				}
			}
		}
		max = Math.max(max, cnt);
		
	}
}