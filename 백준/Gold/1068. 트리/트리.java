//package BOJ.트리;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		List<Integer>[] g = new List[N]; for(int i=0; i<N; i++) g[i] = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if(p==-1) continue;
			g[p].add(i);
		}
		
		int D = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> que = new ArrayDeque<>();
		que.add(D);
		HashSet<Integer> remove = new HashSet<>();
		while(!que.isEmpty()) {
			int now = que.poll();
			remove.add(now);
			for (int nxt : g[now]) {
				que.add(nxt);
			}
		}
		int res = 0;
		for (int i=0; i<N; i++) {
			if(remove.contains(i)) continue;
			
			boolean isEmpty = true;
			for (int nxt : g[i]) {
				if (!remove.contains(nxt)) isEmpty = false;
			}
			if(isEmpty) res++;
		}
		System.out.println(res);
	}
}
