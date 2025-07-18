import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		
		
		dp[0] = 1;
		int result = 1;
		for (int i = 1; i < N; i++) {
			int max = 0;
			for (int j = i-1; j >=0; j--) {
				if (map[i] > map[j]) {
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max+1;
			result = Math.max(dp[i], result);
		}
		System.out.println(result);
	}
}