import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //y
		int M = Integer.parseInt(st.nextToken()); //x
		int[][] map = new int[N][M]; //방을 저장할 배열

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); //로봇의 초기 좌표 y
		int c = Integer.parseInt(st.nextToken()); //로봇의 초기 좌표 x
		int d = Integer.parseInt(st.nextToken()); //로봇의 초기 방향
		int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}  }; //로봇이 바라보는 방향
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		//로봇이 청소한 칸의 개수를 계산할 변수
		int result = 0;
		
		//로봇의 현재 위치, 방향을 저장할 변수들
		int curY = r;
		int curX = c;
		int[] curDir = dir[d];
		while(true) {
			
			if (map[curY][curX] == 0) {
				map[curY][curX] = 2;
				result++;
			}
			
			//현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 존재하는지 확인한다.
			boolean isDirty = false;
			for (int i = 0; i < 4; i++) {
				int nextY = curY + dy[i];
				int nextX = curX + dx[i];
				if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < M) {
					if (map[nextY][nextX] == 0) {
						isDirty = true;
						break;
					}
				}
			}
			
			
			if(isDirty) {
				//주변 4칸 중 청소되지 않은 빈 칸이 존재한다면,
				for (int i = 0; i < 4; i++) {
					//회전
					d--;
					if (d == -1) d = 3;
					curDir = dir[d];
					
					int nextY = curY + curDir[0];
					int nextX = curX + curDir[1];
					if (map[nextY][nextX] == 0) {
						curY = nextY;
						curX = nextX;
						break;
					}
					
				}
			} else {
				//주변 4칸 중 청소되지 않은 빈 칸이 존재하지 않는다면,
				int nextY = curY - curDir[0];
				int nextX = curX - curDir[1];
				
				//만약 후진을 못하는 경우라면 종료한다.
				if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || map[nextY][nextX] == 1) {
					break;
				} else {
					
					curY = nextY;
					curX = nextX;
				}
			}

		}
		System.out.println(result);
			
	}

}