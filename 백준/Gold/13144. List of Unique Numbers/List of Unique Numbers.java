//package BOJ.ListofUniqueNumbers;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] map = new int[N];
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		long res = 0;
		int l = 0;
		int r = 0;
		
		boolean[] v = new boolean[100001];
		while (r < N && l < N) {
			
			int num = map[r];
			
			if (v[num]) {
				res += r-l;
				v[map[l]] = false;
				l++;
			} else {
				v[num] = true;
				r++;
			}
		}
		
		int num = (r - l);
		
		for (int i=1; i<=num; i++) {
			res += i;
		}
		System.out.println(res);
//		System.out.println(num);
	}
}


/*
	최적화 
	- 각 숫자들이 어디서 다시 나오는지 알수 잇ㄷ마ㅕㄴ..
	
	
*/