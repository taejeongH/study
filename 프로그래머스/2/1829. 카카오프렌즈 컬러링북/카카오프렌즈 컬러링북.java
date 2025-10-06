import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int[] answer = new int[2];
        
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        
        ArrayDeque<int[]> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (picture[i][j]==0 || visited[i][j]) continue;
                que.add(new int[] {i, j});
                visited[i][j]=true;
                int sum = 0;
                int color = picture[i][j];
                numberOfArea++;
                while(!que.isEmpty()) {
                    int[] now = que.poll();
                    int y = now[0];
                    int x = now[1];
                    sum++;
                    
                    for (int k=0; k<4; k++) {
                        int ny = y+dy[k];
                        int nx = x+dx[k];
                        if(ny>=0 && ny<m && nx>=0 && nx<n && !visited[ny][nx] && picture[ny][nx]==color) {
                            visited[ny][nx]=true;
                            que.add(new int[] {ny, nx});
                        }
                    }
                }
                
                maxSizeOfOneArea = Math.max(sum, maxSizeOfOneArea);
            }
        }
        
        
        
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}

