import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;





public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> que = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			que.add(i);
		}
		
		int j = 1;
		StringBuffer result = new StringBuffer();
		result.append("<");
		while (!que.isEmpty()) {
			if (j % K == 0) {
				if(que.size() != 1) {
					result.append(Integer.toString(que.poll()) + ", ");
				} else {
					result.append(Integer.toString(que.poll()));
				}
			} else {
				que.add(que.poll());
			}
			j++;
		}
		result.append(">");
		
		System.out.println(result.toString());
		
	}
	
}