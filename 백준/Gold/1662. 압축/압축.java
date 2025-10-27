//package BOJ.압축;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		
		System.out.println(calc(s, 0, s.length()));
		
		
		
	}
	
	public static int calc(String s, int l, int r) {
		if (l==r) return 0;
		int length = 1;
		
		char prev = s.charAt(l);
		l++;
		while(l<r) {
			if (s.charAt(l)=='(') {
				length--;
				int startCnt = 1;
				int endCnt = 0;
				int endPos = l;
				while (startCnt!=endCnt) {
					endPos++;
					if(s.charAt(endPos)==')') {
						endCnt++;
					} else if(s.charAt(endPos)=='(') {
						startCnt++;
					}
				}
				length += (prev-'0')*calc(s, l+1, endPos);
				l = endPos;
			} else {
				length++;
			}
			
			prev = s.charAt(l);
			l++;
		}
		
		
		return length;
	}
}

/*
	3 + 3( 56 + 2(7+1(9))))
	
	괄호 안의 것을 k번 
	
	(괄호 안의 길이 문자열 길이 * 괄호 밖의 바로 옆 숫자)
	
	1. 숫자와 괄호 구분 -> 괄호 안의 return 값 * 숫자 
*/