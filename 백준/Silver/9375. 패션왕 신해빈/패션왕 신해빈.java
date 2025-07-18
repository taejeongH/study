import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for (int test = 0; test < testCase; test++) {
			int N = Integer.parseInt(br.readLine());
			HashMap<String, Integer> map = new HashMap<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String clothName = st.nextToken();
				String clothType = st.nextToken();
				if (map.containsKey(clothType)) {
					map.put(clothType, map.get(clothType)+1);
				} else {
					map.put(clothType, 1);
				}
			}
			

			int result = 1;
			for (String clothType : map.keySet()) {
				result *= map.get(clothType) + 1;
			}
			
			System.out.println(result-1);
		}
	}

}