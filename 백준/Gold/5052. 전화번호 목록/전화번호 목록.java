//package BOJ.전화번호목록;

import java.io.*;
import java.util.*;
class Trie{
	class Node {
		Map<Character, Node> child;
		boolean endOfWor;
		
		public Node() {
			child = new HashMap<>();
			endOfWor = false;
		}
		
	}
	
	Node root = new Node();
	
	boolean add(String s) {
		Node cur = root;
		
		for (int i=0; i<s.length(); i++) {
			if(s.charAt(i)==' ') continue;
			
			cur.child.putIfAbsent(s.charAt(i), new Node());
			if(cur.child.get(s.charAt(i)).endOfWor) return true;
			cur = cur.child.get(s.charAt(i));
		}
		
		if(cur.endOfWor) return true;
		cur.endOfWor = true;
		return false;
	}
}
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			Trie t = new Trie();
			int N = Integer.parseInt(br.readLine());
			boolean res = true;
			String[] map = new String[N];
			for (int i=0; i<N; i++) {
				map[i] = br.readLine();
			}
			Arrays.sort(map);
			for (int i=0; i<N; i++) {
				if(t.add(map[i])) {
					res = false;
					break;
				}
			}
			if(res)sb.append("YES").append("\n");
			else sb.append("NO").append("\n");
		}
		System.out.println(sb.toString());
	}
}
