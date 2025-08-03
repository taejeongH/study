import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());//지도의 크기
		int L = Integer.parseInt(st.nextToken());//경사로의 길이
		
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		boolean[][] visited = new boolean[N][N];
		
		//세로 계산
		for (int i = 0; i < N; i++) {
			boolean isPass = true;
			int start = 0;
			int end = N-1;
			
			int sum = 1;
			while (start < end) {
				int cur = map[start][i];
				int next = map[start+1][i];

				if (cur == next) {
					//만약 다음 높이가 현재 높이와 같다면 sum++
					if (!visited[start][i])
					sum += 1;
				} else if (cur < next && Math.abs(cur-next) == 1) {
					//현재 높이가 더낮은데, 차이가 1이라면
					if (sum >= L && !visited[start][i]) {
						//주어진 경사로의 길이보다 현재 계산된 연속된 수가 더 길다면 길이를 초기화하고 계속 진행
						sum = 1;
					} else {
						//주어진 경사로의 길이보다 현재 계산된 연속된 수가 더 짧다면 못지나가니 break
						isPass = false;
						break;
					}
				} else if (cur < next && Math.abs(cur-next) > 1) {
					//다음 높이가 현재 높이보다 1보다 더 크다면 못지나감
					isPass = false;
					break;
				} else if (cur > next && Math.abs(cur-next) == 1) {
					//다음 높이가 더 낮다면 못지나감
					start++;
					sum=1;
					while(start < end) {
						cur = map[start][i];
						next = map[start+1][i];
						visited[start][i] = true;
						if (cur != next || sum == L) {
							break;
						}
						
						sum++;
						start++;
					}
					if (sum >= L) {
						sum = 1;
						start--;
					} else {
						isPass = false;
						break;
					}
				} else {
					isPass = false;
					break;
				}
				start++;
			}
			if(isPass) {
				result++;
			}
		}
		
		
		visited = new boolean[N][N];
		//가로 계산
		for (int i = 0; i < N; i++) {
			boolean isPass = true;
			int start = 0;
			int end = N-1;
			
			int sum = 1;
			while (start < end) {
				int cur = map[i][start];
				int next = map[i][start+1];

				if (cur == next) {
					//만약 다음 높이가 현재 높이와 같다면 sum++
					if (!visited[i][start])
					sum += 1;
				} else if (cur < next && Math.abs(cur-next) == 1) {
					//현재 높이가 더낮은데, 차이가 1이라면
					if (sum >= L && !visited[i][start]) {
						//주어진 경사로의 길이보다 현재 계산된 연속된 수가 더 길다면 길이를 초기화하고 계속 진행
						sum = 1;
					} else {
						//주어진 경사로의 길이보다 현재 계산된 연속된 수가 더 짧다면 못지나가니 break
						isPass = false;
						break;
					}
				} else if (cur < next && Math.abs(cur-next) > 1) {
					//다음 높이가 현재 높이보다 1보다 더 크다면 못지나감
					isPass = false;
					break;
				} else if (cur > next && Math.abs(cur-next) == 1){
					//다음 높이가 더 낮고 차이가 1이라면
					start++;
					sum=1;
					while(start < end) {
						cur = map[i][start];
						next = map[i][start+1];
						visited[i][start] = true;
						if (cur != next || sum == L) {
							break;
						}
						sum++;
						start++;
					}
					if (sum >= L) {
						sum = 1;
						start--;
					} else {
						isPass = false;
						break;
					}
				} else {
					isPass = false;
					break;
				}
				start++;
			}
			if(isPass) {
				result++;
			}
		}
	
		
		System.out.println(result);
		
		
	}
	
	

}