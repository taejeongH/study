//package BOJ.마법사상어와블리자드;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {-1, 0, 1, 0};	//좌하우상
	static int[] dy = {0, 1, 0, -1};
	static int[] filter = {0, 3, 1, 0, 2}; //1->3, 2->1, 3->0, 4->2
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int midY = N/2;
		int midX = N/2;
		
		int oneCount = 0;
		int twoCount = 0;
		int threeCount = 0;
		for (int order=0; order<M; order++) {
			st = new StringTokenizer(br.readLine());
			int d = filter[Integer.parseInt(st.nextToken())];
			int s = Integer.parseInt(st.nextToken());
			
			//1. d 방향으로 s거리만큼 구슬을 모두 파괴 (벽 무시) 
			for (int i=1; i<=s; i++) {
				int ny = midY + (dy[d] * i);
				int nx = midX + (dx[d] * i);
				map[ny][nx] = 0;
			}
			
			//2.달팽이 돌면서 que에 넣기
			ArrayDeque<Integer> que = new ArrayDeque<>();
			int cnt = 0;
			int step = 1;
			int dir = 0;
			int ny = midY;
			int nx = midX;
			nxt: while(true) {
				for (int i=0; i<step; i++) {
					ny += dy[dir];
					nx += dx[dir];
					
					if (map[ny][nx]!=0) {
						que.add(map[ny][nx]);
					}
					map[ny][nx] = 0;
					if (ny==0 && nx==0) break nxt;
				}
				if (++cnt == 2) {
					step++;
					cnt = 0;
				}
				dir = (dir+1)%4;
			}
			
			// 3. 빼고 넣고 하면서 연속 4개면 없애기..
			ArrayDeque<Integer> tmp = new ArrayDeque<>();
			que.add(-1);
			while (true) {
				boolean flag = true;
				int iter = que.size();
				int conti = 0;
				int pre = que.peek();
				for (int i=0; i<iter; i++) {
					int cur = que.poll();
					if(cur == -1) break;
					if (cur==pre) {
						conti++;
					} else{
						if (conti >= 4) {
							if (pre==1) oneCount+=conti;
							else if (pre==2) twoCount+=conti;
							else if (pre==3) threeCount+=conti;
							flag = false;
							for (int j=0; j<conti; j++) {
								tmp.pollLast();
							}
						}
						conti = 1;
					}
					tmp.add(cur);
					pre = cur;
				}
				
				if(conti>=4) {
					if (pre==1) oneCount+=conti;
					else if (pre==2) twoCount+=conti;
					else if (pre==3) threeCount+=conti;
					for (int j=0; j<conti; j++) {
						tmp.pollLast();
					}
				}
				while(!tmp.isEmpty()) {
					que.addFirst(tmp.pollLast());
				}
				if(flag) break;
				que.add(-1);
			}
			
			
			
			// 4. 구슬 변화
			while(!que.isEmpty()) {
				int cur = que.poll();
				int count = 1;
				while(!que.isEmpty() && que.peek()==cur) {
					que.poll();
					count++;
				}
				tmp.add(count);
				tmp.add(cur);
			}
			
			//5. 달팽이 돌면서 다시 넣기
			cnt = 0;
			step = 1;
			dir = 0;
			ny = midY;
			nx = midX;
			nxt: while(true) {
				for (int i=0; i<step; i++) {
					if (tmp.isEmpty()) break nxt;
					ny += dy[dir];
					nx += dx[dir];
					
					map[ny][nx] = tmp.poll();
					if (ny==0 && nx==0) break nxt;
				}
				if (++cnt == 2) {
					step++;
					cnt = 0;
				}
				dir = (dir+1)%4;
			}
//			for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
//			System.out.println();
		}
		
		System.out.println(oneCount + twoCount*2 + threeCount*3);
	}
}

/*
	N*N 격자판, N은 항상 홀수 칸, r = 1~N, c = 1~N
	
	마법사 상어는 (N+1)/2, (N+1)/2 (가운데)에 존재
	토네이도 형태로 벽이 세워져 있음
	
	
	1. d 방향으로 s거리만큼 구슬을 모두 파괴 (벽 무시)
	2. 구슬이 한 칸 씩 땡겨짐
	3. 구슬  폭발
		- 같은 번호의 구슬들이 4개 이상 연속할 경우 파괴됨
	4. 구슬 변화
		- 연속하는 구슬을 하나의 그룹으로 지정
		- 하나의 그룹은 2개의 구슬 A, B로 변함
			- A : 그룹에 들어있는 구슬의 개수
			- B : 그룹을 이루고 있는 구슬의 번호
			
	-> M번 수행 후 1*(폭발한 1번 구슬의 개수) + 2*(폭발한 2번 구슬의 개수) + 3*(폭발한 3번 구슬의 개수)
	
	
	
	1. map에서 방향으로 파괴
	2. 달팽이로 돌면서 que에 넣기 (0 제외)
		- 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6 ... 
	
	3. 빼고 넣고 하면서 연속 4개면 없애기..
	4. 빼면서 개수 세고 차례로 맵에 채워 넣기
	
	
	
*/