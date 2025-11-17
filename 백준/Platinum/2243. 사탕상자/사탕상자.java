//package BOJ.사탕상자;

import java.io.*;
import java.util.*;

public class Main {
	static int[] tree;
	static int query(int start, int end, int node, int value) {
		if(start==end) return start;
		int left = tree[node*2];
		
		int mid = (start+end)/2;
		if(left < value) {
			//오른쪽 이동
			return query(mid+1, end, node*2+1, value-left);
		} else {
			//left 로 이동
			return query(start, mid, node*2, value);
		}
		
	}
	
	static void update(int start, int end, int node, int index, int value) {
		if(index>end || index<start) return;
		if(start==end) {
			tree[node]=value;
			return;
		}
		int mid = (start+end)/2;
		update(start, mid, node*2, index, value);
		update(mid+1, end, node*2+1, index, value);
		tree[node] = tree[node*2]+tree[node*2+1];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int M = 1000001;
		int height = (int) Math.ceil(Math.log(M) / Math.log(2));
		int[] candies = new int[M];
		tree = new int[1 << (height+1)];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			if(A==1) {
				//사탕을 꺼내는 경우
				int B = Integer.parseInt(st.nextToken());
				
				//누적합이 B보다 작으면서 가장 큰 값을 찾아야 한다.
				//만약 왼쪽 자식이 내가 ㅊ자아야 하는 값보다 작다면? 찾아야 하는 값에서 왼쪽 자식 값을 빼고 오른쪽으로 이동
				//크다면 왼쪽자식으로 이동 이걸 반복해서 리프노드로 갈 때 까지 찾기 
				int idx = query(0, M, 1, B);
				candies[idx]--;
				update(0, M, 1, idx, candies[idx]);
				sb.append(idx).append("\n");
			} else {
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				candies[B]+=C;
				update(0, M, 1, B, candies[B]);
			}
//			System.out.println(Arrays.toString(candies));
			//for(int j=1; j<=10; j++) System.out.print(tree[j] + " ");
			//System.out.println();
		}
		System.out.print(sb);
	}
}

/*
	1은 가장 맛있는 사탕, 1,000,000은 가장 맛없는 사탕을 의미
	
	
	사탕을 꺼내는 경우
	 - A : 1
	 - B : 꺼낼 사탕의 순위
	 
	사탕을 넣는 경우
	 - A : 2
	 - B : 넣을 사탕의 맛을 나타내는 정수
	 - C : 그러한 사탕의 개수 (C가 양수일 경우 넣는 경우, 음수일 경우 빼는 경우)
	 
	 맨 처음은 빈 상자에서 시작
	 
	 사탕의 총 개수는 20억개를 넘어가지 않음
	
*/