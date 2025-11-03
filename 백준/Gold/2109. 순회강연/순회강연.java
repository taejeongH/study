//package BOJ.순회강연;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0]-o1[0];
			}
		});
		
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			que.add(new int[] {p, d});
		}
		int res = 0;
		boolean[] v = new boolean[10001];
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int d = now[1];
			for (int i=d; i>=1; i--) {
				if (!v[i]) {
					v[i]=true;
					res+=now[0];
					break;
				}
			}
		}
		System.out.println(res);
	}

}


/*
	d일 안에 일을 해주면 p만큼의 강연료를 받는다.
	
	
*/