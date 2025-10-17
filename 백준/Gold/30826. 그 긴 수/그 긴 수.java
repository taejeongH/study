//package BOJ.그긴수;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long k = Long.parseLong(br.readLine());
		
		int length = 1;
		long lengthSum = 0;
		long curLength = 0;
		//k번째 정수가 몇자리 팰린드롬인지
		while (true) {
			curLength = (long) Math.pow(10, (length-1)/2) * 9;
			if (lengthSum+(curLength*length) >= k) {
				break;
			}
			lengthSum += curLength*length;
			length++;
		}
		
		k -= lengthSum;
		
		//length: 총 자릿수, k%length: 총 자릿수 내 자릿수
		int idx = (int) ((k-1)%length);
		int iter = length/2;
		if (length%2==1) iter++; 
		
		int[] map = new int[length];
		int org = length;
		for (int i=0; i<iter; i++) {
			long test = 0;
			if (i==0) {
				for (int j=1; j<=9; j++) {
					if(test + (curLength/9)*org >= k) {
						map[i] = j;
						map[org-1-i] = j;
						break;
					}
					test += (curLength/9)*length;
				}
			} else {
				for (int j=0; j<=9; j++) {
					if(test + (curLength/10)*org >= k) {
//						System.out.println((curLength/10)*org);
						map[i] = j;
						map[org-1-i] = j;
						break;
					}
					test += (curLength/10)*org;
				}
			}
			k -= test;
			length-=2;
			curLength = (long) Math.pow(10, (length-1)/2) * 10;
//			System.out.println(i+ " " + k + " " + curLength);
		}
		
//		System.out.println(Arrays.toString(map));
//		
//		System.out.println(k);
		System.out.println(map[idx]);
	}

	
}

/*
	팰린드롬 수 중 양의 정수면서 첫 자리가 0이 아닌 팰린드롬 수들을 1부터 차례대로 이어붙여 아주 긴 수를 새로 
	
	그 긴 수 : 첫 자리가 0이 아닌 팰린드롬 수를 1부터 쭉 이어붙인 수
	k가 주어질 때 그 긴 수의 k번째 숫자를 출력
	
	
	펠린드롬수에서 작은 순서대로 만들려면 가운데 부터 키워야 함
	00000
	
	10001
	10101
	11011
	11111
	11211
	가운데 키우고 -> 옆 키우고 -> 가운데 키우고. . .. . . 
*/