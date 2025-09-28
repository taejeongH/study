//package BOJ.마법사상어와파이어볼;

import java.io.*;
import java.util.*;

class Fireball implements Comparable<Fireball>{
	int id;
	int y;
	int x;
	int dir;
	int vel;
	int mass;
	
	public Fireball(int id, int y, int x, int dir, int vel, int mass) {
		super();
		this.id = id;
		this.y = y;
		this.x = x;
		this.dir = dir;
		this.vel = vel;
		this.mass = mass;
	}

	@Override
	public int compareTo(Fireball o) {
		if(this.y==o.y) {
			if(this.x==o.x) return this.id - o.id;
			return this.x-o.x;
		}
		return this.y-o.y;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fireball [id=").append(id).append(", y=").append(y).append(", x=").append(x).append(", dir=")
				.append(dir).append(", vel=").append(vel).append(", mass=").append(mass).append("]");
		return builder.toString();
	}
}

public class Main {
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] even = {0, 2, 4, 6};
	static int[] odd = {1, 3, 5, 7};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //격자판 크기
		int M = Integer.parseInt(st.nextToken()); //파이어볼 개수
		int K = Integer.parseInt(st.nextToken()); //이동 횟수
		int id = 0;
		
		ArrayDeque<Fireball> que = new ArrayDeque<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			Fireball f = new Fireball(++id, y, x, d, s, m);
			que.add(f);
		}
		
		PriorityQueue<Fireball> pq = new PriorityQueue<Fireball>();
		for(int i=0; i<K; i++) {
			//파이어볼의 이동
			while(!que.isEmpty()) {
				Fireball now = que.poll();
				
				int y = now.y;
				int x = now.x;
				for (int j=0; j<now.vel; j++) {
					int ny = y + dy[now.dir];
					int nx = x + dx[now.dir];
					
					if(ny<0) ny = N+ny;
					ny %= N;
					if(nx<0) nx = N+nx;
					nx %= N;
					
					y = ny;
					x = nx;
				}
				
				
				now.y = y;
				now.x = x;
				pq.add(now);
			}
			
			//겹치는 처리
			while(!pq.isEmpty()) {
				Fireball now = pq.poll();
				int massSum = now.mass;
				int velSum = now.vel;
				int count = 1;
				boolean isSame = true;
				
				while(!pq.isEmpty() && pq.peek().y==now.y && pq.peek().x==now.x) {
					Fireball same = pq.poll();
					massSum+=same.mass;
					velSum+=same.vel;
					if(now.dir%2 != same.dir%2) {
						isSame = false;
					}
					count++;
				}
				
				if(count==1) {
					que.add(now);
					continue;
				}
				
				int newMass = massSum/5;
				int newVel = velSum/count;
				if(newMass==0) continue;
				
				for (int j=0; j<4; j++) {
					if(isSame) {
						que.add(new Fireball(++id, now.y, now.x, even[j], newVel, newMass));
					} else {
						que.add(new Fireball(++id, now.y, now.x, odd[j], newVel, newMass));
					}
				}
			}
			
			if(que.isEmpty()) break;
		}
		
		int res = 0;
		while(!que.isEmpty()) {
			Fireball now = que.poll();
			res += now.mass;
		}
		System.out.println(res);
	}
	
	
}


/* N*N인 격자에 파이어볼 M개를 발사
 * 파이어볼은 각장 위치에서 이동을 대기
 * i번 파이어볼의 위치는 (r,c) 질량은 m, 방향은 d, 속력은 s
 * 
 * 이동 명령
 * 1. 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동
 *   이동하는 중에는 같은 칸에 여러개 파이어볼이 있을 수 있음
 * 2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어남
 * 	 같은 칸에 있는 파이어볼은 모두 합쳐짐
 * 	 파이어볼은 4개의 파이어볼로 나누어짐
 *   난워진 파이어볼의 질량, 속력, 방향은 다음과 같음
 *   		질량 = L(합쳐진 파이어볼의 질량의 합)/5
 *   		속력 = L(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)
 *   		합쳐진 파이어볼의 방향이 모두 홀수거나 짝수면 방향은 짝수, 그렇지 않으면 방향은 홀수
 *   		질량이 0인 파이어볼 소멸
 *   
 *   K번 이동 후 남아있는 파이어볼 질량의 합을 구하는 문제
 * 
 */