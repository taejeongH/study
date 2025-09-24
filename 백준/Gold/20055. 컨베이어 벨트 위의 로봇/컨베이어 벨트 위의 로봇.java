//package BOJ.컨베이어벨트위의로봇;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] durability= new int[N*2];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N*2; i++) {
			durability[i] = Integer.parseInt(st.nextToken());
		}
		
		int zeroCount=0;
		boolean[] robot = new boolean[N*2];
		int count =0;
		int upArea = 0;
		int downArea = N-1;
		while(zeroCount<K) {
			
			//1.각 칸 위에 있는 로봇과 함께 한 칸 회전
			upArea--;
			if(upArea==-1) upArea=N*2-1;
			downArea--;
			if(downArea==-1) downArea=N*2-1;
			
			robot[downArea]=false;
			
			//로봇이 앞으로 한 칸 씩 전진 내리는 곳 부터 시작
			int cur = downArea;
			int pre = cur-1;
			if(pre < 0) pre = N*2-1;
			while(pre!=downArea) {
				if(!robot[cur] && robot[pre] && durability[cur]!=0) {
					robot[cur]=robot[pre];
					robot[pre]=false;
					if(--durability[cur]==0) zeroCount++;
				}
				cur--;
				if(cur<0) cur=N*2-1;
				pre--;
				if(pre < 0) pre = N*2-1;
			}
			
			//로봇이 내리는 위치에 도착한다면 내리기
			robot[downArea]=false;
			
			
			//3. 내구도가 0이 아니라면 로봇 올리기
			if (durability[upArea]!=0 && !robot[upArea]) {
				robot[upArea] = true;
				if(--durability[upArea]==0) zeroCount++;
			}
			
			count++;
		}
		System.out.println(count);
	}
}

/* 길이가 N인 컨베이어 벨트, 길이가 2N인 벨트가 
 * 1번 = 올리는 위치, N번 = 내리는 위치
 * 로봇은 1번칸에서 올려 N번 칸에서 내림
 * 로봇이 지나간 자리는 내구도가 1만큼 감소
 * 
 * 벨트가 각 칸위에 있는 로봇과 함께 회전
 * 
 */
