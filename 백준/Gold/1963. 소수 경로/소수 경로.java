//package BOJ.소수경로;

import java.io.*;
import java.util.*;

public class Main {
	static int N, to;
	static final int INF = 1_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int tc = Integer.parseInt(br.readLine());
		for (int t=1; t<=tc; t++) {
			st = new StringTokenizer(br.readLine());
			String from = st.nextToken();
			to = Integer.parseInt(st.nextToken());
			N = from.length();
			int[] nums = new int[N+1];
			for (int i=0; i<N; i++) {
				nums[i] = from.charAt(i)-'0';
			}
			nums[N] = 0;
			
			boolean[] v = new boolean[10000];
			ArrayDeque<int[]> que = new ArrayDeque<>();
			que.add(nums);
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int num = toInt(now);
				
				if(v[num]) continue;
				v[num] = true;
				if(num==to) {
					System.out.println(now[N]);
					break;
				}
				if(!isPrime(num)) continue;
				
				
				for (int i=0; i<N; i++) {
					int org = now[i];
					for (int j=0; j<10; j++) {
						if(i==0 && j==0) continue;
						if(org==j) continue;
						int[] nxt = Arrays.copyOf(now, N+1);
						nxt[i] = j;
						nxt[N]++;
						que.add(nxt);
					}
				}
			}
		}
	}

	static int toInt(int[] nums) {
		int num = 0;
		for (int i=0; i<N; i++) {
			num += nums[i] * Math.pow(10, N-1-i);
		}
		return num;
	}
	
	static boolean isPrime(int num) {
		for (int i=2; i<num; i++) {
			if(num%i==0) return false;
		}
		return true;
	}
}
