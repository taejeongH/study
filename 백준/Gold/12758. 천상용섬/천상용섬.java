//package BOJ.천상용섬;

import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	static List<Integer>[] divisor;
	static int[][] dp;
	static final int MOD = 1_000_000_007;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			N = Integer.parseInt(br.readLine());
			int[] map = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
			}
			divisor = new List[N]; for (int i=0; i<N; i++) divisor[i] = new ArrayList<>();
			calDivisor(map);
			dp = new int[N][];
			for (int i=0; i<N; i++) {
				dp[i] = new int[divisor[i].size()];
				Arrays.fill(dp[i], -1);
			}
			sb.append(dfs(0, 1)).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int dfs(int depth, int prevHeight) {
		if (depth==N) {
			return 1;
		}
		int startIdx = binarySearch(divisor[depth], prevHeight);
		if(startIdx==divisor[depth].size()) return 0;
		if(dp[depth][startIdx] != -1) return dp[depth][startIdx];
		long res = 0;
		for (int i=startIdx; i<divisor[depth].size(); i++) {
			res = (res + dfs(depth+1, divisor[depth].get(i))) % MOD;
		}
		return dp[depth][startIdx] = (int) res;
	}
	
	public static void calDivisor(int[] map) {
		for (int i=0; i<N; i++) {
			int num = map[i];
			int max = (int) Math.sqrt(num);
			for (int j=1; j<=max; j++) {
				if(map[i]%j==0) {
					divisor[i].add(j);
					if(j!=num/j) {
						divisor[i].add(num/j);
					}
				}
			}
			Collections.sort(divisor[i]);
		}
	}
	
	public static int binarySearch(List<Integer> arr, int val) {
		int left = 0;
        int right = arr.size() - 1;
        int resultIndex = arr.size();

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr.get(mid) >= val) {
                resultIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return resultIndex;
	}
}

/*
	천상용섬은 눈앞의 N개의 물체를 베는 기술, 벨 때마다 베는 높이가 줄어들지 않고 유지되거나 증가
	물체를 베는 높이로 물체의 높이를 나누었을 때 항상 자연수로 나누어 떨어짐
	
	약수 지점에서 벨 수 있고, 이전에 벤 높이보다 높거나 같아야함
	
	dp 배열 너무 낭비
	
*/