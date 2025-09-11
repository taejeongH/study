//package BOJ.가장긴증가하는부분수열2;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] LIS = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = 0;
		for (int i=0; i<N; i++) {
			int input = Integer.parseInt(st.nextToken());
			int pos = Arrays.binarySearch(LIS, 0, len, input);
			if (pos < 0) pos = (pos+1)*-1;
			LIS[pos] = input;
			if(pos >= len) len++;
		}
		System.out.println(len);
	}
}
