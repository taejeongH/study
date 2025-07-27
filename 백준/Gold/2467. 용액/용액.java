import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] map = new int[N];
		int acidCount = 0;
	    int alkalineStart = -1;
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    for (int i = 0; i < N; i++) {
	    	map[i] = Integer.parseInt(st.nextToken());
	    	if(map[i] < 0) acidCount++;
	    	if(alkalineStart == -1 && map[i] >= 0) alkalineStart = i;
	    }
	    
	    if(alkalineStart == -1) {
	    	System.out.println(map[N-2] + " " + map[N-1]);
	    } else if (alkalineStart == 0) {
	    	System.out.println(map[0] + " " + map[1]);
	    } else if (map[alkalineStart] == 0) {
	    	if (Math.abs(map[alkalineStart]+map[alkalineStart-1]) < Math.abs(map[alkalineStart] + map[alkalineStart+1])) {
	    		System.out.println(map[alkalineStart-1] + " " + map[alkalineStart]);
	    	} else {
	    		System.out.println(map[alkalineStart] + " " + map[alkalineStart+1]);
	    	}
	    } else {
	    	int min = Integer.MAX_VALUE;
		    int minAlkaline = 0;
		    int minAcid = 0;
		    int start = 0;
		    int end = N-1;
		    for (int i = 0; i < N; i++) {
		    	int minSum = Integer.MAX_VALUE;
		    	int minM = 0;
		    	int minP = 0;
		    	for (int j = N-1; j > i; j--) {
		    		int sum = Math.abs(map[i] + map[j]);
		    		if(minSum < sum) {
		    			break;
		    		} else {
		    			minSum = sum;
		    			minM = map[i];
		    			minP = map[j];
		    			
		    		}
		    	}
		    	if(minSum < min) {
		    		min = minSum;
		    		minAlkaline = minP;
		    		minAcid = minM;
		    	}
		    	
		    }
		    
		    System.out.println(minAcid + " " + minAlkaline);
	    }
	    
		
	}
	
}