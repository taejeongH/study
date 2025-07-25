import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;



public class Main{
	public static int N;
	public static int result;
	public static boolean[][] crossVisited;
	public static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N];
		crossVisited = new boolean[N][N];
		
		
		result = 0;
		bt(0);
		System.out.println(result);
	}
	public static void bt(int num) {
		if (num == N) {
			result++;
		} else {
			for (int i = 0; i < N; i++) {
				boolean check = checkCross(num, i);
				if (!visited[i]  && check) {
					visited[i] = true;				//가로, 세로 방향 방문 처리
					crossVisited[num][i] = true;	//현재 방문한 곳의 위치는 (num, i)
					bt(num+1);
					crossVisited[num][i] = false;
					visited[i] = false;
				}
				
			}
		}
	}
	
	//대각선에 방문한 곳이 있다면 False, 없다면 True 반환
	public static boolean checkCross(int startY, int startX) {

		int cnt = 1;
		while(startY - cnt >= 0 && startX + cnt < N) {
			if (crossVisited[startY-cnt][startX+cnt]) {
				return false;
			}
			cnt++;
		}
		
		cnt = 1;
		while(startY + cnt < N && startX + cnt < N) {
			if (crossVisited[startY+cnt][startX+cnt]) {
				return false;
			}
			cnt++;
		}
		
		cnt = 1;
		while(startY - cnt >= 0 && startX - cnt >= 0) {
			if (crossVisited[startY-cnt][startX-cnt]) {
				return false;
			}
			cnt++;
		}
		
		cnt = 1;
		while(startY + cnt < N && startX - cnt >= 0) {
			if (crossVisited[startY+cnt][startX-cnt]) {
				return false;
			}
			cnt++;
		}
		
//		System.out.println(startY + " " + startX);
//		System.out.println(Arrays.toString(visited));
//		for (int i = 0; i < visited.length; i++) {
//			System.out.println(Arrays.toString(crossVisited[i]));
//		}
		
		return true;

	}
	

}