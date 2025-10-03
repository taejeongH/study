import java.io.*;
import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        Comparator<String> comp = (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2); // 길이 같으면 사전순
            }
            return o1.length() - o2.length(); // 길이 비교
        };
        
        Arrays.sort(bans, comp);

        String target = findWord(n);
        int idx = 0;
        while (true) {
            int newidx = idx;
            for (int i=idx; i<bans.length; i++) {
                if (comp.compare(bans[i], target)>0) {
                    break;
                }
                n++;
                newidx++;
            }
            if(idx==newidx) break;
            idx=newidx;
            target = findWord(n);
        }

        return findWord(n);
    }
    public String findWord(long num) {
        StringBuilder sb = new StringBuilder();
        
        while(num>0) {
            num--;
            sb.insert(0, (char) ('a' + (num % 26)));
            num /= 26;
        }
        
        return sb.toString();
    }
}

/*
    각 주문은 알파벳 소문자 11글자 이하로 구성
    a~z -> aa~zz -> aaa~aab ... 로 구성된 주문서에서 bans를 삭제시켰다고 했을 때, n번째 문자를 찾기
    
    n보다 앞에 있는 문자열이 몇 개 삭제되었는지 찾고,
    
    
    z -> 26
    aa -> 2
    
    
    
*/