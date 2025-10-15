//package BOJ.만칼라;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			int[] map = new int[N+1];
			map[0] = N;
			
			int max = 0;
			while(map[0]!=0) {
				int nxt = 1;
				for (int i=0; i<N; i++) {
					if (map[i]==0) {
						nxt = i;
						break;
					}
				}
				
				for (int i=0; i<nxt; i++) {
					map[i]--;
				}
				map[nxt] = nxt;
				max = Math.max(max,  nxt);
			}
			
			sb.append(test).append(" ").append(max).append("\n");
			
			for (int i=1; i<=max; i++) {
				sb.append(map[i]).append(" ");
				if(i%10==0) sb.append("\n");
			}
			if(max%10!=0) sb.append("\n");
		}
		System.out.println(sb.toString());
		
		
	}
}

/*
	여러 개의 빈칸과 하나의 럼바로 이루어진 보드 위에서 게임 진행
	가장 왼쪽의 빈칸은 럼바, 처음엔 항상 비어있음
	
	게임 진행
	1.
	각 칸을 B[N]이라 할 때, B[N]에 N개의 구슬이 들어있는 칸을 찾음
	그런 칸이 여러 개라면 아무거나 선택해도 상관 X
	만일 B[N]=N인 칸이 없다면 패배
	
	2.
	고른 칸의 구슬을 럼바와 B[1], B[2], ..., B[N-1]에 하나씩 고르게 나누어줌
	
	3.
	위의 시행을 계속 반복하여 구슬이 럼바로 들어간다면 성공적으로 마치고, 승리하는 게임판
	
	모든 정수 N에 대해, 구슬이 총 N개 일때의 승리하는 게임판은 항상 유일
	총 구슬의 개수 N이 주어지면 승리하는 게임판을 출력
	
	
	럼바에 N개의 구슬을 두고 처음 상태로 돌아가자
	
*/