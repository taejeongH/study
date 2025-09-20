//package BOJ.네트워크연결;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<int[]>[] g = new List[N+1]; for (int i=1; i<N+1; i++) g[i] = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			g[s].add(new int[] {e, c});
			g[e].add(new int[] {s, c});
		}
		
		
		int[] prim = new int[N+1];
		Arrays.fill(prim, Integer.MAX_VALUE);
		prim[1]=0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1[1], o2[1]));
		pq.add(new int[] {1, 0});
		
		int cnt=0, mst = 0;
		boolean[] v = new boolean[N+1];
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			int node = now[0];
			int dist = now[1];
			
			if(v[node]) continue;
			v[node]=true;
			mst += dist;
			if(cnt++ == N) break;
			
			for (int[] jc : g[node]) {
				if(!v[jc[0]] && prim[jc[0]]>jc[1]) {
					prim[jc[0]]=jc[1];
					pq.add(new int[] {jc[0], jc[1]});
				}
			}
			
		}
		System.out.println(mst);
	}
	
	
}
