//package BOJ.마법사상어와비바라기;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {N-1, 0});
		que.add(new int[] {N-1, 1});
		que.add(new int[] {N-2, 0});
		que.add(new int[] {N-2, 1});
		boolean[][] v = new boolean[N][N];
		for (int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken())-1;
			int amount = Integer.parseInt(st.nextToken());
			for (int i=0; i<N; i++) Arrays.fill(v[i], false);
			
			//1. 모든 구름이 dir 방향으로 amount칸 이동
			int iter = que.size();
			for(int i=0; i<iter; i++) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				
				int ny = (y+(dy[dir]*amount)) % N;
				int nx = (x+(dx[dir]*amount)) % N;
				
				if(ny<0) ny = N+ny;
				if(nx<0) nx = N+nx;
				
				v[ny][nx]=true;
				que.add(new int[] {ny, nx});
			}
			
			//2. 물의 양 1 증가
			iter = que.size();
			for(int i=0; i<iter; i++) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				map[y][x]++;
				que.add(now);
			}
			
			//4. 대각선 방향 탐색 -> 물양 증가
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				
				int cnt = 0;
				for (int i=1; i<8; i+=2) {
					int ny = y+dy[i];
					int nx = x+dx[i];
					if(ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx]==0) continue;
					cnt++;
				}
				map[y][x] += cnt;
			}
			
			//5. 2이상인 곳에 구름 생성하기 
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (map[i][j]<2 || v[i][j]) continue;
					
					map[i][j]-=2;
					que.add(new int[] {i, j});
				}
			}
			
		}
		
		int res = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				res += map[i][j];
			}
		}
		System.out.println(res);
	}
}


/*
	N*N 크기의 격자판
	
	물을 저장할 수 있고, 양에 제한은 없음
	(r,c) = (1,1) ~ (N, N)
	1번행과 N번행, 1번열과 N번열은 연결되어ㅣㅇㅆ음
	
	비바라기 시전시 (N,1), (N,2), (N-1,1), (N-1, 2)에 비구름 생김
	
	구름에 이동을 M번 명령
	 1. 모든 구름이 di 방향으로 si칸 이동
	 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 ㅈ으가
	 3. 구름이 모두 사라짐
	 4. 2에서 물이 증가한 칸 (r, c)에 물복사 버그 마법을 시전, 
	  - 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물의 양이 증가
	 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어듬.
	  - 3에서 구름이 사라진 칸이 아니어야 함
	
*/