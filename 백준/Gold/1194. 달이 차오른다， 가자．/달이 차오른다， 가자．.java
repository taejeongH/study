//package SWEA.달이차오른다가자;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j]=='0') que.add(new int[] {i, j, 0, 0});
			}
		}
		
		boolean[][][] v = new boolean[N][M][64];
		int result=-1;
		
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int dis = now[2];
			int key = now[3];
			
			if(map[y][x]=='1') {
				result = dis;
				break;
			}
			
		  for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (map[ny][nx] == '#') continue;

                int newKeys = key;

                char ch = map[ny][nx];
                if (ch >= 'a' && ch <= 'f') { // 키 획득
                    newKeys |= (1 << (ch - 'a'));
                } else if (ch >= 'A' && ch <= 'F') { // 문
                    if ((key & (1 << (ch - 'A'))) == 0) continue; // 키 없으면 이동 불가
                }

                if (!v[ny][nx][newKeys]) {
                    v[ny][nx][newKeys] = true;
                    que.add(new int[] {ny, nx, dis + 1, newKeys});
                }
            }
		} 
		
		 
		System.out.println(result);
	}
}
