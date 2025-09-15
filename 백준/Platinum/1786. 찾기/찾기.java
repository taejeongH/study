//package BOJ.찾기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String parent = br.readLine();
		String pattern = br.readLine();
		
		ArrayList<Integer> count = KMP(parent, pattern);
		System.out.println(count.size());
		for (int i=0; i<count.size(); i++) {
			System.out.print(count.get(i) + " ");
		}
	}

	static int[] makeTable(String pattern) {
		int n = pattern.length();
		int[] table = new int[n];
		
		int idx=0;
		for (int i=1; i<n; i++) {
			while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(idx)) {
				idx += 1;
				table[i] = idx;
			}
		}
		return table;
		
	}
	static ArrayList<Integer> KMP(String parent, String pattern) {
		int[] table = makeTable(pattern);
		ArrayList<Integer> count = new ArrayList<>();
		int n1 = parent.length();
		int n2 = pattern.length();
		
		int idx = 0;
		for (int i=0; i<n1; i++) {
			
			while(idx>0 && parent.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			
			
			if(parent.charAt(i)==pattern.charAt(idx)) {
				if(idx == n2-1) {
					count.add(i-n2+2);
					idx=table[idx];
				} else {
					idx += 1;
				}
			}
		}
		
		return count;
	}
}
