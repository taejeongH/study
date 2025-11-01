//package BOJ.별찍기10;

import java.io.*;
import java.util.*;

public class Main {
	static boolean[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		
		star(0, 0, N, N);
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(map[i][j]) sb.append("*");
				else sb.append(" ");
			}
			sb.append("\n");
		}
		
//		for(int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
		System.out.print(sb);
	}
	
	public static void star(int sy, int sx, int ey, int ex) {
		if (ey - sy == 3) {
			int idx = 0;
			for (int i=sy; i<ey; i++) {
				for (int j=sx; j<ex; j++) {
					if(idx==4) {
						idx++;
						continue;
					}
					map[i][j] = true;
					idx++;
				}
			}
			return;
		}
		
		
		//3*3으로 나눠서 가운데 빼고 ㄱㄱ
		int newy = (ey-sy)/3;
		int newx = (ex-sx)/3;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if(i== 1 && j==1) continue;
				star(sy + (newy * i), sx + (newx * j), sy + newy * (i+1), sx + newx * (j+1));				
			}
		}
		
		
		
		
	}
}
