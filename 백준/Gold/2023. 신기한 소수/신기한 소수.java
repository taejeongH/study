//package BOJ.신기한소수;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] first = {2, 3, 5, 7};
	static int[] second = {1, 3, 7, 9};
	static StringBuilder res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		res = new StringBuilder();
		dfs(0, new int[N]);
		System.out.println(res.toString());
	}
	public static void dfs(int depth, int[] nums) {
		if (depth==N) {
			if (isPrime(nums)) {
				for (int i=0; i<N; i++) {
					res.append(nums[i]);
				}
				res.append("\n");
			}
			return;
		}
		
		
		if(depth==0) {
			for (int i=0; i<first.length; i++) {
				nums[depth] = first[i];
				dfs(depth+1, nums);
			}
		} else {
			for (int i=0; i<second.length; i++) {
				nums[depth] = second[i];
				dfs(depth+1, nums);
			}
		}
	}
	
	public static boolean isPrime(int[] nums) {
		int num = 0;
		for (int i=0; i<N; i++) {
			num += nums[i];
			for (int j=2; j<num; j++) {
				if (num % j == 0) return false;
			}
			num *= 10;
		}
		
		
		return true;
	}
}


/*
	1의 자리에 올 수 있는 수 1, 2, 3, 5, 7
	
	
	11 13 17 19
	23 29
	31 37 39

*/