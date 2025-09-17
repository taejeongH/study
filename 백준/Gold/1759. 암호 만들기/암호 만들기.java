//package BOJ.암호만들기;

import java.io.*;
import java.util.*;

public class Main {
	
	static int L,C;
	static char[] chars;
	static boolean[] v;
	 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		
		chars = new char[C];
		v = new boolean[C];		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<C; i++) {
			char input = st.nextToken().charAt(0);
			chars[i] = input;
		}
		
		
		Arrays.sort(chars);
		bt(0, 0, 0, 0, new StringBuilder());
	}
	
	public static String bt(int depth, int start, int moCnt, int jaCnt, StringBuilder word) {
		if(moCnt>=L-1) return null;
		if (depth==L) {
			if (moCnt>=1 && jaCnt>=2) {
				System.out.println(word);
			}
			return null;
		}
		
		for (int i=start; i<C; i++) {
			char c = chars[i];
			
			if (c=='a' || c=='e' || c=='i' || c=='o' || c=='u') {
				bt(depth+1, i+1, moCnt+1, jaCnt, word.append(c));
			} else {
				bt(depth+1, i+1, moCnt, jaCnt+1, word.append(c));
			}
			
			word.deleteCharAt(depth);
		}
		
		return null;
	}
}
