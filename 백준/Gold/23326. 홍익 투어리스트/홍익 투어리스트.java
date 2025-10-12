//package BOJ.홍익투어리스트;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] map = new int[N];
		TreeSet<Integer> set = new TreeSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			if (map[i]==1) set.add(i);
		}
		
		
		int pos = 0;
		for (int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			
			if(order==1) {
				int place = Integer.parseInt(st.nextToken())-1;
				if (set.contains(place)) {
					set.remove(place);
				} else {
					set.add(place);
				}
			} else if(order==2) {
				int shift = Integer.parseInt(st.nextToken());
				pos = (pos+shift)%N;
			} else {
				if(set.isEmpty()) {
					sb.append(-1).append("\n");
				} else {
					Integer r = set.ceiling(pos);
					if(r != null) sb.append(r-pos).append("\n");
					else {
						Integer l = set.ceiling(-1);
						if (l != null) sb.append(N-pos + l).append("\n");
						else sb.append(-1).append("\n");
					}
				}
			}
		}
		System.out.println(sb.toString());
		
	}
}

/*
	홍익대학교는 N개의 구역이 원형으로 배치된 모습
	도현이는 현재 1번 구역에 서있음
	
	1i : i번 구역이 명소가 아니었다면 명소로 지정, 명소였다면 지정이 풀림
	2x : 도현이가 시계방향으로 x만큼 이동 (1<=x<=10^9)
	3 : 도현이가 명소에 도달하기 위해 시계방향으로 최소 몇 칸 움직여야 하는 지 출력
	
	명소의 위치들을 가지고 있다면 
	1 5 6 8 
*/