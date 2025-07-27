import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); //플레이어 수
		int[] directory = new int[1000001];
		int[] map = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			directory[map[i]] = 1;
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < N; i++) {
			
			int multiple = map[i];
			int cnt = 2;
			int sum = 0;
			while(multiple * cnt < directory.length) {
				if (directory[multiple * cnt] == 1) { 
					sum++;
				}
				
				cnt++;
			}

			for (int j = 1; j <= (int) Math.sqrt(map[i]); j++) {
				if (map[i] % j == 0 && map[i] != j) {
					if (directory[j] == 1) {
						sum -= 1;
					}
					
					if (map[i] % (map[i]/j) == 0 && directory[map[i]/j] == 1) {
						if (map[i]/j != j && map[i]/j != map[i]) {
							sum -= 1;
						}
					}
				}
				
			}
			
//			if(directory[1] == 1) {
//				sum -= 1;
//			}
			
			sb.append(sum + " ");
		}
		System.out.println(sb.toString());
		
		
	}	
}