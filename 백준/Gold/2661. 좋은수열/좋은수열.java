//package BOJ.좋은수열;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static StringBuilder res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dfs(1, new StringBuilder().append(1));
//		System.out.println(isGood("121313"));
		System.out.println(res);
	}
	public static boolean dfs(int depth, StringBuilder sb) {
		if(!isGood(sb.toString())) return false;
		if(depth == N) {
			res = sb;
			return true;
		}
		if (sb.charAt(sb.length()-1)!='1') {
			sb.append(1);
			if (dfs(depth+1, sb)) return true;
			sb.deleteCharAt(sb.length()-1);
		}
		
		if (sb.charAt(sb.length()-1)!='2') {
			sb.append(2);
			if(dfs(depth+1, sb)) return true;
			sb.deleteCharAt(sb.length()-1);
		}
		
		if (sb.charAt(sb.length()-1)!='3') {
			sb.append(3);
			if (dfs(depth+1, sb)) return true;
			sb.deleteCharAt(sb.length()-1);
		}
		
		return false;
	}
	
	
	public static boolean isGood(String s) {
		if(s.length()==1) return true;
		int l = s.length();
		
		for (int i=1; i<=l/2; i++) {
			if(s.substring(l-i, l).equals(s.substring(l-i-i, l-i))) return false;
			
		}
		
		return true;
	}
}


/*
	숫자 1,2,3으로만 이루어지는 수열
	임의의 길이에 인접한 두 개의 부분 수열이 동일한 것이 있으면 나쁜수열
	
	
	길이가 N인 좋은 수열들 중 가장 작은 수
	
	완탐
	 - 1, 2, 3 중 하나를 선택하고
	 - N==depth라면, 좋은 수열인지 확인 -> 좋은 수열이라면 최솟값을 기록
	 
	 
	 시간복잡도 : 3^80
	 
	 최적화1
	  - 1, 2, 3 중 이전에 선택하지 않은 숫자 중 1가지를 고르기
	  - 2^80
	  
	  최적화2
	  - 좋은 수열인지 나쁜 수열인지 확인하는 게 얼마나 걸릴지? 
	  - 분할정복으로 확인하기?
	  - 현재 depth가 홀수라면? 앞을 버리거나 뒤를 버리거나 확인? 12123
	  
	
*/