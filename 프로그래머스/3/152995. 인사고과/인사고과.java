import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        
        int N = scores.length;
        
        int[][] score = new int[N][3];
        for (int i=0; i<N; i++) {
            score[i][0]=i;
            score[i][1]=scores[i][0];
            score[i][2]=scores[i][1];
        }
        
        Arrays.sort(score, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2) {
                if (o1[1]==o2[1]) return o1[2]-o2[2];
                return o2[1]-o1[1];
            }
            
        });
        
        int maxScore = 0;
        int test = 0;
        ArrayList<int[]> sum = new ArrayList<>();
        for (int i=0; i<N; i++) {
            if(test!=score[i][1] && score[i][2]<maxScore) continue;
            
            if(maxScore<score[i][2]) {
                maxScore = score[i][2];
                test = score[i][1];
            } else if (maxScore==score[i][2]) {
                test = Math.min(test, score[i][1]);
            }
            maxScore = Math.max(score[i][2], maxScore);
            sum.add(new int[] {score[i][0], score[i][1]+score[i][2]});
        }
        
        Collections.sort(sum, (o1, o2)->Integer.compare(o2[1], o1[1]));
        
        int answer = -1;
        int rank = 1;
        int conti = 1;
        
        // for (int i=0; i<sum.size(); i++) System.out.println(Arrays.toString(sum.get(i)));
        if(sum.get(0)[0]==0) return 1;
        
        for (int i=1; i<sum.size(); i++) {
            if(sum.get(i)[1]!= sum.get(i-1)[1]) {
                rank += conti;
                conti = 1;
            } else {
                conti++;
            }
            
            if(sum.get(i)[0]==0){
                answer = rank;
                break;
            } 
            
        }
        
        return answer;
    }
}

/*
    근무태도점수와 동료평가점수
    만약 임의의 사원보다 두 점수가 모두 낮은 경우가 한 번이라도 있다면 인센못받음
    
    해당 없는 사원들에 대해서는 두 점수의 합이 높은순으로 인센 차등 지급
    합이 동일한 사원들은 동석차, 동석차의 수만큼 다음 석차를 건너 뜀
    완호의 석차(scroes[0])를 return
    
    
    한 점수를 기준으로 내림 차순 정렬 -> 앞에서 부터 탐색하는데 정렬 안한 점수의 최댓값을 저장하면서 탐색, 만약 최댓값보다 작은 값이 나온다면 제외하기
    
    
*/