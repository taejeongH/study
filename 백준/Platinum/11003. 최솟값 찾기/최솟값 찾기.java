//package BOJ.최솟값찾기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		ArrayDeque<Integer> deq = new ArrayDeque<>();
		
		int[] map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			
			while(!deq.isEmpty() && map[deq.peekLast()]>map[i]) {
				deq.pollLast();
			}
			
			if(!deq.isEmpty() && deq.peekFirst() < i-L+1) {
				deq.pollFirst();
			}
			
			deq.addLast(i);
			sb.append(map[deq.peekFirst()]).append(" ");
		}
		System.out.println(sb);
	}
}

/*
	구간 최솟값을 구하는 문제
	1 5 2 3
	(1 5 2) -> 1
	  (5 2 3) -> 
	 
	 1 5 2 3 6
	 1 5 2
	
	1. 세그트리
	2. 트리셋? 최솟값 logN, 제거 logN, 넣기 logN 3logN * N ->  중복때문에 못쓸듯.. 
	
*/