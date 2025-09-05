//package BOJ.구간합구하기;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static long[] map;
	
	static class Segtree{
		private long[] tree;
		
		public Segtree() {
			double treeHeight = Math.ceil(Math.log(N)/Math.log(2))+1;
			long treeSize = Math.round(Math.pow(2, treeHeight));
			tree = new long[Math.toIntExact(treeSize)];
		}
		
		long init(int start, int end, int node) {
			if(start==end) return tree[node]=map[start];
			int mid = (start+end)/2;
			return tree[node]=init(start, mid, node*2) + init(mid+1, end, node*2+1);
		}
		
		long sum(int start, int end, int left, int right, int node) {
			if (start > right || end < left) return 0;
			
			if(left <= start && end <= right) return tree[node];
			int mid = (start+end)/2;
			return sum(start, mid, left, right, node*2) + sum(mid+1, end, left, right, node*2+1);
		}
		
		void update(int start, int end, int idx, long val, int node) {
			if (end < idx || start > idx) return;
			
			tree[node] += val - map[idx];
			int mid = (start+end)/2;
			if (start==end) return;
			
			update(start, mid, idx, val, node*2);
			update(mid+1, end, idx, val, node*2+1);
			
		}
		
		void print() {
			for (int i=1; i<tree.length; i++) {
				System.out.println("[" + i + "] " + tree[i]);
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new long[N];
		for (int i=0; i<N; i++) {
			map[i] = Long.parseLong(br.readLine());
		}
		
		Segtree segtree = new Segtree();
		segtree.init(0, N-1, 1);
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<K+M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			
			if(order == 1) {
				long b = Long.parseLong(st.nextToken());
				segtree.update(0, N-1, a-1, b, 1);
				map[a-1] = b;
			} else {
				int b = Integer.parseInt(st.nextToken());
				sb.append(segtree.sum(0, N-1, a-1, b-1, 1)).append("\n");
			}
		}
		
		System.out.println(sb.toString());
		
		
		
	}
}
