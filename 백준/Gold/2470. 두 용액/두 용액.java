//package BOJ.두용액;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(map);
		int resl = 0;
		int resr = 0;
		long min = Long.MAX_VALUE;
		for (int i=0; i<N; i++) {
			int cur = map[i];
			
			int lower = lower(cur*-1);
			
			if(lower==i) {
				if (lower==0) {
					lower++;
				} else {
					lower--;
				}
			}
			if(lower!=N) {
				int lowerAbs = Math.abs(map[lower]+cur);
				if (lowerAbs==0) {
					resl = cur;
					resr = map[lower];
					break;
				}
				
				if(lowerAbs < min) {
					resl = cur;
					resr = map[lower];
					min = lowerAbs;
				}
			}
			
			if(lower!=0 && lower-1!=i) {
				int upperAbs = Math.abs(map[lower-1]+cur);
				if (upperAbs < min) {
					resl = cur;
					resr = map[lower-1];
					min = upperAbs;
				}
			}
		}
		System.out.println(resl<resr?resl+" "+resr:resr+" "+resl);
	}
	
	public static int lower(int key) {
		int l = 0;
		int r = N;
		int mid = 0;
		while(l<r) {
			mid = (l+r)/2;
			if(key <= map[mid]) {
				r = mid;
			} else {
				l = mid+1;
			}
		}
		return l;
	}
}

/*


*/