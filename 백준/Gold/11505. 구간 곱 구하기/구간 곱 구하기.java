//package BOJ.구간곱구하기;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] tree;
	
	static int init(int[] map, int start, int end, int node) {
		if (start==end) return tree[node]=map[start];
		
		int mid = (start+end)/2;
		return tree[node] = (int)((long)init(map, start, mid, node*2) * init(map, mid+1, end, node*2+1)%1000000007);
	}
	
	static void update(int[]map, int start, int end, int node, int idx, int val) {
		if (start > idx || idx > end) return;
		
		if (start==end) {
			tree[node] = val;
			return;
		}
		
		int mid = (end+start)/2;
		update(map, start, mid, node*2, idx, val);
		update(map, mid+1, end, node*2+1, idx, val);
		
		tree[node] = (int)((long)tree[node*2] * tree[node*2+1]%1000000007);
	}
	
	static int mult(int start, int end, int left, int right, int node) {
		if(end < left || start > right) return 1;
		
		if (left <= start && end <= right) return tree[node];
		
		int mid = (end+start)/2;
		return (int)((long)mult(start, mid, left, right, node*2) * mult(mid+1, end, left, right, node*2+1)%1000000007);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] map = new int[N];
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		int treeHeight = (int)Math.ceil(Math.log(N)/Math.log(2))+1;
		int treeSize = 1<<treeHeight;
		tree = new int[treeSize];
		init(map, 0, N-1, 1);
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a==1) {
				update(map, 0, N-1, 1, b-1, c);
				map[b-1]=c;
			} else {
				sb.append(mult(0, N-1, b-1, c-1, 1)).append("\n");
			}
		}
		System.out.println(sb.toString());
		
	}
}
