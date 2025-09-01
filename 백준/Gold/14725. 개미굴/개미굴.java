//package BOJ.개미굴;

import java.io.*;
import java.util.*;

public class Main {
	static class Trie{
		class Node {
			Map<String, Node> child;
			boolean endOfWord;
			
			public Node() {
				child = new TreeMap<>();
			}

			@Override
			public String toString() {
				return "Node [child=" + child + ", endOfWord=" + endOfWord + "]";
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
		
		
		void print(Node cur, int idx) {
			for (String s : cur.child.keySet()) {
				for (int i=0; i<idx*2; i++ ) {
					System.out.print("-");
				}
				System.out.print(s);
				System.out.println();
				print(cur.child.get(s), idx+1);
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Trie t = new Trie();
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			String[] s = new String[K];
			for (int j=0; j<K; j++) {
				s[j] = st.nextToken();
			}
			t.add(s);
		}
		
		t.print(t.root, 0);
		
		
	}
}
