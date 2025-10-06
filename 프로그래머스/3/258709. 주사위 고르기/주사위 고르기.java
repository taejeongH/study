import java.io.*;
import java.util.*;

class Solution {
    int N, win, maxWin;
    int[] aDice, bDice, answer;
    int[][] dice;
    public int[] solution(int[][] dice) {
        this.dice = dice;
        N = dice.length;
        aDice = new int[N/2];
        bDice = new int[N/2];
        answer = new int[N/2];
        choice(0, 0);
        return answer;
    }
    
    public void choice(int depth, int start){
        if (depth == N/2) {
            int idx = 0;
            for (int i=0; i<N; i++) {
                boolean isContain = false;
                for (int j=0; j<N/2; j++) {
                    if (i==aDice[j]) {
                        isContain = true;
                        break;
                    }
                }
                if(!isContain) bDice[idx++] = i;
            }
            
            ArrayList<Integer> aSum = new ArrayList<>();
            ArrayList<Integer> bSum = new ArrayList<>();
            
            dfs(0, aSum, 0, aDice);
            dfs(0, bSum, 0, bDice);
            Collections.sort(bSum);
            
            int win = 0;
            for (int n : aSum) {
                win += lowerBound(n, bSum);
            }
            
            if (win>maxWin) {
                maxWin = win;
                for (int i=0; i<N/2; i++) {
                    answer[i] = aDice[i]+1;
                }
            }
            return;
        }
        
        for (int i=start; i<N; i++) {
            aDice[depth] = i;
            choice(depth+1, i+1);
        }
    }
    
    public void dfs(int depth, ArrayList<Integer> sumArr, int sum, int[] select) {
        if (depth==N/2) {
            sumArr.add(sum);
            return;
        }
        
        for (int i=0; i<6; i++) {
            dfs(depth+1, sumArr, sum+dice[select[depth]][i], select);
        }
    }
    
    public int lowerBound(int key, ArrayList<Integer> arr) {
        int s = 0;
        int e = arr.size()-1;
        
        while (s<=e) {
            int mid = (s+e)/2;
            
            if(arr.get(mid) < key) {
                s = mid+1;
            } else {
                e = mid-1;
            }
        }
        
        return e;
    }
}