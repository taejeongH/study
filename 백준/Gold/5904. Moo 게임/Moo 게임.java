//package BOJ.Moo게임;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int k = 0;
		long num = 3;
		while (true) {
			k++;
			num = num*2 + k+3;
			if(num>N) break;
		}
		
		while(k!=1) {
			long preNum = (num-k-3)/2;
			if(N > preNum && N <= (num-k-3)/2+k+3) {
				if (N == preNum+1) System.out.println("m");
				else System.out.println("o");
				break;
			} else {
				//이전 단계
				if (N <= preNum) {
					//앞쪽
					
					
				} else {
					//뒤쪽
					N -= preNum+k+3;
				}
			} 
			k--;
			num = preNum;
		}
		
		if (k==1) {
			if (N==1 || N==4 || N==8)
				System.out.println("m");
			else System.out.println("o");
		}
		
	}
}

/*
	S(0) = moo 3
	S(1) = moo(S(0)) + mooo + moo(S(0)) 10
	S(2) = moomooomoo + moooo + moomooomoo (25)
	
	S(k-1)*2+k+2
	
	k를 구하고
	
	
	
	
	N번째에 무슨 글자가 있는지.

*/