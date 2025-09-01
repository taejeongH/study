//package BOJ.디스크트리;

import java.io.*;
import java.util.*;

class Trie {
	class Node{
		TreeMap<String, Node> child;
		boolean endOfWord;
		public Node() {
			child = new TreeMap<>();
		}
	}
	
	Node root = new Node();
	
	void add(String[] s) {
		Node cur = root;
		for (int i=0; i<s.length; i++) {
			cur.child.putIfAbsent(s[i], new Node());
			cur = cur.child.get(s[i]);
		}
		
		cur.endOfWord = true;
	}
	
	String print(Node cur, int cnt) {
		StringBuilder sb = new StringBuilder();
		for (String s : cur.child.keySet()) {
			for (int i=0; i<cnt; i++) sb.append(" ");
			sb.append(s).append("\n");
			sb.append(print(cur.child.get(s), cnt+1));
		}
		
		return sb.toString();
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		Trie t = new Trie();
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			t.add(input.split("\\\\"));
		}
		System.out.println(t.print(t.root, 0));
	}
}
