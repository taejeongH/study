import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static int min = Integer.MAX_VALUE;
	static int max = -100000000;
	static int[] map;
	static int[] opCount;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		N = Integer.parseInt(br.readLine()); //숫자 개수
		map = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		opCount = new int[4]; //{+, -, *, /}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			opCount[i] = Integer.parseInt(st.nextToken());
		}
		
		bt(0, map[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void bt(int num, int sum) {
		if (num == N-1) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
		} else {
			for (int i = 0; i < 4; i++) {
				if (opCount[i] != 0) {
					int upSum = sum;
					if (i == 0) {
						upSum += map[num+1];
					} else if(i == 1) {
						upSum -= map[num+1];
					} else if(i == 2) {
						upSum *= map[num+1];
					} else {
						upSum /= map[num+1];
					}
					opCount[i] -= 1;
					bt(num+1, upSum);
					opCount[i] += 1;
					
				}
				
				
			}
			
		}
	}
}