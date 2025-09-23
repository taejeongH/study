//package BOJ.열쇠;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[N+2][M+2];
			for (int i=0; i<N+2; i++) Arrays.fill(map[i], '.');
			for (int i=1; i<N+1; i++) {
				String input = br.readLine();
				for (int j=1; j<M+1; j++) {
					map[i][j]=input.charAt(j-1);
				}
			}
			
			HashSet<Character> keys = new HashSet<>();
			String input = br.readLine();
			if (!input.equals("0")) {
				for (int i=0; i<input.length(); i++) {
					keys.add(Character.toUpperCase(input.charAt(i)));
				}
			}
			
			ArrayDeque<int[]> que = new ArrayDeque<>();
			boolean[][] visited = new boolean[N+2][M+2];
			que.add(new int[] {0, 0});
			visited[0][0]=true;
			
			boolean[][] dollarVisited= new boolean[N+2][M+2];
			int res = 0;
			while(!que.isEmpty()) {
				int[] now = que.poll();
				int y = now[0];
				int x = now[1];
				
				if(map[y][x]=='$' && !dollarVisited[y][x]) {
					dollarVisited[y][x]=true;
					res++;
				}
				for (int i=0; i<4; i++) {
					int ny = y+dy[i];
					int nx = x+dx[i];
					
					if(ny<0 || ny>=N+2 || nx<0 || nx>=M+2) continue;
					if(visited[ny][nx] || map[ny][nx]=='*') continue;
					if(map[ny][nx]>=65 && map[ny][nx]<=90) {
						if(!keys.contains(map[ny][nx])) continue;
						map[ny][nx]='.';
					}
					
					if(map[ny][nx]>=97 && map[ny][nx]<=122) {
						keys.add(Character.toUpperCase(map[ny][nx]));
						for (int j=0; j<N+2; j++) Arrays.fill(visited[j], false);

						map[ny][nx]='.';
					}
					visited[ny][nx]=true;
					que.add(new int[] {ny, nx});
				}
			}
//			System.out.println();
//			for(int i=0; i<N+2; i++) System.out.println(Arrays.toString(map[i]));
			
			sb.append(res).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
}
