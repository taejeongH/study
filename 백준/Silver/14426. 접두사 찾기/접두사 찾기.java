//package BOJ.접두사찾기;

import java.io.*;
import java.util.*;

class Trie {
	class Node {
		Map<Character, Node> child = new HashMap<>();
		boolean endOfWor;
		
		public Node() {
		}
	}
	
	Node root = new Node();
	
	void add(String s) {
		Node cur = root;
		
		for(int i=0; i<s.length(); i++) {
			cur.child.putIfAbsent(s.charAt(i), new Node());
			cur = cur.child.get(s.charAt(i));
		}
		cur.endOfWor=true;
	}
	
	boolean search(String s) {
		Node cur = root;
		
		for (int i=0; i<s.length(); i++) {
			if(!cur.child.containsKey(s.charAt(i))) return false;
			cur = cur.child.get(s.charAt(i));
		}
		
		return true;
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Trie t = new Trie();
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			t.add(s);
		}
		
		int result = 0;
		for (int i=0; i<M; i++) {
			String s = br.readLine();
			if(t.search(s)) result++;
		}
		
		System.out.println(result);
	}
}
