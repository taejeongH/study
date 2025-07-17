import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		int[][] sum = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (j != 0) {
					sum[i][j] = sum[i][j-1] + map[i][j];
				} else {
					sum[i][j] = map[i][j];
				}
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int startY = Integer.parseInt(st.nextToken()) - 1;
			int startX = Integer.parseInt(st.nextToken()) - 1;
			int endY = Integer.parseInt(st.nextToken()) - 1;
			int endX = Integer.parseInt(st.nextToken()) - 1;
			
			int cnt = 0;
			if (startX > 0) {
				for (int y = startY; y <= endY; y++) {
					cnt += sum[y][endX] - sum[y][startX-1];
				}
				
			} else {
				for (int y = startY; y <= endY; y++) {
					cnt += sum[y][endX];
				}
			}
			System.out.println(cnt);
		}		
		
	}
}