//package BOJ.오름세;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		while(sc.hasNext()) {
			int N = sc.nextInt();
			int[] map = new int[N];
			int[] L = new int[N];
			int max = 0;
			for (int i=0; i<N; i++) {
				map[i] = sc.nextInt();
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
