import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static ArrayList<Integer>[] map;
	public static int[] parent;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new ArrayList[N+1];
		for (int i = 1; i < N+1; i++) {
			map[i] = new ArrayList<Integer>();
		}
		parent = new int[N+1];
		
		
		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[start].add(end);
			map[end].add(start);
		}
		
		dfs(1);
		
		for (int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}

	}
	
	public static void dfs(int num) {
			for (int j = 0; j < map[num].size(); j++) {
				if (parent[map[num].get(j)] == 0) {
					parent[map[num].get(j)] = num;
					dfs(map[num].get(j));
				}
			}
	}
}
