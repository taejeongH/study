import java.io.*;
import java.util.*;

public class Main {
	
	static int[] tree;
	static int[] lazy;
	static int N;
	
	
	static void updateLazy(int start, int end, int node) {
		if (lazy[node]==0) return;
		
		if (lazy[node]%2==1) {
			tree[node] = (end-start+1) - tree[node];
		}
		
		if (start != end) {
			lazy[node*2] += lazy[node];
			lazy[node*2+1] += lazy[node];
		}
		
		lazy[node] = 0;
	}
	
	static void reverse(int start, int end, int left, int right, int node) {
		updateLazy(start, end, node);
		if (start > right || end < left) return;
		if(left <= start && end <= right) {
			tree[node] = (end-start+1) - tree[node];
			if(start != end) {
				lazy[node*2] += 1;
				lazy[node*2+1] += 1;
			}
			return;
		}
		int mid = (end+start)/2;
		reverse(start, mid, left, right, node*2);
		reverse(mid+1, end, left, right, node*2+1);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	static int query(int start, int end, int left, int right, int node) {
		updateLazy(start, end, node);
		if(start > right || end < left) return 0;
		
		if(left <= start && end <= right) return tree[node];
		
		int mid = (end+start)/2;
		return query(start, mid, left, right, node*2) + query(mid+1, end, left, right, node*2+1);
	}
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int treeHeight = (int) Math.ceil(Math.log(N) / Math.log(2))+1;
		int treeSize = 1 << treeHeight;
		tree = new int[treeSize];
		lazy = new int[treeSize];
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken())-1;
			int t = Integer.parseInt(st.nextToken())-1;
			
			if(o == 0) {
				reverse(0, N-1, s, t, 1);
			} else {
				sb.append(query(0, N-1, s, t, 1)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}