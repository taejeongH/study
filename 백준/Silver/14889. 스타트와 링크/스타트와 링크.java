import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static int result = Integer.MAX_VALUE;
	static int[][] map;
	static int[] select;
	static int[] selected;
	static boolean[] visited;
	static Map<String, Integer> check= new HashMap<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		N = Integer.parseInt(br.readLine()); //숫자 개수
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		select = new int[N];
		selected = new int[N/2];
		visited = new boolean[N];
		bt(0);
		
		System.out.println(result);
	}
	
	public static void bt(int num) {
		if (num == N/2) {
			String selectChecking = "";
			for (int i = 0; i < selected.length; i++) {
				selectChecking += selected[i];
			}
			
			if (check.containsKey(selectChecking)) {
				return;
			}
			
			int selectSum = 0;
			int unselectSum = 0;
			
			int[] unselected = new int[N/2];
			int unselectedIdx = 0;
			
			String unselectChecking = "";
			for (int i = 0; i < N; i++) {
				if (select[i] == 0) {
				unselected[unselectedIdx++] = i;
				unselectChecking += i;
				}
			}
			

			check.put(unselectChecking, 1);
			check.put(selectChecking, 1);
			for (int i = 0; i < selected.length-1; i++) {
				for (int j = i+1; j < selected.length; j++) {
					selectSum += map[selected[i]][selected[j]];
					selectSum += map[selected[j]][selected[i]];
				}
			}
			
			for (int i = 0; i < unselected.length-1; i++) {
				for (int j = i+1; j < unselected.length; j++) {
					unselectSum += map[unselected[i]][unselected[j]];
					unselectSum += map[unselected[j]][unselected[i]];
				}
			}
			result = Math.min(Math.abs(unselectSum-selectSum), result);
		} else {
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					if (num == 0) {
						select[i] = 1;
						selected[num] = i;
						visited[i] = true;
						bt(num+1);
						select[i] = 0;
						selected[num] = 0;
						visited[i] = false;
					} else {
						if (selected[num-1] < i) {
							select[i] = 1;
							selected[num] = i;
							visited[i] = true;
							bt(num+1);
							select[i] = 0;
							selected[num] = 0;
							visited[i] = false;
						}
					}
					
				}
			}
				
			
		}
	}
}