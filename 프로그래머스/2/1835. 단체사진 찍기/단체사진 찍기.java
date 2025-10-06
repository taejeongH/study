import java.io.*;
import java.util.*;

class Solution {
    HashMap<Character, Integer> map = new HashMap<>();
    boolean[] visited;
    int[][] data;
    int answer;
    public int solution(int n, String[] data) {
        map.put('A', 1);
        map.put('C', 2);
        map.put('F', 3);
        map.put('J', 4);
        map.put('M', 5);
        map.put('N', 6);
        map.put('R', 7);
        map.put('T', 8);
        answer = 0;
        int[][] newdata = new int[n][4];
        for (int i=0; i<n; i++) {
            newdata[i][0] = map.get(data[i].charAt(0));
            newdata[i][1] = map.get(data[i].charAt(2));
            newdata[i][2] = data[i].charAt(4)-'0';
            char op = data[i].charAt(3);
            if (op=='=') {
                newdata[i][3] = 0;
            } else if(op=='>') {
                newdata[i][3] = 1;
            } else {
                newdata[i][3] = 2;
            }
            
        }
        this.data = newdata;
        visited = new boolean[9];
        dfs(0, new int[8]);
        return answer;
    }
    
    public void dfs(int depth, int[] select) {
        if (depth==8) {
            for (int i=0; i<data.length; i++) {
                int a = data[i][0];
                int b = data[i][1];
                int c = data[i][2];
                int op = data[i][3];
                int aIdx = 0;
                int bIdx = 0;
                for (int j=0; j<8; j++) {
                    if (select[j]==a) aIdx=j;
                    if (select[j]==b) bIdx=j;
                }

                if (op==0) {
                    if (Math.abs(aIdx-bIdx)!=c+1) return;
                } else if(op==1) {
                    if (Math.abs(aIdx-bIdx) <= c+1) return;
                } else {
                    if (Math.abs(aIdx-bIdx) >= c+1) return;
                }
            }
            answer++;
            return;
        }
        for (int i=1; i<=8; i++) {
            if (!visited[i]) {
                select[depth] = i;
                visited[i] = true;
                dfs(depth+1, select);
                visited[i] = false;
            }
        }
        
    }
}