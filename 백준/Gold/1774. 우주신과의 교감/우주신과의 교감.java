//package BOJ.우주신과의교감;

import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] pos = new int[N+1][2];
		
		for (int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			pos[i][0] =  Integer.parseInt(st.nextToken());
			pos[i][1] =  Integer.parseInt(st.nextToken());
		}
		
		List<Integer>[] g = new List[N+1]; for(int i=1; i<N+1; i++) g[i] = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			g[s].add(e);
			g[e].add(s);
		}
		
		double[] prim = new double[N+1];
		Arrays.fill(prim, Integer.MAX_VALUE);
		boolean[] v = new boolean[N+1];
		PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1[1], o2[1]));
		pq.add(new double[] {1, 0});
		prim[1] = 0;
		double mst = 0;
		while(!pq.isEmpty()) {
			double[] now = pq.poll();
			int node = (int) now[0];
			double dis = now[1];
			
			if(v[node]) continue;
			v[node] = true;
			mst += dis;
			
			for (int nxt : g[node]) {
				if(!v[nxt] && prim[nxt]>0) {
					prim[nxt] = 0;
					pq.add(new double[] {nxt, 0});
				}
			}
			
			int x = pos[node][0];
			int y = pos[node][1];
			for (int i=1; i<N+1; i++) {
				if(i==node || v[i]) continue;
				int nx = pos[i][0];
				int ny = pos[i][1];
				
				double dist = Math.hypot(pos[node][0] - pos[i][0], pos[node][1] - pos[i][1]);
				
				if(prim[i] > dist) {
					prim[i] = dist;
					pq.add(new double[] {i, dist});
				}
			}
		}
//		System.out.println(Arrays.toString(prim));
		System.out.println(String.format("%.2f", mst));
	}
}


/*
	

*/