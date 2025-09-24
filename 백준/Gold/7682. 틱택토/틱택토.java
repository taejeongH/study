//package BOJ.틱택토;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[][] map = new char[3][3];
		while(true) {
			String input = br.readLine();
			if("end".equals(input)) break;
			
			int idx = 0;
			int xCount = 0;
			int oCount = 0;
			int dotCount = 0;
			for (int i=0; i<3; i++) {
				for (int j=0; j<3; j++) {
					map[i][j]=input.charAt(idx++);
					if(map[i][j]=='O') oCount++;
					else if(map[i][j]=='X') xCount++;
					else dotCount++;
				}
			}
			
			if(xCount-oCount>1 || xCount-oCount<0) {
				System.out.println("invalid");
			} else {
				int rowconti = 0;
				int colconti = 0;
				int crossconti = 0;
				int oconti = 0;
				int xconti = 0;
				for (int i=0; i<3; i++) {
					if(map[i][0] != '.' && map[i][0]==map[i][1] && map[i][1]==map[i][2]) {
						if(map[i][0]=='O') oconti++;
						else xconti++;
						rowconti++;
					}
				}
				
				for (int i=0; i<3; i++) {
					if(map[0][i] != '.' &&map[0][i]==map[1][i] && map[1][i]==map[2][i]) {
						if(map[0][i]=='O') oconti++;
						else xconti++;
						colconti++;
					}
				}
				
				if(map[0][0] != '.' && map[0][0]==map[1][1] && map[1][1]==map[2][2]) {
					crossconti++;
					if(map[0][0]=='O') oconti++;
					else xconti++;
				}
				if(map[0][2] != '.' && map[0][2]==map[1][1] && map[1][1]==map[2][0]) {
					crossconti++;
					if(map[0][2]=='O') oconti++;
					else xconti++;
				}
				
				if(rowconti>1 || colconti>1 || oconti>1 || (xconti!=0 && oconti!=0)) {
					System.out.println("invalid");
				} else if (rowconti+colconti+crossconti==0 && dotCount!=0) {
					System.out.println("invalid");
				} else if (dotCount!=0 && rowconti+colconti+crossconti==1) {
					if((oconti==1 && oCount<xCount) || (xconti==1 && xCount==oCount)) {
						System.out.println("invalid");
					} else {
						System.out.println("valid");
					}
				} else if (dotCount==0 && rowconti+colconti+crossconti==1) {
					if((oconti==1 && oCount<xCount) || (xconti==1 && xCount==oCount)) {
						System.out.println("invalid");
					} else {
						System.out.println("valid");
					}
				} else {
					System.out.println("valid");
				}
			}
//			for (int i=0; i<3; i++) System.out.println(Arrays.toString(map[i]));
//			System.out.println();
		}
	}
}

/* X개수가 O보다 많거나 같아야 함
 * .이 있는 상태라면 누군가는 이겼어야 함 O or X가 일렬로
 * O or X가 2줄 이상 이어져 있다면 겹쳐야 함
 * 
 */
