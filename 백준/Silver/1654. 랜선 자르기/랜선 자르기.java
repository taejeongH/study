import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken()); //랜선의 개수
		int N = Integer.parseInt(st.nextToken()); //만들어야 하는 개수
		
		int[] map = new int[K];
		long start = 1;
		long end = 1;
		for (int i = 0; i < K; i++) {
			map[i] = Integer.parseInt(br.readLine());
			end = Math.max(end, map[i]);
		}
		
		while (start <= end) {
			long mid = (start + end) / 2;
			int cnt = 0;
			
			for (int i = 0; i < K; i++) {
				cnt += map[i]/mid;
			}
			
			if (cnt < N) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		System.out.println(end);
	}
}