import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());//시험장의 개수
		int[] map = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());//총감독관이 감시할 수 있는 응시자 수
		int C = Integer.parseInt(st.nextToken());//부감독관이 감시할 수 있는 응시자수
		
		long sum = 0;
		for (int i = 0; i < N; i++) {
			map[i] -= B;
			sum++;
			
			if(map[i] > 0) {
				if (map[i] % C == 0) {
					sum += map[i]/C;
				} else {
					sum += map[i]/C+1;
				}
			}
		}
		System.out.println(sum);
		
			
	}
}