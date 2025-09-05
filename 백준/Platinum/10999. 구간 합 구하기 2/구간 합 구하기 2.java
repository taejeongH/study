import java.io.*;
import java.util.*;

public class Main {
	
	static long[] tree;
	static long[] lazy;
	static int N;
	
	static long init(long[] arr, int start, int end, int node) {
		if (start==end) return tree[node] = arr[start];
		
		int mid = (start+end)/2;
		return tree[node] = init(arr, start, mid, node*2) + init(arr, mid+1, end, node*2+1);
	}
	
	static void lazyUpdate(int start, int end, int node) {
		if(lazy[node]==0) return;
		
		tree[node] += (end-start+1) * lazy[node];
		if(start != end) {
			lazy[node*2] += lazy[node];
			lazy[node*2+1] += lazy[node];
		}
		
		lazy[node] = 0;
	}
	
	static long query(int start, int end, int left, int right, int node) {
		lazyUpdate(start, end, node);
		if (start > right || end < left) return 0;
		
		if (left <= start && end <= right) return tree[node];
		
		int mid = (end+start)/2;
		return query(start, mid, left, right, node*2) + query(mid+1, end, left, right, node*2+1);
	}
	
	static void updateRange(int start, int end, int left, int right, int node, long val) {
		lazyUpdate(start, end, node);
		if(start > right || end < left) return;
		
		if (left<=start && end<=right) {
			tree[node] += (end-start+1) * val;
			if (start!=end) {
				lazy[node*2] += val;
				lazy[node*2+1] += val;
			}
			return;
		}
		
		int mid = (start+end)/2;
		updateRange(start, mid, left, right, node*2, val);
		updateRange(mid+1, end, left, right, node*2+1, val);
		tree[node] = tree[node*2]+tree[node*2+1];
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] map = new long[N];
		int treeHeight = (int) Math.ceil(Math.log(N) / Math.log(2)) +1;
		int treeSize = 1 << treeHeight;
		tree = new long[treeSize];
		lazy = new long[treeSize];
		
		for (int i=0; i<N; i++) {
			map[i] = Long.parseLong(br.readLine());
		}
		
		init(map, 0, N-1, 1);
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (order==1) {
				long d = Long.parseLong(st.nextToken());
				updateRange(0, N-1, b-1, c-1, 1, d);
			} else {
				sb.append(query(0, N-1, b-1, c-1, 1)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

}
