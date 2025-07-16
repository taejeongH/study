import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	

	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		
		for (int test = 0; test < testCase; test++) {
			int N = Integer.parseInt(br.readLine());
			long[] dp = new long[N+1];
			dp[1] = 1;
			if (N >= 2) {
				dp[2] = 2;
			}
			
			if (N >= 3) {
				dp[3] = 4;
			}
			
			
			for (int i = 4; i < N+1; i++) {
				dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1000000009;
			}
			
			System.out.println(dp[N]);
		}
		
	}
}