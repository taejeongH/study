//package BOJ.PPAP;

import java.io.*;
import java.util.*;

public class Main {
	static final String p = "PPAP";
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		Stack<Character> stk = new Stack<>();
		
		String res = "PPAP";
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)=='A') {
				if (i+1<s.length() && stk.size()>=2 && s.charAt(i+1)=='P') {
					stk.pop();
					i++;
				} else {
					res = "NP";
					break;
				}
				
			} else {
				stk.add(s.charAt(i));
			}
			
		}
		
		if (stk.size()>1) System.out.println("NP");
		
		else System.out.println(res);
 	}

	
}


/*
	pppapap
	
	재귀
	ppap -> p로 바꿔서 1개가 나올 때 까지 반복하고, p가 남는다면 ppap 출력 아니라면 np추력

*/