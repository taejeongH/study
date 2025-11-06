//package BOJ.넴모넴모2020;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] count = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			count[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken())-1;
			int sum = 0;
			//y는 정렬된 상태이니깐 . . . lowerbound?
			int idx = lowerBound(count, x);
			if (idx != -1) {
				if (idx - y > 0) {
					sum += idx - y;
				}
			} else {
				sum += N-y;
			}
			
			
			if (x <= count[y]) sum += count[y]-x;
			
//			if (count[y] >= x) sum++;
			sb.append(sum).append("\n");
		}
		System.out.print(sb);
		
	}
	
	public static int lowerBound(int[] arr, int key) {
		int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < key) {
            	result = mid;
            	right = mid - 1;
            } else {
            	left = mid + 1;
            }
        }
        return result;
	}
}


/*
	y는 아래부터 0
	
	(x, y)에 레이저를 쏘면 (x, y)보다 위인 넴모들, (x, y)와 같은 층이면서 오른쪾인 넴모들이 사라짐
	
	레이저를 설치할 수 있는 위치 Q개
*/