//package SWEA.무선충전5644;

import java.util.*;
import java.io.*;

public class Solution {
	
	static int[] dx = {0, 0, 1, 0, -1};
	static int[] dy = {0, -1, 0, 1, 0};

	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("./src/SWEA/무선충전5644/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test=1; test<=testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			int M = Integer.parseInt(st.nextToken()); //총 이동 시간
			int A = Integer.parseInt(st.nextToken()); //BC의 개수
			
			int[][] people = new int[2][M]; //이동 방향
			int[][] bcs = new int[A][4]; //{위치y, 위치x, 범위, 성능}
			for (int i=0; i<2; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j=0; j<M; j++) {
					people[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				bcs[i][1] = Integer.parseInt(st.nextToken())-1;
				bcs[i][0] = Integer.parseInt(st.nextToken())-1;
				bcs[i][2] = Integer.parseInt(st.nextToken());
				bcs[i][3] = Integer.parseInt(st.nextToken());
			}
			
			int[][][] peoplePos = new int[2][M+1][2];
			peoplePos[0][0][0] = 0;
			peoplePos[0][0][1] = 0;
			peoplePos[1][0][0] = 9;
			peoplePos[1][0][1] = 9;
			for (int i=0; i<2; i++) {
				for (int j=1; j<M+1; j++) {
					peoplePos[i][j][0] = peoplePos[i][j-1][0] + dy[people[i][j-1]];
					peoplePos[i][j][1] = peoplePos[i][j-1][1] + dx[people[i][j-1]];
				}
			}
			
			int result = 0;
			for (int i=0; i<M+1; i++) {
				int aPosY = peoplePos[0][i][0];
				int aPosX = peoplePos[0][i][1];
				int bPosY = peoplePos[1][i][0];
				int bPosX = peoplePos[1][i][1];
				ArrayList<Integer> aConnect = new ArrayList<>();
				ArrayList<Integer> bConnect = new ArrayList<>();
				for (int j=0; j<A; j++) {
					int aDis = Math.abs(bcs[j][0] - aPosY) + Math.abs(bcs[j][1] - aPosX);
					int bDis = Math.abs(bcs[j][0] - bPosY) + Math.abs(bcs[j][1] - bPosX);
					if(aDis <= bcs[j][2]) {
						aConnect.add(j);
					}
					if(bDis <= bcs[j][2]) {
						bConnect.add(j);
					}
				}
				
				
				if (aConnect.size()==0 && bConnect.size()==0) continue;
				
				
				//a 최적 선택 -> b최적 선택 vs b최적 선택 -> a 최적 선택 중 최댓값찾기
				int best = 0;
				
				int maxB = 0;
				int maxBcB = -1;
				for (int j=0; j<bConnect.size(); j++) {
					if (maxB < bcs[bConnect.get(j)][3]) {
						maxB = bcs[bConnect.get(j)][3];
						maxBcB = bConnect.get(j);
					}
				}
				int maxA = 0;
				int maxBcA = -1;
				for (int j=0; j<aConnect.size(); j++) {
					if (aConnect.get(j) == maxBcB) continue;
					if (maxA < bcs[aConnect.get(j)][3]) {
						maxA = bcs[aConnect.get(j)][3];
						maxBcA = aConnect.get(j);
					}
				}
				
				best = Math.max(best, maxB + maxA);
				maxA = 0;
				maxBcA = -1;
				for (int j=0; j<aConnect.size(); j++) {
					if (maxA < bcs[aConnect.get(j)][3]) {
						maxA = bcs[aConnect.get(j)][3];
						maxBcA = aConnect.get(j);
					}
				}
				
				maxB = 0;
				maxBcB = -1;
				for (int j=0; j<bConnect.size(); j++) {
					if (bConnect.get(j) == maxBcA) continue;
					if (maxB < bcs[bConnect.get(j)][3]) {
						maxB = bcs[bConnect.get(j)][3];
						maxBcB = bConnect.get(j);
					}
				}
				best = Math.max(best, maxB + maxA);
				
				
				result += best;
//				System.out.println(i + ": " + maxBcA + " " + maxBcB);
//				System.out.println(i + ": " + maxA + " " + maxB);
			}
			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		
		
	}
		
}
