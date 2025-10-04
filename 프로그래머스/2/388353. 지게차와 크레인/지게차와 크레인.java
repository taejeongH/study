import java.io.*;
import java.util.*;
class Solution {
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        int N = storage.length;
        int M = storage[0].length();
        int Q = requests.length;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        
        int[][] map = new int[N+2][M+2];
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                map[i+1][j+1] = storage[i].charAt(j)-'A'+1;
            }
        }
        
        ArrayDeque<int[]> que = new ArrayDeque<>();
        for (int i=0; i<Q; i++) {
            String order = requests[i];
            int target = order.charAt(0)-'A'+1;
            if(order.length()==1) {
                que.add(new int[] {0, 0});
                boolean[][] visited = new boolean[N+2][M+2];
                visited[0][0]=true;
                while(!que.isEmpty()) {
                    int[] now = que.poll();
                    int y = now[0];
                    int x = now[1];
                    
                    for (int j=0; j<4; j++) {
                        int ny = y+dy[j];
                        int nx = x+dx[j];
                        
                        if(ny<0 || ny>=N+2 || nx<0 || nx>=M+2 || visited[ny][nx]) continue;
                        if(map[ny][nx]==0) {
                            visited[ny][nx] = true;
                            que.add(new int[] {ny, nx});
                        } else if(map[ny][nx]==target) {
                            map[ny][nx]=-1;
                        }
                    }   
                }
                for (int j=1; j<N+1; j++) {
                    for (int k=1; k<M+1; k++) {
                        if(map[j][k]==-1){
                            map[j][k]=0;
                        }
                    }
                }
                
            } else {
                //모든 알파벳 제거
                for (int j=1; j<N+1; j++) {
                    for (int k=1; k<M+1; k++) {
                        if(map[j][k]==target){
                            map[j][k]=0;
                        }
                    }
                }
            }
        }
        
        
        for (int i=1; i<N+1; i++) {
            for (int j=1; j<M+1; j++) {
                if(map[i][j]==0) continue;
                answer++;
            }
        }
        
        // for (int i=0; i<N+2; i++) System.out.println(Arrays.toString(map[i]));
        
        
        return answer;
    }
}

/*
    y,x 확장, bfs와 for문
*/