//package BOJ.톱니바퀴2;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		boolean[][] wheel = new boolean[N][8];
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<8; j++) {
				if(input.charAt(j)=='1') wheel[i][j]=true;
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		ArrayDeque<int[]> que = new ArrayDeque<>();
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			boolean clock = dir==1?true:false;
			
			//왼쪽 확인
			int cur = num;
			int curDir = clock?0:1;
			for (int j=num-1; j>=0; j--) {
				if (!(wheel[cur][6]^wheel[j][2])) break;
				
				que.add(new int[] {j, curDir});
				curDir = 1-curDir;
				cur = j;
			}
			
			//오른쪽 확인
			cur = num;
			curDir = clock?0:1;
			for (int j=num+1; j<N; j++) {
				if (!(wheel[cur][2]^wheel[j][6])) break;
				que.add(new int[] {j, curDir});
				cur = j;
				curDir = 1-curDir;
			}
			
			turn(wheel[num], clock);
			while(!que.isEmpty()) {
				int[] now = que.poll();
				
				turn(wheel[now[0]], now[1]==1?true:false);
			}
//			for (int j=0; j<N; j++) System.out.println(Arrays.toString(wheel[j]));
		}
		
		int res = 0;
		for (int i=0; i<N; i++) {
			if(wheel[i][0]) res++;
		}
		System.out.println(res);
	}
	
	public static void turn(boolean[] arr, boolean clock) {
		
		if (clock) {
			//시계방향
			boolean tmp = arr[7];
			for (int i=7; i>=1; i--) {
				arr[i] = arr[i-1];
			}
			arr[0]=tmp;
		} else {
			boolean tmp = arr[0];
			for (int i=0; i<7; i++) {
				arr[i] = arr[i+1];
			}
			arr[7]=tmp;
		}
	}
	
	
}


/*	
	왼쪽확인
	wheel[i][6] ^ wheel[i-1][2] 다르다면 회전 같다면 break
	
	오른쪽 확인
	wheel[i][2] ^ wheel[i+1][2] 다르다면 회전 
	2, 6

*/