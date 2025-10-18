//package BOJ.수묶기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> plus = new ArrayList<>();
		ArrayList<Integer> minus = new ArrayList<>();
		
		int res = 0;
		int zero = 0;
		for (int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num==0) zero++;
			else if(num==1) res++;
			else if(num<0) {
				minus.add(num);
			} else {
				plus.add(num);
			}
		}
		
		Collections.sort(plus, Collections.reverseOrder());
		Collections.sort(minus);
		
		int p = plus.size();
		int m = minus.size();
		
		if (p%2==1) {
			res += plus.get(p-1);
			p--;
		}
		
		for (int i=0; i<p; i+=2) {
			res += plus.get(i) * plus.get(i+1);
		}
		
		if (m%2==1) {
			if (zero==0) res += minus.get(m-1);
			m--;
		}
		
		for (int i=0; i<m; i+=2) {
			res += minus.get(i) * minus.get(i+1);
		}
		
		System.out.println(res);
	}
}

/*
	길이가 N인 수열이 주어졌을 때, 수열의 합을 구하려고 함
	수열의 합을 그냥 구하는 것이 아니고 수를 묶어서 더함(위치 상관없음)
	묶은 수는 곱해지고 나머지는 더해짐
	이 때 얻을 수 있는 최댓값
	
	N<=50
	
	-1 2 1 3
	0 1 2 4 3 5
	
	20 + 6 + 1 +0 
	
	그리디
	음수는 음수끼리 큰순서대로 곱하고 0은 그냥 더해주고,
	음수가 홀수라면 가장 작은 음수는 그냥 더하면 될듯
	
	
*/