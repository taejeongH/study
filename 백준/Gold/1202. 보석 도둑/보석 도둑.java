//package BOJ.보석도둑;

import java.io.*;
import java.util.*;

public class Main {
	static class Bag implements Comparable<Bag>{
		int m;
		int id;
		public Bag(int m, int id) {
			super();
			this.m = m;
			this.id = id;
		}
		@Override
		public int compareTo(Bag o) {
			if (o.m==this.m) return o.id-this.id;
			return this.m-o.m;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//보석개수
		int M = Integer.parseInt(st.nextToken());//가방개수

		int[][] jewel = new int[N][2];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			jewel[i][0] = Integer.parseInt(st.nextToken());
			jewel[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(jewel, (o1, o2) -> Integer.compare(o2[1], o1[1]));
		TreeSet<Bag> bag = new TreeSet<>();
		for (int i=0; i<M; i++) {
			int m = Integer.parseInt(br.readLine());
			bag.add(new Bag(m, i));
		}
		
		long res = 0;
		Bag comp = new Bag(0, M);
		for (int i=0; i<N; i++) {
			int m = jewel[i][0];
			int v = jewel[i][1];
			if(bag.isEmpty()) break;
			comp.m = m;
			Bag choice = bag.ceiling(comp);
			if (choice == null) continue;
			bag.remove(choice);
			
			res += v;
		}
		
		System.out.println(res);
		
	}
}
