import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main {
	
	static int N;
	static ArrayList<Integer>[] connect;
	static ArrayList<Integer>[] child;
	static int[] subtreeCount;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //트리의 정점의 수
		int R = Integer.parseInt(st.nextToken()); //루트의 번호
		int U = Integer.parseInt(st.nextToken()); //쿼리의 수
		
		connect = new ArrayList[N+1];
		child = new ArrayList[N+1];
		
		for (int i = 0; i < N+1; i++) {
			connect[i] = new ArrayList<>();
			child[i] = new ArrayList<>();
		}
		
		subtreeCount = new int[N+1];
		
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());		
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			connect[start].add(end);
			connect[end].add(start);
		}
		
	    makeTree(R, -1);
	    //System.out.println(Arrays.toString(child));
	    
	    countSubtree(R);
	    for (int i = 0; i < U; i++) {
	    	int num = Integer.parseInt(br.readLine());
	    	System.out.println(subtreeCount[num]);
	    }
	    
	    
		
	}
	
	//child노드만 저장
	public static void makeTree(int curNode, int parent) {
		for (int i = 0; i < connect[curNode].size(); i++) {
			if(connect[curNode].get(i) != parent) {
				child[curNode].add(connect[curNode].get(i));
				makeTree(connect[curNode].get(i), curNode);
			}
		}
	}
	
	//모든 노드의 서브트리에 포함된 개수 계산
	public static void countSubtree(int curNode) {
		subtreeCount[curNode] = 1;
		for (int i = 0; i < child[curNode].size(); i++) {
			countSubtree(child[curNode].get(i));
			subtreeCount[curNode] += subtreeCount[child[curNode].get(i)];
		}
	}
	
}