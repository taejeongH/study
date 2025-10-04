class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        
        int N = schedules.length;
        int answer = N;
        
        int[] agree = new int[N];
        for (int i=0; i<N; i++) {
            int hour = schedules[i]/100;
            int minute = schedules[i]%100 + 10;
            
            hour += minute/60;
            minute = minute % 60;
            agree[i] = hour*100+minute;
        }
        
        for (int i=0; i<N; i++) {
            // System.out.println(agree[i]);
            int j=0;
            int s = startday;
            while(j<7) {
                if (s!=6 && s!=7) {
                    if (agree[i] < timelogs[i][j]){
                        
                        answer--;
                        break;
                    }
                }
                
                s = (s+1)%7;
                if (s==0) s=7;
                j++;
            }
            
            
        }
        return answer;
    }
}

/*
    출근희망시각 + 10분까지 어플로 출근, 토요일 일요일은 이벤트에 영향 x
    schedules[] : 직원 n명이 설정한 출근 희망 시각
    timelogs[][] : 일주일동안 출근한 시각
    startday : 시작한 요일 (1 월, ... ,7 일)
*/