//package BOJ.공항;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		int[] p = new int[P];
		int cnt = 0;
		parents = new int[G+1];
		for (int i=1; i<G+1; i++) {
			parents[i] = i;
		}
		for (int i=0; i<P; i++) {
			p[i] = Integer.parseInt(br.readLine());
			
			int root = find(p[i]);
			
			if(root == 0) break;
			union(root, root-1);
			cnt++;
		}
		System.out.println(cnt);
	}
	
	static int find(int x) {
		if (parents[x] == x) return x;
		
		int xRoot = find(parents[x]);
		parents[x] = xRoot;
		
		return xRoot;
	}
	
	static void union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		parents[xRoot] = y;
	}
}


//갈 수 있는 게이트 중 G보다 작거나 같고 가장 큰 Gate
//N