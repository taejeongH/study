import java.io.*;
import java.util.*;

public class Main {
	
	static int[] select = new int[9];
	static int N;
	static int[][] map;
	static boolean[] visited;
	static int result;
	
	public static void main(String[] args) throws Exception{
//		File file = new File("src/sample_input (3).txt");
//		BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[9];
		
		map = new int[N][9]; //map[N][i] = i번째 선수가 N번 이닝에서 얻는 결과
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		select[3] = 0;
		
		bt(0);
		System.out.println(result);
	}
	
	public static void bt(int num) {
		if (num == 9) {
			int score = calScore();
			result = Math.max(score, result);
		} else if(num == 3) {
			bt(num+1);
		} else {
			for (int i = 1; i < 9; i++) {
				if(!visited[i]) {
					select[num] = i;
					visited[i] = true;
					bt(num+1);
					visited[i] = false;
					select[num] = 0;
				}
				
			}
		}
	}

	
	public static int calScore() {
		int score = 0; //점수
		int inning = 0; //현재 이닝
		int out = 0;
		
		int batter = 0; //타자
		
		int[] field = new int[3];
		while(inning < N) {		
			int curResult = map[inning][select[batter]];
			
			if(curResult == 0) {
				out++;
			} else if(curResult == 1) {
				score += field[2];
				field[2] = field[1];
				field[1] = field[0];
				field[0] = 1;
			} else if(curResult == 2) {
				score += field[2];
				score += field[1];
				field[2] = field[0];
				field[1] = 1;
				field[0] = 0;
			} else if(curResult == 3) {
				score += field[2];
				score += field[1];
				score += field[0];
				field[2] = 1;
				field[1] = 0;
				field[0] = 0;
			} else if(curResult == 4) {
				score += field[2];
				score += field[1];
				score += field[0];
				score += 1;
				field[0] = 0;
				field[1] = 0;
				field[2] = 0;
			}
			
			batter = (batter+1) % 9;
			
			if(out == 3) {
				inning++;
				out = 0;
				field[0] = 0;
				field[1] = 0;
				field[2] = 0;
			}
		}
		
		
		return score;
	}




}
