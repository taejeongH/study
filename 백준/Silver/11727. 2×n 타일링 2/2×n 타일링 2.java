import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		
		dp[1]= 1;
		if (N >= 2) dp[2] = 3;
		
		if (N >= 3) dp[3] = 5;
		
		if (N >= 4) dp[4] = 11;
		
		for (int i = 5; i <= N; i++) {
			dp[i] = (dp[i-2] * 2 + dp[i-1]) % 10007;
		}
		
		System.out.println(dp[N] % 10007);
		
	}

}