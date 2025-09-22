//package BOJ.극장좌석;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] map, dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine()); //VIP좌석 수
		
		map = new int[N+1];
		for (int i=1; i<N+1; i++) map[i]=i;
		for (int i=0; i<M; i++) {
			int vip = Integer.parseInt(br.readLine());
			map[vip] = -1;
		}
		dp = new int[N+1];
		Arrays.fill(dp, -1);
		System.out.println(dfs(1));
	}
	
	static int dfs(int idx) {
		if(idx==N+1) return 1;
		
		if(dp[idx]!=-1)return dp[idx];
		int res = 0;
		res += dfs(idx+1);
		if(idx<N && map[idx]!=-1 && map[idx+1]!=-1) {
			res += dfs(idx+2);
		}
		return dp[idx]=res;
	}
	
	
}

/* 좌석은 1~N까지 번호가 매겨져 있음
 * 입장권에 표시되어 있는 좌석에 앉아야 함 (자기의 바로 왼쪽 혹은 오른쪽으로는 옮길 수 있음)
 * VIP는 반드시 자기 좌석에만 앉아야함
 * 사람들이 좌석에 앉는 서로 다른 방법의 가짓수를 구하시오
 * 
 */
