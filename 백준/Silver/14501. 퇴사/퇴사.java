import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][2];	//{걸리는 일수, 얻는 금액};
		
		for (int i = 1; i < N+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];
		
		if (map[1][0] + 1 <= N + 1) {
			dp[1] = map[1][1];
		}
		
		
		int result = dp[1];
		for (int i = 2; i < N+1; i++) {
			int max = 0;
			for (int j = i-1; j >= 0; j--) {
				if (j + map[j][0] <= i) {
					max = Math.max(dp[j], max);
				}
			}
			if (i + map[i][0] <= N+1) {
				dp[i] = max + map[i][1];
			} else {
				dp[i] = max;
			}
			result = Math.max(dp[i], result);
		}
		
		
		System.out.println(result);
		
		
		

		
		
		
			
	}
}