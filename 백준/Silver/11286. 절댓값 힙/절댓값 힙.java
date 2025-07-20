import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> minusPq = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> plusPq = new PriorityQueue<Integer>();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num != 0) {
				if (num > 0) {
					plusPq.add(num);
				} else {
					minusPq.add(num);
				}
			} else {
				if (plusPq.isEmpty() && minusPq.isEmpty()) {
					bw.write("0" + "\n");
				} else if (!plusPq.isEmpty() && minusPq.isEmpty()) {
					bw.write(String.valueOf(plusPq.poll()) + "\n");
				} else if (plusPq.isEmpty() && !minusPq.isEmpty()){
					bw.write(String.valueOf(minusPq.poll()) + "\n");
				} else {
					if(plusPq.peek() < Math.abs(minusPq.peek())) {
						bw.write(String.valueOf(plusPq.poll()) + "\n");
					} else {
						bw.write(String.valueOf(minusPq.poll()) + "\n");
					}
				}
				
			}
		}
		
		bw.flush();
		bw.close();
		
	}

}