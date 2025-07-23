import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken()); //endy
		int c = Integer.parseInt(st.nextToken()); //endx
		int result = 0;
		
		int startY = 0;
		int startX = 0;
		int endY = (int) Math.pow(2, N);
		int endX = (int) Math.pow(2, N);
		int startNum = 0;
		int endNum = (int) Math.pow(2, N) * (int) Math.pow(2, N) - 1;

		while(true) {
			int midY = startY + (endY - startY)/2;
			int midX = startX + (endX - startX)/2;
			
			if (startY == r && startX == c) {
				result = startNum;
				break;
			}
			
			//4면으로 나누어서 탐색
			if (r < midY && r >= startY && c < midX && c >= startX) {
				int nextEndNum = startNum + (endNum - startNum)/4;
				endNum = nextEndNum;
				endY = midY;
				endX = midX;
			} else if (r < midY && r >= startY && c < endX && c >= midX) {
				int nextStartNum = startNum + (endNum - startNum)/4 + 1;
				int nextEndNum = startNum + (endNum - startNum)/2;
				startNum = nextStartNum;
				endNum = nextEndNum;
				startX = midX;
				endY = midY;
			} else if (r < endY && r >= midY && c < midX && c >= startX) {
				int nextStartNum = startNum + (endNum - startNum)/2 + 1;
				int nextEndNum = startNum + (endNum - startNum)/2 +(endNum - startNum)/4 + 1;
				startNum = nextStartNum;
				endNum = nextEndNum;
				startY = midY;
				endX = midX;
			} else {
				int nextStartNum = startNum + (endNum - startNum)/2 +(endNum - startNum)/4 + 2;
				startNum = nextStartNum;
				startY = midY;
				startX = midX;
			}
			
//			System.out.println(startY + " " + startX);
//			System.out.println(endY + " " + endX);
//			System.out.println(startNum);
//			System.out.println(endNum);
			
		}
		System.out.println(result);
		
		
	}

}