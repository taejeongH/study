import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String l = br.readLine();
		String s = br.readLine();
		
		if (l.length()<s.length()) {
			String tmp = l;
			l = s;
			s = tmp;
		}
		
		StringBuilder ls = new StringBuilder();
		for (int i=0; i<s.length(); i++) {
			ls.append(l);
		}
		
		StringBuilder ss = new StringBuilder();
		for (int i=0; i<l.length(); i++) {
			ss.append(s);
		}
		
		if(ls.toString().equals(ss.toString())) System.out.println(1);
		else System.out.println(0);
		
	}
}

