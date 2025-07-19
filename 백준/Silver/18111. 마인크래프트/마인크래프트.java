import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] map = new int[256+1];
		
		int min = 256;
		int max = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[num] += 1;
				max = Math.max(num, max);
				min = Math.min(num, min);
			}
		}
		
		
		if (min == max) {
			System.out.println(0 + " " + min);
		} else {
			int result = 100000000;
			int block = 0;
			for (int i = min; i <= max; i++) {
				int cnt = 0;
				int toNum = i;
				int inven = B;
				boolean flag = true;
				for (int j = max; j >= min; j--) {	//작은 것들을 채우기 위해서는 큰 것들을 깎아서 인벤을 채워야하기 때문에 뒤에서부터 시작
					if (map[j] != 0) {		//해당 개수의 블럭이 1개라도 있을 경우에만 계산
						if (toNum > j) {	//만들어야 하는 블럭의 크기가 현재 블럭보다 크다면 ? -> 인벤토리에 충분한 개수가 있다면, 채우고 아닐경우에는 그 값을 만들 수 없으니 break
							if (inven >= (toNum - j) * map[j]) {
								inven -= (toNum - j) * map[j];
								cnt += (toNum - j) * map[j];
							} else {
								flag = false;;
								break;
							}
						} else if (toNum < j){	//만들어야 하는 블럭의 크기가 현재 블럭보다 작다면, 현재 블럭을 깎아주면 됨 
							cnt += ((j - toNum) * map[j]) * 2;
							inven += (j - toNum) * map[j];
						}
					}
				}
				if (flag) {
					if (result == cnt) {
						block = Math.max(toNum, block);
						result = cnt;
					} else if (result > cnt){
						result = cnt;
						block = toNum;
					}
				}
			}
			System.out.println(result + " " + block);
		}
	}

}