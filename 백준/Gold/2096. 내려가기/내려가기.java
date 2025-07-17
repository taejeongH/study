import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][6]; //{max1, max2, max3, min1, min2, min3}
		
		int max = 0;
		int min = 1000000;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (i == 0) {
					map[i][j] = num;
					map[i][j+3] = num;
				} else {
					if (j == 0) {
						map[i][j] = Math.max(map[i-1][j], map[i-1][j+1]) + num;
						map[i][j+3] = Math.min(map[i-1][j+3], map[i-1][j+4]) + num;
					} else if(j == 1) {
						map[i][j] = Math.max(map[i-1][j], map[i-1][j+1]);
						map[i][j] = Math.max(map[i-1][j-1], map[i][j]) + num;
						
						map[i][j+3] = Math.min(map[i-1][j+3], map[i-1][j+4]);
						map[i][j+3] = Math.min(map[i-1][j+2], map[i][j+3]) + num;
					} else {
						map[i][j] = Math.max(map[i-1][j], map[i-1][j-1]) + num;
						map[i][j+3] = Math.min(map[i-1][j+3], map[i-1][j+2]) + num;
						
					}
					
				}
			}
		}
		
		max = Math.max(map[N-1][0], map[N-1][1]);
		max = Math.max(max, map[N-1][2]);
		
		min = Math.min(map[N-1][3], map[N-1][4]);
		min = Math.min(map[N-1][5], min);
		
		System.out.println(max + " " + min);
		
	}
}