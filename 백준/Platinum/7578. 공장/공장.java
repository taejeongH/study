//package BOJ.공장;

import java.io.*;
import java.util.*;

public class Main {
	static int[] tree;
	static void init(int[] arr, int node, int start, int end) {
		if(start==end) {
			tree[node] = arr[start];
		} else {
			int mid = (start+end)/2;
			init(arr, node*2, start, mid);
			init(arr, node*2+1, mid+1, end);
			tree[node] = tree[node*2] + tree[node*2+1]; 
		}
	}
	static int query(int start, int end, int left, int right, int node) {
		if(start > right || end <left) return 0;
		if(left<=start && end<=right) return tree[node];
		int mid = (end+start)/2;
		return query(start, mid, left, right, node*2) + query(mid+1, end, left, right, node*2+1);
	}
	
	static void update(int[]arr, int start, int end, int node, int index, int val) {
		if (index>end || index <start) return;
		if(start == end) {
			tree[node] = val;
			return;
		}
		int mid = (end+start)/2;
		update(arr, start, mid, node*2, index, val);
		update(arr, mid+1, end, node*2+1, index, val);
		tree[node] = tree[node*2]+tree[node*2+1];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] A = new int[N];
		for (int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] idx = new int[1000001];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			idx[Integer.parseInt(st.nextToken())] = i;
		}
		
		int[] arr = new int[N];
		Arrays.fill(arr, 1);
		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		tree = new int[1<<(h+1)];
		init(arr, 1, 0, N-1);
		
//		System.out.println(Arrays.toString(tree));
//		int t = query(0, N-1, 0, 3, 1);
//		System.out.println(t);
//		update(arr, 0, N-1, 1, 2, 0);
//		System.out.println(Arrays.toString(tree));
//		t = query(0, N-1, 0, 3, 1);
//		System.out.println(t);
		
		long res = 0;
		for (int i=0; i<N; i++) {
			int curidx = idx[A[i]];
			
			res += query(0, N-1, 0, curidx-1, 1);
			update(arr, 0, N-1, 1, curidx, 0);
		}
		
		System.out.println(res);
	}
}

/*
	
	교차하는 케이블 쌍의 개수를 구하기
	
	132 392 311 351 231
	 2   0   3   1 
	
	392 351 132 311 231
	
	
	
	2 0 3 1 5 4 6
*/	