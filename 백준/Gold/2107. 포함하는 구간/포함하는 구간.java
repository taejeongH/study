//package BOJ.포함하는구간;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][2];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(map, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0]) return o1[1]-o2[1];
				return o1[0]-o2[0];
			}
		});
		
//		for (int i=0; i<N; i++) System.out.println(Arrays.toString(map[i]));
		
		int res = 0;
		for (int i=0; i<N; i++) {
			int sum = 0;
			for (int j=i+1; j<N; j++) {
				if (map[i][1]<map[j][0]) break;
				if (map[i][0]<map[j][0] && map[i][1]>map[j][1]) sum++;
			}
			res = Math.max(sum, res);
		}
		System.out.println(res);
	}

}


/*
	1<=N<=25,000
	1<=점의 위치A,B<=2,000,000,000
	
	수직선상에 N개의 구간이 존재
	구간의 양 끝점이 주어짐(시작, 끝)
	점들은 겹치지 않음(한 위치에는 점이 오직 1개만 존재)
	어떤 한 구간이 다른 구간들을 최대한 많이 포함하고 있는 개수 찾기
	
	완탐 
	 - for문돌려서 범위 안에 들어오는 게 있다면++ -> 시간복잡도 25000 * 25000 = 6.25억
	   -> 정렬해서 탐색한다면... 점의 범위가 너무 커서 크게 의미는 없을 것 같음
	
	이분 탐색을 할 수 있을까?
	 -> 일단 크기로 정렬해서 나보다 작은건 패스해도 되는데..
	
	아이디어 1. 각 구간들의 크기를 추가로 저장해서 크기별로 정렬 -> 작은 것들 부터 자기 안에 속하는 
	
*/