import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static int M;
	static int[][] map;
	static List<int[]> cctv = new ArrayList<>();
	static boolean[] visited;
	static int result = Integer.MAX_VALUE;
	static boolean[][] dirVisited;
	
	static int[][] oneDir = { {0, -1}, {0, 1}, {1, 0}, {-1, 0} };
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //y 크기
		M = Integer.parseInt(st.nextToken()); //x 크기
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {
					cctv.add(new int[] {i, j});
				} 
			}
		}
		
		visited = new boolean[cctv.size()];
		dirVisited = new boolean[cctv.size()][];
		for (int i = 0; i < cctv.size(); i++) {
			if (map[cctv.get(i)[0]][cctv.get(i)[1]] == 2) {
				dirVisited[i] = new boolean[2];
			} else if (map[cctv.get(i)[0]][cctv.get(i)[1]] == 5) {
				dirVisited[i] = new boolean[1];
			} else {
				dirVisited[i] = new boolean[4];
			}
		}
		bt(0);
		System.out.println(result);
		
	}
	
	public static void bt(int num) {
		if (num == cctv.size()) {
			int unsafeZone = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0) {
						unsafeZone++;
					}
				}
				//System.out.println(Arrays.toString(map[i]));
				
			}
			result = Math.min(unsafeZone, result);
			
		} else {
			for (int i = num; i < cctv.size(); i++) {
				if(!visited[i]) {
					
					int cctvNum = map[cctv.get(i)[0]][cctv.get(i)[1]];
					visited[i] = true;
					if (cctvNum == 1) {
						for (int j = 0; j < 4; j++) {
							if (!dirVisited[i][j]) {
								dirVisited[i][j] = true;
								check(cctv.get(i), oneDir[j], 0, num+10);
								bt(num + 1);
								check(cctv.get(i), oneDir[j], num+10, 0);	
								dirVisited[i][j] = false;
							}
						}
					} else if (cctvNum == 2) {
						int idx = 0;
						for (int j = 0; j < 3; j+=2) {
							if(!dirVisited[i][idx]) {
								dirVisited[i][idx] = true;
								check(cctv.get(i), oneDir[j], 0, num+10);
								check(cctv.get(i), oneDir[j+1], 0, num+10);
								bt(num+1);
								check(cctv.get(i), oneDir[j], num+10, 0);
								check(cctv.get(i), oneDir[j+1], num+10, 0);
								dirVisited[i][idx++] = false;
							} 
						}
					} else if (cctvNum == 3) {
						int idx = 0;
						for (int j = 0; j < 2; j++) {
							for (int k = 2; k <= 3; k++) {
								if(!dirVisited[i][idx]) {
									dirVisited[i][idx] = true;
									check(cctv.get(i), oneDir[j], 0, num+10);
									check(cctv.get(i), oneDir[k], 0, num+10);
									
									bt(num+1);
									check(cctv.get(i), oneDir[j], num+10, 0);
									check(cctv.get(i), oneDir[k], num+10, 0);
									dirVisited[i][idx++] = false;
								}
							}
						}
					} else if (cctvNum == 4) {
						int idx = 0;
						for (int j = 0; j < 4; j++) {
							if (!dirVisited[i][j]) {
								dirVisited[i][j] = true;
								if (j == 0) {
									check (cctv.get(i), oneDir[0], 0, num+10);
									check (cctv.get(i), oneDir[2], 0, num+10);
									check (cctv.get(i), oneDir[3], 0, num+10);
								} else if(j == 1) {
									check (cctv.get(i), oneDir[0], 0, num+10);
									check (cctv.get(i), oneDir[1], 0, num+10);
									check (cctv.get(i), oneDir[2], 0, num+10);
								} else if (j == 2) {
									check (cctv.get(i), oneDir[1], 0, num+10);
									check (cctv.get(i), oneDir[2], 0, num+10);
									check (cctv.get(i), oneDir[3], 0, num+10);
								} else {
									check (cctv.get(i), oneDir[1], 0, num+10);
									check (cctv.get(i), oneDir[0], 0, num+10);
									check (cctv.get(i), oneDir[3], 0, num+10);
								}
								
								bt(num+1);
								
								if (j == 0) {
									check (cctv.get(i), oneDir[0], num+10, 0);
									check (cctv.get(i), oneDir[2], num+10, 0);
									check (cctv.get(i), oneDir[3], num+10, 0);
								} else if(j == 1) {
									check (cctv.get(i), oneDir[0], num+10, 0);
									check (cctv.get(i), oneDir[1], num+10, 0);
									check (cctv.get(i), oneDir[2], num+10, 0);
								} else if (j == 2) {
									check (cctv.get(i), oneDir[1], num+10, 0);
									check (cctv.get(i), oneDir[2], num+10, 0);
									check (cctv.get(i), oneDir[3], num+10, 0);
								} else {
									check (cctv.get(i), oneDir[1], num+10, 0);
									check (cctv.get(i), oneDir[0], num+10, 0);
									check (cctv.get(i), oneDir[3], num+10, 0);
								}
								dirVisited[i][j] = false;
							}
						}
						
					} else {
						if(!dirVisited[i][0]) {
							dirVisited[i][0] = true;
							for (int j = 0; j < 4; j++) {
								check(cctv.get(i), oneDir[j], 0, num + 10);
							}
							bt (num + 1);
							for (int j = 0; j < 4; j++) {
								check(cctv.get(i), oneDir[j], num+10, 0);
							}
							dirVisited[i][0] = false;
						}
					}
					
					visited[i] = false;
				}
			}
		}
	}

	public static void check(int[] start, int[] dir, int from, int to) {
		int startY = start[0];
		int startX = start[1];
		
		while(true) {
			int nextY = startY + dir[0];
			int nextX = startX + dir[1];
			
			if(nextY > N-1 || nextY < 0 || nextX > M-1 || nextX < 0 || map[nextY][nextX] == 6) {
				return;
			}
			
			if (map[nextY][nextX] == from) {
				map[nextY][nextX] = to;
			}
			
			
			startY = nextY;
			startX = nextX;
		}
	}

}