//package BOJ.단어수학;

import java.io.*;
import java.util.*;

public class Main {
	static int count, N, res;
	static char[][] map;
	static ArrayList<Character> arr;
	static boolean[] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][8];
		Set<Character> words = new HashSet<>();
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			
			for (int j=0; j<input.length(); j++) {
				map[i][j] = input.charAt(j);
				words.add(map[i][j]);
			}
		}
		v = new boolean[10];
		arr = new ArrayList<>(words);
		count = arr.size();
		res = 0;
		dfs(0, new int[count]);
		System.out.println(res);
	}
	
	public static void dfs(int depth, int[] choice) {
		if(depth==count) {
			res = Math.max(calSum(choice), res);
			return;
		}
		
		for (int i=9; i>=0; i--) {
			if(!v[i]) {
				choice[depth] = i;
				v[i]=true;
				dfs(depth+1, choice);
				v[i]=false;
				
			}
		}
		
	}
	
	public static int calSum(int[] choice) {
		int sum = 0;
		for (int i=0; i<N; i++) {
			StringBuilder sb = new StringBuilder();
			int j = 0;
			while(j<8 && map[i][j]!='\0') {
				for (int k=0; k<count; k++) {
					if (map[i][j]==arr.get(k)) {
						sb.append(choice[k]);
						break;
					}
				}
				j++;
			}
			sum += Integer.parseInt(sb.toString());
		}
		return sum;
	}
	
	
}


/*
	알파벳은 최대 10가지
	
	
	
	
	
*/