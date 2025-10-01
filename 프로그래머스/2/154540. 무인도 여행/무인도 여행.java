import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(String[] maps) {
        ArrayDeque<int[]> que = new ArrayDeque<>();
        
        int N = maps.length;
        int M = maps[0].length();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        
        int[][] map = new int[N][M];
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (maps[i].charAt(j)=='X') {
                    map[i][j]=0;
                } else {
                    map[i][j] = maps[i].charAt(j) - '0';
                }
            }
        }
        
        boolean[][] visited = new boolean[N][M];
        ArrayList<Integer> res = new ArrayList<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j]!=0 && !visited[i][j]) {
                    int sum = 0;
                    que.add(new int[] {i, j});
                    visited[i][j] = true;
                    while(!que.isEmpty()) {
                        int[] now = que.poll();
                        int y = now[0];
                        int x = now[1];
                        
                        sum+= map[y][x];
                        
                        for (int k=0; k<4; k++) {
                            int ny = y+dy[k];
                            int nx = x+dx[k];
                            if(ny<0 || ny>=N || nx<0 || nx>=M || visited[ny][nx] || map[ny][nx]==0) continue;
                            
                            que.add(new int[] {ny, nx});
                            visited[ny][nx]=true;
                        }
                    }
                    res.add(sum);
                }
            }
        }
        int[] answer = new int[res.size()];
        if(res.isEmpty()) return answer = new int[] {-1};
        
        Collections.sort(res);
        
        
        for (int i=0; i<res.size(); i++) {
            answer[i]=res.get(i);
        }
        return answer;
    }
}