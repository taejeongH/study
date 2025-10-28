//package BOJ.회문;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			String s = br.readLine();
			int N = s.length();
			
			int idx = -1;
			for (int i=0; i<N/2; i++) {
				if (s.charAt(i) != s.charAt(N-1-i)) {
					idx = i;
					break;
				}
			}
			
			if (idx==-1) {
				sb.append(0).append("\n");
			} else {
				//idx 지우고 
				String left = s.substring(0, idx) + s.substring(idx+1, N);
				String right = s.substring(0, N-idx-1) + s.substring(N-idx, N);
				boolean lt = isPalin(left);
				boolean rt = isPalin(right);
				
				if(lt||rt) sb.append(1).append("\n");
				else sb.append(2).append("\n");
			}
		}
		System.out.print(sb);
		
	}
	public static boolean isPalin(String s) {
		int N = s.length();
		for (int i=0; i<N/2; i++) {
			if (s.charAt(i) != s.charAt(N-1-i)) {
				return false;
			}
		}
		return true;
	}
}

/*
	
	문자열 최대 10만 
	
	하나씩 지워본다면 -> 2 * (10만/2) * 30
	
	알파벳이 짝수로 있어야 함 홀 수로 있는 거 지워보기 ?
	
*/