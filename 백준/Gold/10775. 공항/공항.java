//package BOJ.공항;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		int[] p = new int[P];
		int cnt = 0;
//		int[] g = new int[G+1];
		
		TreeSet<Integer> set = new TreeSet<>();
		for (int i=1; i<=G; i++) {
			set.add(i);
		}
		for (int i=0; i<P; i++) {
			p[i] = Integer.parseInt(br.readLine());
			
			if (set.floor(p[i]) == null) break;
			
			set.remove(set.floor(p[i]));
			
			cnt++;
		}
		System.out.println(cnt);
	}
}


//갈 수 있는 게이트 중 G보다 작거나 같고 가장 큰 Gate
//N