//package BOJ.가르침;

import java.io.*;
import java.util.*;

public class Main {
	static int N, K, res;
	static HashSet<Integer>[] words;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//단어개수
		K = Integer.parseInt(st.nextToken()); //배울 수 있는 알파벳 개수
		
		words = new HashSet[N]; for (int i=0; i<N; i++) words[i] = new HashSet<>();
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<input.length(); j++) {
				char cur = input.charAt(j);
				if (cur=='a' || cur=='t' || cur=='n' || cur=='i' || cur=='c') continue;
				words[i].add(input.charAt(j)-'a');
			}
		}
		if (K<5) {
			System.out.println(0);
		} else {
			int[] choice = new int[26];
			res = 0;
			dfs(0, 0, choice);
			System.out.println(res);
		}
		
	}
	public static void dfs(int depth, int start, int[] choice) {
		if (depth==K-5) {
			int sum = N;
			for (int i=0; i<N; i++) {
				for (int num : words[i]) {
					if (choice[num]==0) {
						sum--;
						break;
					}
 				}
			}
			res = Math.max(sum, res);
			return;
		}
		
		for (int i=start; i<26; i++) {
			if (i==0 || i==19 || i==13 || i==2 || i==8) continue;
			choice[i] = 1;
			dfs(depth+1, i+1, choice);
			choice[i] = 0;
		}
	}
}
