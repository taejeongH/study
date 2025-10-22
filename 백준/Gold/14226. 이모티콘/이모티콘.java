//package BOJ.이모티콘;

import java.io.*;
import java.util.*;

public class Main {
	static int S;
	static int[][] dp;
	static final int INF = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		
		int[] dis = new int[S*2];
		Arrays.fill(dis, INF);
		que.add(new int[] {1, 0, 0});
		dis[1] = 0;
		int res = INF;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int num = now[0];
//			System.out.println(num);
			int time = now[1];
			int clip = now[2];
			if(num<0 || num>=S*2) continue;
			if(num==S) {
//				System.out.println(time);
				res = Math.min(time, res);
			}
			
			if(time > dis[num]+1) continue;
			dis[num] = Math.min(dis[num], time);
			
			que.add(new int[] {num, time+1, num});
			if(clip!=0) que.add(new int[] {num+clip, time+1, clip});
			que.add(new int[] {num-1, time+1, clip});
		}
		System.out.println(res);
	}
}
