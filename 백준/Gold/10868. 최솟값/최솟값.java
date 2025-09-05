//package BOJ.XOR;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] tree;
	
	
	static int init(int[] map, int start, int end, int node) {
		if(start==end) return tree[node]=map[start];
		
		int mid = (start+end)/2;
		return tree[node] = Math.min(init(map,start,mid,node*2), init(map,mid+1,end,node*2+1));
	}
	
	static int query(int start, int end, int left, int right, int node) {
		if (start > right || end < left) return Integer.MAX_VALUE;
		
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start+end)/2;
		return Math.min(query(start, mid, left, right, node*2), query(mid+1, end, left, right, node*2+1));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] map = new int[N];
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		int treeHeight = (int) Math.ceil(Math.log(N)/Math.log(2))+1;
		tree = new int[1<<treeHeight];
		init(map, 0, N-1, 1);
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			sb.append(query(0, N-1, a, b, 1)).append("\n");
		}
		System.out.println(sb.toString());
	}
}
