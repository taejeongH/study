//package SWEA.홈방범서비스2117;

import java.util.*;
import java.io.*;

public class Solution {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/SWEA/홈방범서비스2117/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test=1; test<=testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			//운영 비용 = K * k + (K-1)*(K-1)
			//얻는 수익 = M * 집의 개수
			//얻는수익 - 운영 비용이 0이하가 되지 않아야함
			
			int[][] map = new int[N][N];
			int homeCount = 0;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) homeCount++;
				}
			}
			
			int maxHouse = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					int K = 1;
					ArrayDeque<int[]> que = new ArrayDeque<>();
					boolean[][] visited = new boolean[N][N];
					visited[i][j] = true;
					que.add(new int[] {i, j, 0});
					int houseCount = 0;
					if(map[i][j]==1) houseCount++;
					while(!que.isEmpty()) {
						int[] now = que.poll();
						int y = now[0];
						int x = now[1];
						int d = now[2];
						if(d==K-1) {
							if ((houseCount*M)-(K*K+(K-1)*(K-1)) >= 0) {
								maxHouse = Math.max(maxHouse, houseCount);
							}
							K++;
						}
						
						for (int k=0; k<4; k++) {
							int nextY = y+dy[k];
							int nextX = x+dx[k];
							
							if(nextY>=0 && nextY<N && nextX>=0 && nextX<N && !visited[nextY][nextX]) {
								visited[nextY][nextX] = true;
								que.add(new int[] {nextY, nextX, d+1});
								if(map[nextY][nextX]==1) houseCount++;
							}
						}
					}
						
				}
			}
			
			sb.append("#").append(test).append(" ").append(maxHouse).append("\n");
			
		}
		System.out.println(sb.toString());
		
	}
}
