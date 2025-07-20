import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N];
		HashSet<Integer> set = new HashSet<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			map[i] = num;
			set.add(num);
		}
		
		int[] removeDuplicatesMap = new int[set.size()];
		int i = 0;
		for (int num : set) {
			removeDuplicatesMap[i] = num;
			i++;
		}
		
		Arrays.sort(removeDuplicatesMap);	//중복 제거, 정렬
		
		HashMap<Integer, Integer> hash = new HashMap<>();
		
		for (int j = 0; j <removeDuplicatesMap.length; j++) {
			hash.put(removeDuplicatesMap[j], j);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < map.length; j++) {
			sb.append(hash.get(map[j]) + " ");
		}
		System.out.println(sb.toString());
		
 	}
}