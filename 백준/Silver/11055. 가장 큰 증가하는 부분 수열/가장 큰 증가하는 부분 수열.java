import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {	

	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N];
		int[] dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		dp[0] = map[0];
		for (int i = 0; i < N; i++) {
			int max = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (map[j] < map[i]) {
					max = Math.max(dp[j], max);
					dp[i] = dp[j] + map[i];
				}
			}
			
			if (max != 0) {
				dp[i] = max + map[i];
			} else {
				dp[i] = map[i];
			}
			
			result = Math.max(dp[i], result);
		}
		
		System.out.println(result);
	}
}