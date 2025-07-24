import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main{
	public static boolean[] visited;
	public static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int partyCount = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		map = new int[N+1][N+1];
		
		Queue<Integer> que = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		int knownCount = Integer.parseInt(st.nextToken());
		int[] knownPeople = new int[knownCount];
		for (int i = 0; i < knownCount; i++) {
			int num = Integer.parseInt(st.nextToken());
			que.add(num);
			visited[num] = true;
		}
		
		ArrayList<Integer>[] partyArr = new ArrayList[partyCount];

		for (int i = 0; i < partyCount; i++) {
			st = new StringTokenizer(br.readLine());
			int inPartyCount = Integer.parseInt(st.nextToken());
			partyArr[i] = new ArrayList<>();
			for (int j = 0; j < inPartyCount; j++) {
				partyArr[i].add(Integer.parseInt(st.nextToken()));
			}
			
			for (int j = 0; j < inPartyCount; j++) {
				for (int k = 0; k < inPartyCount; k++) {
					map[partyArr[i].get(j)][partyArr[i].get(k)] = 1;
				}
			}
		}
		
		while(!que.isEmpty()) {
			int now = que.poll();
			
			for (int j = 1; j <= N; j++) {
				if (map[now][j] == 1 && !visited[j]) {
					visited[j] = true;
					que.add(j);
				}
			}
		}
		
		int result = partyCount;
		for (int i = 0; i < partyCount; i++) {
			for (int j = 0; j < partyArr[i].size(); j++) {
				if (visited[partyArr[i].get(j)]) {
					result -= 1;
					break;
				}
			}
		}
		
		System.out.println(result);
		
		
	}
	
	
}