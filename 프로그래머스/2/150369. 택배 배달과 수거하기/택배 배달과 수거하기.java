import java.io.*;
import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int N = deliveries.length;
        int delIdx = N-1;
        int pickIdx = N-1;
        while(true) {
            //뒤에서 부터 수거
            if (delIdx<0 && pickIdx<0) break;
            
            int delCan = cap;
            int pickCan = cap;
            int startMax = -1;
            while(delIdx>=0 && delCan>0) {              
                if(deliveries[delIdx]!=0){
                    startMax = Math.max(startMax, delIdx);
                    if (deliveries[delIdx]<delCan) {
                        delCan -= deliveries[delIdx];
                        deliveries[delIdx] = 0;
                    } else {
                        deliveries[delIdx] -= delCan;
                        delCan = 0;
                        break;
                    }
                }
                delIdx--;
            }
            
            while(pickIdx>=0 && pickCan>0) {
               if (pickups[pickIdx]!=0){
                   startMax = Math.max(startMax, pickIdx);
                    if (pickups[pickIdx]<pickCan) {
                        pickCan -= pickups[pickIdx];
                        pickups[pickIdx]=0;
                    } else {
                        pickups[pickIdx] -= pickCan;
                        pickCan = 0;
                        break;
                    }
               }
                pickIdx--;
            }
            
            // System.out.println(startMax);
            // System.out.println(Arrays.toString(deliveries));
            // System.out.println(Arrays.toString(pickups));
            if(startMax!=-1) answer += (startMax+1)*2;
        }
        return answer;
    }
}

/* 일렬로 나열된 n개의 집에 택배를 배달, 재활용 택배 상자에 담아 배달, 배달을 다니면서 빈 재활용 택배 상자들을 수거
*  i번째 집은 물류창고에서 거리i 만큼 떨어져 있음
*  i번째 집은 j번째 집과 j-i만큼 떨어져 있음
*  트럭에는 택배 상자를 cap개 실을 수 있음
*  택배 상자들을 실어 물류창고에서 출발 -> 각 집에 배달하면서 빈 재활용 택배 상자들을 수거해 물류창고에 내림
*  
*  cap = 들고 다닐 수 있는 최대 개수
*  n = 집의 개수
*  
*
*
*/