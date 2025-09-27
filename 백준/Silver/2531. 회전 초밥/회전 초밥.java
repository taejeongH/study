//package BOJ.회전초밥;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //초밥의 개수
		int D = Integer.parseInt(st.nextToken()); //초밥의 가지수
		int K = Integer.parseInt(st.nextToken()); //연속해서 먹는 접시의 수
		int C = Integer.parseInt(st.nextToken()); //쿠폰 번호
		
		int[] map = new int[N];
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		
		int res = 0;
		boolean[] visited = new boolean[D+1];
		for (int i=0; i<N; i++) {
			Arrays.fill(visited, false);
			visited[map[i]]=true;
			int conti=1;
			for (int j=i+1; j<i+K; j++) {
				if(!visited[map[j%N]]) {
					conti++;
				}
				visited[map[j%N]]=true;
			}
			
			if(!visited[C]) conti++;
			res = Math.max(res, conti);
		}
		System.out.println(res);
		
	}
	
}

/* 손님은 자기가 좋아하는 초밥을 골라먹음
 * 회전 초밥 위에는 동일한 번호의 초밥이 있을 수 잇음
 * 
 * 1. 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공
 * 2. 1번 행사에 참여할 경우, 초밥 종류 하나가 쓰인 쿠폰을 통해 초밥 하나를 추가로 무료 제공 (없을경우 만들어서 제공)
 * 
 * 위 행사에 참여하여 가능한 다양한 종류의 초밥을 먹으려고 함(겹치지 않도록?)
 * 즉, 연속해서 k개를 먹으면 c를 추가로 먹을 수 있음
 * 
 * 완탐 : dfs로 구하는데, 만약 c가 포함되지 않았다면 +1? 
 * dfs의 상태는? -> 이전에 선택한 것들
 * 
 * 
 * 초밥의 개수N<=30000
 * 초밥의 가짓수d<=3000
 * 연속해서 먹는 접시의 수k <=3000
 * 쿠폰 번호 c
 */ 
