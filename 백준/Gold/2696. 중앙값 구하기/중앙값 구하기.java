//package BOJ.중앙값구하기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Integer> maxq = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Integer> minq = new PriorityQueue<>();
			int[] map = new int[N];
			int idx = 0;
			for (int i=0; i<N/10; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<10; j++) {
					map[idx++] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N%10; i++) {
				map[idx++] = Integer.parseInt(st.nextToken());
			}
			sb.append(N/2+1).append("\n");
			int cnt = 0;
			for (int i=0; i<N; i++) {
				int num = map[i];
				if(i%2==0) {
					maxq.add(num);
				} else {
					minq.add(num);
				}
				if(i!=0 && maxq.peek() > minq.peek()) {
					int tmp1 = maxq.poll();
					int tmp2 = minq.poll();
					
					maxq.add(tmp2);
					minq.add(tmp1);
				}
				
//				System.out.println("minq : " + minq.toString());
//				System.out.println("maxq : " + maxq.toString());
//				System.out.println();
				
				if(i%2==0) {
					//출력
					sb.append(maxq.peek()).append(" ");
					if(++cnt%10==0) sb.append("\n");
				}
			}
			sb.append("\n");
			
		}
		
		System.out.print(sb);
	}
}


/*
	홀수번째 수에서의 중앙값 찾기
	
	23 41 13
	
*/