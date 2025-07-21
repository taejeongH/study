import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for (int test = 0; test < testCase; test++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[2][N];
			int[][] dp = new int[2][N];
			
			for (int i = 0 ; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[0][0] = map[0][0];
			dp[1][0] = map[1][0];
			
			int upMax = dp[0][0];
			int downMax = dp[1][0];
			for (int i = 1; i < N; i++) {
				dp[0][i] = downMax + map[0][i];
				dp[1][i] = upMax + map[1][i];
				downMax = Math.max(dp[1][i], downMax);
				upMax = Math.max(dp[0][i], upMax);
			}
			
			System.out.println(Math.max(downMax, upMax));
		}
	}
	
}
