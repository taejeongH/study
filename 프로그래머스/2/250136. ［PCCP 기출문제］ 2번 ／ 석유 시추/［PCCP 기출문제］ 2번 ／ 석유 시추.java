import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        int N = land.length;
        int M = land[0].length;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        
        ArrayDeque<int[]> que = new ArrayDeque<>();
        boolean[][] visited = new boolean [N][M];
        int[] xSum = new int[M];
        for (int j=0; j<M; j++) {
            
            for (int i=0; i<N; i++) {
                if(land[i][j]==0 || visited[i][j]) continue;
                int sum = 0;
                que.add(new int[] {i, j});
                visited[i][j]=true;
                HashSet<Integer> xPos = new HashSet<>();
                while(!que.isEmpty()) {
                    int[] now = que.poll();
                    int y = now[0];
                    int x = now[1];
                    xPos.add(x);
                    sum++;
                    for (int k=0; k<4; k++) {
                        int ny = y+dy[k];
                        int nx = x+dx[k];
                        if(ny>=0 && ny<N && nx>=0 && nx<M && !visited[ny][nx] && land[ny][nx]==1){
                            que.add(new int[] {ny, nx});
                            visited[ny][nx]=true;
                        }
                    }
                    
                }
                for (int n : xPos) {
                    xSum[n] += sum;
                }
            }
            
        }
        
        for (int n : xSum) {
            answer = Math.max(n, answer);
        }

        return answer;
    }
}