//package BOJ.카드정렬하기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		
		PriorityQueue<Integer> que = new PriorityQueue<>();
		for (int i=0; i<N; i++) {
			que.add(Integer.parseInt(br.readLine()));
		}
		
		
		int sum = 0;
		while(que.size()>1) {
			int num0 = que.poll();
			int num1 = que.poll();
			
			
			
			sum += num0 + num1;
			que.add(num0 + num1);
		}
		
		System.out.println(sum);
//		System.out.println(que.toString());
	}
}
