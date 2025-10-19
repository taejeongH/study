//package BOJ.트리의순회;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] in, inPos, post, postPos;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		in = new int[N+1];
		inPos = new int[N+1];
		post = new int[N];
		postPos = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
			inPos[in[i]] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			post[i] = Integer.parseInt(st.nextToken());
			postPos[post[i]] = i;
		}
		sb = new StringBuilder();
		dfs(0, N-1, 0, N-1);
		System.out.println(sb);
	}
	
	public static void dfs(int inStart, int inEnd, int postStart, int postEnd) {
		if(inStart<0 || inEnd>N || postStart<0 || postEnd>N) return;
		if (inStart > inEnd || postStart > postEnd) return;
		sb.append(post[postEnd]).append(" ");
		
		int idx = inPos[post[postEnd]];
		int leftSize = idx - inStart;
		
		//left
		dfs(inStart, idx-1, postStart, postStart+leftSize-1);
		dfs(idx+1, inEnd, postStart+leftSize, postEnd-1);
		
		
	}
}


/*
	인오더, 포스트오더가 주어질 때 해당 트리의 프리 오더 출력
	
	프리오더 -> 내자신이 처음
	인오더 -> 내 자신이 중간
	포스트 오더 -> 내 자신이 끝
	
	
	포스트 오더로 현재 노드를 찾고 -> 인 오더에서 다음 노드를 찾는다?
*/