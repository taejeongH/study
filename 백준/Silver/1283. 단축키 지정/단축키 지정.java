//package BOJ.단축키지정;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		HashSet<Character> hotkeys = new HashSet<>();
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			String[] inputs = input.split(" ");
			
			int idx = 0;
			boolean found = false;
			for (String word : inputs) {
				char lower = Character.toLowerCase(word.charAt(0));
				char upper = Character.toUpperCase(word.charAt(0));
				if (!hotkeys.contains(lower)) {
					hotkeys.add(lower);
					hotkeys.add(upper);
					found = true;
					break;
				}
				idx++;
			}
			
			if(found) {
				for (int j=0; j<idx; j++) {
					sb.append(inputs[j]).append(" ");
				}
				sb.append("[").append(inputs[idx].charAt(0)).append("]");
				for (int j=1; j<inputs[idx].length(); j++) {
					sb.append(inputs[idx].charAt(j));
				}
				
				if(idx!=inputs.length-1) sb.append(" ");
				for (int j=idx+1; j<inputs.length; j++) {
					sb.append(inputs[j]).append(" ");
				}
				sb.append("\n");
			} else {
				idx = 0;
				int idx2 = -1;
				found = false;
				nxt : for (String word : inputs) {
					for (int j=0; j<word.length(); j++) {
						char lower = Character.toLowerCase(word.charAt(j));
						char upper = Character.toUpperCase(word.charAt(j));
						if (!hotkeys.contains(lower)) {
							hotkeys.add(lower);
							hotkeys.add(upper);
							found = true;
							idx2 = j;
							break nxt;
						}
					}
					idx++;
				}
				
				if(idx2!=-1) {
					for (int j=0; j<idx; j++) {
						sb.append(inputs[j]).append(" ");
					}
					
					for (int j=0; j<idx2; j++) {
						sb.append(inputs[idx].charAt(j));
					}
					
					sb.append("[").append(inputs[idx].charAt(idx2)).append("]");
					
					for (int j=idx2+1; j<inputs[idx].length(); j++) {
						sb.append(inputs[idx].charAt(j));
					}
					if(idx!=inputs.length-1) sb.append(" ");
					for (int j=idx+1; j<inputs.length; j++) {
						sb.append(inputs[j]).append(" ");
					}
					sb.append("\n");
				} else {
					sb.append(input).append("\n");
				}
				
			}
				
				
			
		}
		System.out.print(sb);
	}
}

/*
	왼쪽에서부터 단어의 첫 글자가 단축키로 지정되어 있지 않다면 단축키로 지정
	만약 모든 단어의 첫 글자가 이미 지정되어 있다면 왼쪽에서부터 차례대로 알파벳을 보면서 단축키로 지정
	
	
	위에서부터 차례대로 탐색
	만약 단어의 첫 알파벳 (공백구분)이 지정되어 있지 않다면 지정
	다 지정되어 있다면 왼쪽부터 차례로 보면서 지정
	
*/