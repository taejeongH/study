//package BOJ.연구소2;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, res;
	static int[][] map;
	static ArrayList<int[]> virus;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //크기
		M = Integer.parseInt(st.nextToken()); //바이러스 개수
		
		map = new int[N][N];
		virus = new ArrayList<>();
		cnt = 0;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) virus.add(new int[] {i, j});
				if(map[i][j]!=1) cnt++;
			}
		}
		
		res = Integer.MAX_VALUE;
		bt(0, 0, new int[M][2]);
		System.out.println(res==Integer.MAX_VALUE?-1:res);
		
	}
	
	public static void bt(int depth, int start, int[][] virusPos) {
		if (depth==M) {
			res = Math.min(bfs(virusPos), res);
			return;
		}
		
		for (int i=start; i<virus.size(); i++) {
			int y = virus.get(i)[0];
			int x = virus.get(i)[1];
			
			virusPos[depth][0] = y;
			virusPos[depth][1] = x;
			bt(depth+1, i+1, virusPos);
		}
		
	}
	
	public static int bfs(int[][] virusPos) {
		ArrayDeque<int[]> que = new ArrayDeque<>();
		int visit = 0;
		boolean[][] v = new boolean[N][N];
		for (int i=0; i<M; i++) {
			int y = virusPos[i][0];
			int x = virusPos[i][1];
			que.add(new int[] {y, x, 0} );
			v[y][x] = true;
			visit++;
		}
		if(visit==cnt) return 0;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int dis = now[2];
			
			for (int i=0; i<4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if(ny<0 || ny>=N || nx<0 || nx>=N || v[ny][nx] || map[ny][nx]==1) continue;
				if (++visit==cnt) return dis+1;
				v[ny][nx] = true;
				que.add(new int[] {ny, nx, dis+1});
			}
		}
		
		
		return Integer.MAX_VALUE;
	}
}

/*
	0 : 빈칸
	1 : 벽
	2 : 바이러스 놓을 수 있는 칸
	
	모든 빈 칸에 바이러스를 퍼뜨리는 최소 시간
	
	N<=50
	M<=10

	
	10군데에 놓아보
*/