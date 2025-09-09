//package BOJ.오름세;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		String input = "";
		while((input = br.readLine()) != null) {
			int N = Integer.parseInt(input.trim());
			int[] map = new int[N];
			int[] L = new int[N];
			int max = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				map[i] = Integer.parseInt(st.nextToken());
				int pos = Arrays.binarySearch(L,  0, max, map[i]); 
				if(pos<0) pos=-(pos+1);
				L[pos]=map[i];
				if(max==pos) max++;
			}
			
			sb.append(max).append("\n");
		}
		System.out.println(sb.toString());
	}
}
