import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {	
	public static int[][] tree;
	public static void main(String[] ars) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new int[N+1][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String parent = st.nextToken();
			String left = st.nextToken();
			String right = st.nextToken();
			
			int index = parent.charAt(0) - 64;
			
			if (!left.equals(".")) {
				tree[index][0] = left.charAt(0) - 64;
			} else {
				tree[index][0] = -1;
			}
			
			if (!right.equals(".")) {
				tree[index][1] = right.charAt(0) - 64;
			} else {
				tree[index][1] = -1;
			}
		}		
		preOrder(1);
		System.out.println();
		inOrder(1);
		System.out.println();
		postOrder(1);
		
 	}
	
	public static void preOrder(int x) {
		if (tree[x][0] == -1 && tree[x][1] == -1) {
			System.out.print((char)(x + 64));
		} else {
			System.out.print((char)(x + 64));
			if (tree[x][0] != -1) preOrder(tree[x][0]);
			if (tree[x][1] != -1) preOrder(tree[x][1]);
		}
	}
	
	public static void inOrder(int x) {
		if (tree[x][0] == -1 && tree[x][1] == -1) {
			System.out.print((char)(x + 64));
		} else {
			if (tree[x][0] != -1) inOrder(tree[x][0]);
			System.out.print((char)(x + 64));
			if (tree[x][1] != -1) inOrder(tree[x][1]);
		}
	}
	
	public static void postOrder(int x) {
		if (tree[x][0] == -1 && tree[x][1] == -1) {
			System.out.print((char)(x + 64));
		} else {
			if (tree[x][0] != -1) postOrder(tree[x][0]);
			if (tree[x][1] != -1) postOrder(tree[x][1]);
			System.out.print((char)(x + 64));
		}
	}
}