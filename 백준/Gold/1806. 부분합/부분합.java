//package BOJ.부분합;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int res = Integer.MAX_VALUE;
		int len = 0;
		int sum = 0;
		for (int i=0; i<N; i++) {
			sum += map[i];
			len++;
			if(sum >= S) {
				res = Math.min(len, res);
				for (int j=i-len+1; j<=i; j++) {
					sum -= map[j];
					if (sum < S) {
						sum += map[j];
						break;
					}
					len--;
					res = Math.min(len, res);
				}
			}
		}
		
		if (res == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(res);
	}
}
