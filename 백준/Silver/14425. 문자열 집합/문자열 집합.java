//package BOJ.문자열집합;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> map = new HashMap<>();
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			map.put(s, 1);
		}
		
		int result = 0;
		for (int i=0; i<M; i++) {
			String s = br.readLine();
			if(map.containsKey(s)) result++;
		}
		
		System.out.println(result);
	}
}
