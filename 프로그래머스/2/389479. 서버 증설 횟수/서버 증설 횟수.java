import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
            
        ArrayDeque<int[]> que = new ArrayDeque<>();
        int add = 0;
        for (int i=0; i<players.length; i++) {   
            if (que.size()>0 && que.peek()[0]<=i) {
                add -= que.poll()[1]; 
            }
            
            if (players[i] < (add+1)*m) continue;
            
            int newadd = (players[i]-add*m)/m;
            que.add(new int[] {i+k, newadd});
            add += newadd;
            answer += newadd;
            System.out.println(i + " " + add);
        }
        
        
        
        return answer;
    }
    
}

/*
    같은 시간대에 게임을 이용하는 사람이 m명 늘어날 때마다 서버 1대가 추가로 필요
    증설한 서버는 k 시간 동안만 운영 그 후 반납
    
    
    
*/