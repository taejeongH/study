//package SWEA.보호필름2112;

import java.util.*;
import java.io.*;

public class Solution {
	
	static int D;
	static int W;
	static int K;
	static int result;
	static int[][] map;
	static int[] selected;
	static int[] typeSelected;
	
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./src/SWEA/보호필름2112/sample_input (2).txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			
			D = Integer.parseInt(st.nextToken()); //y
			W = Integer.parseInt(st.nextToken()); //x
			K = Integer.parseInt(st.nextToken()); //합격기준(길이)
			
			map = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j <W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0;
			
			selected = new int[D];
			if (!performanceTest(map)) {
				for (int i = 1; i <= D; i++) {
					typeSelected = new int[i];
					bt(0, -1, i);
					if (result != 0) {
						break;
					}
				}
			} 
			System.out.println("#" + test + " " + result);
		}
	}
	
	public static void bt (int num, int start, int max) {
		if (num == max) {
//			System.out.println(Arrays.toString(selected));
			if(injectDrug()) {
				result = max;
			}
			
		} else {
			for (int i = start+1; i < D; i++) {
				for (int j = 1; j <= 2; j++) {
					selected[i] = j;
					bt(num+1, i, max);
					selected[i] = 0;
					if(result!=0) return;
				}
			}
		}
	}

	//type 약품을 투입
	public static boolean injectDrug() {
		int[][] newMap = new int[D][W];
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		} 
		
		//약품 투입
		for (int i = 0; i < D; i++) {
			if (selected[i] != 0) {
				for (int j = 0; j < W; j++) {
					newMap[i][j] = selected[i]-1;
				}
			}
		}
		
		return performanceTest(newMap);
	}
	
	
	//길이가 K만큼 되는지 확인
	public static boolean performanceTest(int[][] map) {
		int sum = 0;
		for (int x = 0; x < W; x++) {
			int preNum = map[0][x];
			int length = 1;
			boolean cant = true;
			for (int y = 1; y < D; y++) {
				if(preNum == map[y][x]) {
					length++;
					if (length == K) {
						cant = false;
						sum++;
						break;
					}
				} else {
					length = 1;
					preNum = map[y][x];
				}
			}
			if(cant) return false;
		}
		
		return sum==W;
	}
}
