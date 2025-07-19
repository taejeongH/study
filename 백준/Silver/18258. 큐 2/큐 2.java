import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		
		Queue<String> que = new LinkedList<String>();
		String last = "";
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			String[] orderArr = input.split(" ");

			String order = orderArr[0];
			if (order.equals("push")) {
				que.add(orderArr[1]);
				last = orderArr[1];
			} else if (order.equals("pop")) {
				if (que.isEmpty()) {
					bw.write(-1 + "\n");
					//bw.flush();
				} else {
					bw.write(que.poll() + "\n");
					//bw.flush();
				}
			} else if (order.equals("size")) {
				bw.write(que.size() + "\n");
				//bw.flush();
			} else if (order.equals("empty")) {
				if (que.isEmpty()) {
					bw.write(1 + "\n");
					//bw.flush();
				} else {
					bw.write(0 + "\n");
					//bw.flush();
				}
			} else if (order.equals("front")) {
				if (que.isEmpty()) {
					bw.write(-1 + "\n");
					//bw.flush();
				} else {
					bw.write(que.peek() + "\n");
					//bw.flush();
				}
			} else {
				if (que.isEmpty()) {
					bw.write(-1 + "\n");
					//bw.flush();
				} else {
					bw.write(last + "\n");
					//bw.flush();
				}
			}
			
			
		}
		bw.flush();
		bw.close();
		
		
		
		
	}

}