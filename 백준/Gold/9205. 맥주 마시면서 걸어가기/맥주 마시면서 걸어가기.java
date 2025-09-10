//package BOJ.맥주마시면서걸어가기;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int test=1; test<=testCase; test++) {
			int N = Integer.parseInt(br.readLine()); //편의점 개수
			int[][] g = new int[N+2][N+2];
			
			ArrayList<int[]> pos = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int startY = Integer.parseInt(st.nextToken());
			int startX = Integer.parseInt(st.nextToken());
			pos.add(new int[] {startY, startX});
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				pos.add(new int[] {y, x});
			}
			st = new StringTokenizer(br.readLine());
			int endY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			pos.add(new int[] {endY, endX});
			
			for (int i=0; i<pos.size()-1; i++) {
				for (int j=i+1; j<pos.size(); j++) {
					int sy = pos.get(i)[0];
					int sx = pos.get(i)[1];
					int ey = pos.get(j)[0];
					int ex = pos.get(j)[1];
					g[i][j] = Math.abs(sy-ey) + Math.abs(sx-ex);
					g[j][i] = Math.abs(sy-ey) + Math.abs(sx-ex);
				}
			}
			
			ArrayDeque<Integer> que = new ArrayDeque<>();
			que.add(0);
			String res = "sad";
			boolean[] v = new boolean[N+2];
			v[0] = true;
			while(!que.isEmpty()) {
				int node = que.poll();
				if(node==N+1) {
					res = "happy";
					break;
				}
				
				for (int i=0; i<N+2; i++) {
					if (g[node][i]!=0 && g[node][i]<=1000 && !v[i])  {
						v[i] = true;
						que.add(i);
					}
				}
			}
			
//			for (int i=0; i<N+2; i++) System.out.println(Arrays.toString(g[i]));
//			System.out.println();
			
			System.out.println(res);
		}
		
	}
}
