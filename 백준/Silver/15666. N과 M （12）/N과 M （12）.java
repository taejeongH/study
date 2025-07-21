import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int M;
	public static int[] map;
	public static int[] result;
	public static boolean[] visited;
	public static HashMap<String, Integer> check;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		check = new HashMap<>();
		visited = new boolean[N];
		result = new int[M];
		map = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(map);
		bt(0);
	}
	
	public static void bt(int num) {
		if (num == M) {
			//System.out.println(Arrays.toString(result));
			StringBuilder br = new StringBuilder();
			for (int i = 0; i < result.length; i++) {
				br.append(result[i]).append(" ");
			}
			
			if(!check.containsKey(br.toString())) {
				System.out.println(br.toString());
				check.put(br.toString(), 1);
			}
			
		} else {
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					if (num == 0) {
						//visited[i] = true;
						result[num] = map[i];
						bt(num+1);
						visited[i] = false;
						result[num] = 0;
					} else if (result[num-1] <= map[i]){
						//visited[i] = true;
						result[num] = map[i];
						bt(num+1);
						visited[i] = false;
						result[num] = 0;
					}
				}
			}
		}
	}
}
