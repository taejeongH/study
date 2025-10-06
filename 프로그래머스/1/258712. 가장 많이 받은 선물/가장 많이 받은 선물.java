import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int N = friends.length;
        int M = gifts.length;
        Map<String, Integer> map = new HashMap<>();
        for (int i=0;i <N; i++) {
            map.put(friends[i], i);
        }
        

        int[][] gift = new int[N][N];
        int[][] giftValue = new int[N][2]; //준횟수, 받은 횟수
        for (int i=0; i<M; i++) {
            String[] s = gifts[i].split(" ");
            gift[map.get(s[0])][map.get(s[1])]++;
            giftValue[map.get(s[0])][0]++;
            giftValue[map.get(s[1])][1]++;
        }
        
        int[] answer = new int[N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (gift[i][j]==gift[j][i]) {
                    int toval = giftValue[i][0] - giftValue[i][1];
                    int fromval = giftValue[j][0] - giftValue[j][1];
                    if(toval > fromval) {
                        answer[i]++;
                    } else if(toval < fromval) {
                        answer[j]++;
                    }
                } else if (gift[i][j]>gift[j][i]){
                    answer[i]++;
                } else {
                    answer[j]++;
                }
            }
        }
        
        int max = 0;
        for (int i=0; i<N; i++) {
            max = Math.max(answer[i], max);
        }
        
        
        // for (int i=0; i<N; i++) System.out.println(Arrays.toString(gift[i]));
        // for (int i=0; i<N; i++) System.out.println(Arrays.toString(giftValue[i]));
        
        
        
        return max/2;
    }
}



/*
    선물 지수 : 준 선물 - 받은 선물
*/