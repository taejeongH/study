class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int N = diffs.length;
        long s = 1;
        long e = limit;
        while (s<=e) {
            long level = (s+e)/2;
            
            long time = 0;
            for (int i=0; i<N; i++) {
                if(diffs[i] <= level) {
                    time += times[i];
                } else {
                    time += (times[i]+times[i-1])*(diffs[i]-level) + times[i];
                }
                
                if(time > limit) break;
            }
            
            if(time <= limit){
                e = level-1;
            } else {
                s = level+1;
            }
        }
      
        return (int) e+1;
    }
}

/*
    n개의 퍼즐을 시간내에 풀어야 함
    diff : 현재 퍼즐의 난이도
    time_cur : 현재 퍼즐의 소요 시간
    time_prev : 이전 퍼즐의 소요 시간
    level : 숙련도
    limit : 제한 시간
    
    diff <= level이면 퍼즐을 틀리지 않고 time_cur만큼의 시간을 사용
    
    diff > level이면 퍼즐을 총 diff-level번 틀림.
    틀릴 때 마다 time_cur만큼의 시간을 사용, 추가로 time_prev만큼의 시간을 사용해 이전 퍼즐을 다시 풀어야함
    
    
    제한시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값 return
*/