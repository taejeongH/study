import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {	
	public static int[][] map;
	public static int white;
	public static int blue;
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); //한 변의 길이
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		white = 0;
		blue = 0;
		int[] start = {0, 0};
		int[] end = {N, N};
		findPaper(0, 0, N, N);
		
		
		System.out.println(white);
		System.out.println(blue);

	}
	
	public static void findPaper(int startY, int startX, int endY, int endX) {
		boolean flag = true;
		//System.out.println(startY);
		int firstVal = map[startY][startX];
		for (int i = startY; i < endY; i++) {
			for (int j = startX; j < endX; j++) {
				if (firstVal != map[i][j]) {
					flag = false;
					break;
				}
			}
			if(!flag) break;
		}
		
		if (flag) {
			int color = map[startY][startX];
			if (color == 1) {
				blue++;
			} else {
				white++;
			}
		} else {
			int midY = startY + (endY - startY)/2;
			int midX = startX + (endX - startX)/2;
			findPaper(startY, startX, midY, midX);	//1
			findPaper(startY, midX, midY, endX);	//2		
			findPaper(midY, startX, endY, midX);	//3
			findPaper(midY, midX, endY, endX);		//4
		}
	}
}