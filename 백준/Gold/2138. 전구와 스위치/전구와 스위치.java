//package BOJ.전구와스위치;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static boolean[] ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		boolean[] org1 = new boolean[N];
		boolean[] org2 = new boolean[N];
		boolean[] ans = new boolean[N];
		
		String input = br.readLine();
		for (int i=0; i<N; i++) {
			if(input.charAt(i)=='0') {
				org1[i] = false;
				org2[i] = false;
			} else {
				org1[i] = true;
				org2[i] = true;
			}
		}
		
		input = br.readLine();
		for (int i=0; i<N; i++) {
			if(input.charAt(i)=='0') ans[i] = false;
			else ans[i] = true;
		}
		
		
		int cnt1 = 0;
		for (int i=1; i<N; i++) {
			if (org1[i-1]!=ans[i-1]) {
				org1[i] = !org1[i];
				org1[i-1] = !org1[i-1];
				if(i+1<N) org1[i+1] = !org1[i+1];
				cnt1++;
			}
		}
		
		boolean can = true;
		for (int i=0; i<N; i++) {
			if (org1[i]!=ans[i]) can=false;
		}
		
		if (can) {
			System.out.println(cnt1);
		} else {
			org2[0] = !org2[0];
			org2[1] = !org2[1];
			int cnt2=1;
			
			for (int i=1; i<N; i++) {
				if (org2[i-1]!=ans[i-1]) {
					org2[i] = !org2[i];
					org2[i-1] = !org2[i-1];
					if(i+1<N) org2[i+1] = !org2[i+1];
					cnt2++;
				}
			}
			
			can = true;
			for (int i=0; i<N; i++) {
				if (org2[i]!=ans[i]) can=false;
			}
			
			if(can) System.out.println(cnt2);
			else System.out.println(-1);
		}
	}
}

/*
	N개의 전구와 스위치, i번째 스위치를 누르면 i-1, i, i+1의 전구의 상태가 바뀜
	N과 현재 전구의 상태, 만들고자 하는 전구의 상태가 주어질때
	스위치를 최소 몇 번 누르면 되는지 알아내기
	만약 불가능하다면 -1 출력
	
	
	0000 -> 0101
	1100
	1011
	0101
	
	0000 -> 1111
	1100
	1111
	
	0000 -> 0110
	1100
	1111
	1000
	0110
	
	
	
	
	홀/짝 홀이면 현재와 반대, 짝수면 현재
	
	
	
	완탐 -> 중복 순열이면 풀 수 있나..
	
	
	
	
	
	
	110
	
	
*/