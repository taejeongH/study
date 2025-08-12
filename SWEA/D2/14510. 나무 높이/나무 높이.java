//package SWEA.나무높이14510;

import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./src/SWEA/나무높이14510/Sample_input (7).txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for (int test = 1; test <= testCase; test++) {
			int N = Integer.parseInt(br.readLine().trim());
			
			int[] numbers = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(numbers);
			
			int sum = 0;
			int twoCount = 0;
			int oneCount = 0;
			for (int i = 0; i < N-1; i++) {
				int sub = numbers[N-1] - numbers[i];
				sum += sub;
				if (sub % 2 != 0) {
					oneCount++;
				} 
				twoCount += sub/2;
			}
			
//			System.out.println(oneCount +" " + twoCount);
//			System.out.println(Arrays.toString(numbers));
//			
//			System.out.println(sum);
			if(oneCount <= twoCount) {
				if (sum % 3 == 0) {
					System.out.println("#" + test + " " + (sum / 3 * 2));
				} else if (sum % 3 == 1){
					System.out.println("#" + test + " " + (sum/3 * 2 + 1));
				} else {
					System.out.println("#" + test + " " + (sum/3 * 2 + 2));
				}
			} else {
				System.out.println("#" + test + " " + (twoCount * 2 + (oneCount-twoCount)*2-1));
			}
			
		}
	}
}
