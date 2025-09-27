//package BOJ.어른상어;

import java.io.*;
import java.util.*;

class Shark{
	int y;
	int x;
	int id;
	int dir;
	int[][] priority = new int[4][4]; //각 방향에서의 우선순위{현재dir}{0}.. 
	
	public Shark(int id, int y, int x) {
		this.id = id;
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shark [y=").append(y).append(", x=").append(x).append(", id=").append(id).append(", dir=")
				.append(dir).append(", priority=").append(Arrays.toString(priority)).append("]");
		return builder.toString();
	}
}

class Smell{
	int id;
	int endTime;
	
	public Smell(int id, int endTime) {
		this.id = id;
		this.endTime = endTime;
	}
}

public class Main {
	static int N, K;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1 , 0, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		ArrayDeque<Shark> que = new ArrayDeque<>();
		HashMap<Integer, Shark> map = new HashMap<>();
		Smell[][] smells = new Smell[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				int id = Integer.parseInt(st.nextToken());
				
				if(id>0) {
					Shark s = new Shark(id, i, j);
					que.add(s);
					map.put(id, s);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=M; i++) {
			int dir = Integer.parseInt(st.nextToken())-1;
			map.get(i).dir = dir;
		}
		
		for (int id=1; id<=M; id++) {
			Shark curShark = map.get(id);
			for (int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<4; j++) {
					curShark.priority[i][j]=Integer.parseInt(st.nextToken())-1;
				}
			}
		}
		
		int second=-1;
		for (int i=1; i<=1000; i++) {
			removeSmell(smells, i);
			que = sharkMove(que, smells, i);
			if(que.size()<2) {
				second=i;
				break;
			}
		}
		System.out.println(second);
	}
	
	public static ArrayDeque<Shark> sharkMove(ArrayDeque<Shark> sharks, Smell[][] smells, int curTime){
		Map<Integer, Shark> map = new HashMap<>();
		while(!sharks.isEmpty()) {
			Shark now = sharks.poll();
			int dir = now.dir;
			smells[now.y][now.x] = new Smell(now.id, curTime+K);
			//1. 아무 냄새가 없는 곳을 찾는다.
			boolean emptySpace = false;
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[now.priority[dir][i]];
				int nx = now.x + dx[now.priority[dir][i]];
				
				if(ny<0 || ny>=N || nx<0 || nx>=N) continue;
				if(smells[ny][nx]!=null) continue;
				
				emptySpace = true;
				now.y = ny;
				now.x = nx;
				now.dir = now.priority[dir][i];
				if(map.containsKey(ny*N+nx)) {
					if (map.get(ny*N+nx).id > now.id) {
						map.put(ny*N+nx, now);
					}
				} else {
					map.put(ny*N+nx, now);
				}
				break;
			}
			
			if(emptySpace) continue;
			
			for (int i=0; i<4; i++) {
				int ny = now.y + dy[now.priority[dir][i]];
				int nx = now.x + dx[now.priority[dir][i]];
				
				if(ny<0 || ny>=N || nx<0 || nx>=N) continue;
				if(smells[ny][nx].id!=now.id) continue;
				
				
				now.y = ny;
				now.x = nx;
				now.dir=now.priority[dir][i];
				if(map.containsKey(ny*N+nx)) {
					if (map.get(ny*N+nx).id > now.id) {
						map.put(ny*N+nx, now);
					}
				} else {
					map.put(ny*N+nx, now);
				}
				break;
			}
		}
		
		for (int id : map.keySet()) {
			Shark s = map.get(id);
			smells[s.y][s.x] = new Smell(s.id, curTime+K);
			sharks.add(s);
		}
 		
		return sharks;
	}
	public static void removeSmell(Smell[][] smells, int time) {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(smells[i][j]==null) continue;
				if(smells[i][j].endTime<=time) {
					smells[i][j]=null;
				}
			}
		}
	}
}

/* 상어는 1이상 M이하의 번호, 모두 번호가 다름
 * N*N 크기의 격자 중 M개의 칸에 상어가 들어있음
 * 자신의 위치에 냄새 뿌리기 -> 상하좌우 중 한 칸으로 이동 -> 이동한 곳에도 냄새뿌리기
 * 냄새는 상어가 K번 이동하고 나면 사라짐
 * 
 * 상어의 이동 방향 결정
 * 1. 우선순위 대로 탐색 하면서 아무 냄새가 없는 칸을 찾기
 * 2. 아무 냄새가 없는 칸이 없다면 우선순위 대로 탐색하면서 자신의 냄새가 있는 칸으로 방향을 잡기
 * 
 * 
*/
