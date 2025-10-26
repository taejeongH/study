//package BOJ.zero만들기;

import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder res;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		res = new StringBuilder();
		for (int test=1; test<=testCase; test++) {
			N = Integer.parseInt(br.readLine());
			dfs(2, new StringBuilder().append(1));
			if(test!=testCase)res.append("\n");
		}
		
//		System.out.println((int) '+');
//		System.out.println((int) '-');
//		System.out.println((int) ' ');
		
		System.out.print(res);
	}
	
	public static void dfs(int i, StringBuilder sb) {
		if (i==N+1) {
			if(cal(sb)==0) {
				sb.append("\n");
				res.append(sb);
			}
			return;
		}
		
		StringBuilder newsb = new StringBuilder(sb);
		newsb.append(" ");
		newsb.append(i);
		dfs(i+1, newsb);
		
		
		newsb = new StringBuilder(sb);
		newsb.append("+");
		newsb.append(i);
		dfs(i+1, newsb);
		
		newsb = new StringBuilder(sb);
		newsb.append("-");
		newsb.append(i);
		dfs(i+1, newsb);
		
		
		
		
	}
	
	public static int cal(StringBuilder sb) {
		int idx = 0;
		
		StringBuilder start = new StringBuilder();
		while(idx<sb.length() && sb.charAt(idx)!='+' && sb.charAt(idx)!='-') {
			if (sb.charAt(idx) == ' ') {
				idx++;
				continue;
			}
			start.append(sb.charAt(idx));
			idx++;
		}
		int sum = Integer.parseInt(start.toString());
		
		while(idx < sb.length()) {
			StringBuilder num = new StringBuilder();
			
			boolean op = false;
			if(sb.charAt(idx)=='+') {
				op = true;
			}
			idx++;
			while(idx<sb.length() && sb.charAt(idx)!='+' && sb.charAt(idx)!='-') {
				if(sb.charAt(idx)==' ') {
					idx++;
					continue;
				}
				num.append(sb.charAt(idx));
				idx++;
			}
			
			if (op) {
				sum += Integer.parseInt(num.toString());
			} else {
				sum -= Integer.parseInt(num.toString());
			}
			
		}
		return sum;
	}
}


/*
	1 ~ N 사이에 적절히 +와 -를 넣어서 0이되는 수식을 만들어야 함
	1 2 불가
	1 2 3 가능
	1 2 3 4 가능
	1 2 3 4 5 
	N<=9 백트?

*/