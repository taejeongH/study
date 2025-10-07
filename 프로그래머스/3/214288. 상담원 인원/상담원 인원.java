import java.io.*;
import java.util.*;

class Solution {
    int[][] takeTime;
    int n, k, answer;
    public int solution(int k, int n, int[][] reqs) {
        answer = Integer.MAX_VALUE;
        this.n=n;
        this.k=k;
        List<int[]>[] times = new List[k]; for (int i=0; i<k; i++) times[i] = new ArrayList<>();
        for (int i=0; i<reqs.length; i++) {
            times[reqs[i][2]-1].add(new int[] {reqs[i][0], reqs[i][1]});
        }
        
        takeTime = new int[k][n-k+1];
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i=0; i<k; i++) {
            for (int j=0; j<n-k+1; j++) {
                int size = j+1;
                int time = 0;
                for (int c=0; c<times[i].size(); c++) {
                    if (que.isEmpty() || que.size()<size) {
                        que.add(times[i].get(c)[0]+times[i].get(c)[1]);
                    } else if(que.peek() <= times[i].get(c)[0]) {
                        que.poll();
                        que.add(times[i].get(c)[0]+times[i].get(c)[1]);
                    } else{
                        int endTime = que.poll();
                        time += endTime - times[i].get(c)[0];
                        que.add(endTime+times[i].get(c)[1]);
                    }
                    
                    // System.out.println(time + " " + que.toString());
                }
                while(!que.isEmpty()) que.poll();
                takeTime[i][j]=time;
            }
        }
        
        // for (int i=0; i<k; i++) System.out.println(Arrays.toString(takeTime[i]));
        
        dfs(0, 0, 0);
        
        return answer;
    }
    
    public void dfs(int depth, int mentoSum, int sum) {
        if(mentoSum > n) return;
        if(sum >= answer) return;
        
        if(depth==k) {
            if (mentoSum == n) {
                answer = Math.min(sum, answer);
            } 
            return;
        }
        
        for (int i=0; i<n-k+1; i++) {
            dfs(depth+1, mentoSum+i+1, sum+takeTime[depth][i]);
        }
        
    }
}


/*
    채용 설명회에는 멘토 n명이 있으며, 1~k번으로 분류되는 상담 유형이 있음.
    각 멘토는 k개의 상담 유형 중 하나만 담당 가능
    멘토는 담당하는 유형, 참가자 한 명만 상담 가능, 상담 시간은 참가자가 요청한 시간만큼 걸림
    
    상담 규칙
     - 참가자가 상담 요청을 하면 해당 상담 유형을 담당하는 상담 중이 아닌 멘토와 상담 시작
     - 만약 멘토가 모두 상담 중이라면, 자신의 차례가 올 때 까지 기다림
     - 모든 멘토는 상담이 끝났을 때, 기다리는 참가자가 있으면 바로 상담 시작
     
     참가자의 상담 요청 정보가 주어질 때, 참가자가 기다린 시간의 합이 최소가 되도록 멘토의 유형을 정하려고 함 (단, 각 유형별로 멘토는 하나 이상이어야 함)
     
     
     상담 유형의 개수 k
     멘토의 수 n
     reqs[a, b, c], c유형의 상담, a분에 b분 동안의 상담 요청 
     
     1명 ~ n-k+1명일 때 걸리는 시간을 계산하고, dfs로 순열 구하기?
     20C5? 
     
     1번 유형 : [10, 60], [20, 30], [50, 40], [65, 30] 2명, 5분
     2번 유형 : [60, 30], [70 100] 2명 0분 1명 20분
     3번 유형 : [15, 100], [30, 50] 1명 85분 2명 0분
*/