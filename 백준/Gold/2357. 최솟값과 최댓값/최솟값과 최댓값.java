//package BOJ.최솟값과최댓값;
import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] tree;
	static int N;
	
	static void init(int[] map, int start, int end, int node) {
		if(start==end)  {
			tree[node][0] = map[start];
			tree[node][1] = map[start];
			return;
		}
		
		int mid = (end+start)/2;
		init(map, start, mid, node*2);
		init(map, mid+1, end, node*2+1);
		tree[node][0] = Math.min(tree[node*2][0], tree[node*2+1][0]);
		tree[node][1] = Math.max(tree[node*2][1], tree[node*2+1][1]);
	}
	
	static int[] query(int start, int end, int left, int right, int node) {
		if (end < left || start > right) {
			int[] none = {Integer.MAX_VALUE, 0};
			return none;
		}
		
		if(left <= start && end <= right) return tree[node];
		
		int mid = (end+start)/2;
		
		int[] l = query(start, mid, left, right, node*2);
		int[] r = query(mid+1, end, left, right, node*2+1);
		int[] val = {Math.min(l[0], r[0]), Math.max(l[1], r[1])};
		return val;
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
		tree = new int[1<<treeHeight][2];
		init(map, 0, N-1, 1);
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int[] val = query(0, N-1, l-1, r-1, 1);
			sb.append(val[0]).append(" ").append(val[1]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
