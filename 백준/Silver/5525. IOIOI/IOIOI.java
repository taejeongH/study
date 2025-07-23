import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main{

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String S = br.readLine();
		
		int nLength = 3 + (N - 1) * 2;
		
		int result = 0;
		int start = 0;
		while(start <= M-1) {
			if(S.charAt(start) == 'I') {
				int wordLength = 1;
				while (start < M-1) {
					char now = S.charAt(start);
					char next = S.charAt(start+1);
					if (start == M-2 && now == 'O' && next == 'I') {
						wordLength += 1;
						break;
					}
					
					if (now == 'I' && next != 'O') {
						break;
					} else if (now == 'O' && next != 'I') {
						wordLength -= 1;
						break;
					}
					
					wordLength++;
					start++;
				}
				
				if (wordLength >= nLength) {
					result += ((wordLength-3)/2+1) - N + 1;
				}
				
				//System.out.println(wordLength);
				
			}
			
			start++;
			
			
		}
		System.out.println(result);
		
	}



}