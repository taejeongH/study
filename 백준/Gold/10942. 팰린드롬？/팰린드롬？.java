//package BOJ.팰린드롬;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			
			int res = 1;
			while (s < e) {
				if (map[s]!=map[e]) {
					res = 0;
					break;
				}
				s++;
				e--;
			}
			sb.append(res).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
