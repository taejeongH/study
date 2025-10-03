import java.io.*;
import java.util.*;
class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int ySize = 0;
        
        int[] dy = {0, 0};
        int[] dx = {1, -1};
        if(n%w==0) {
            ySize = n/w;
        }
        int[][] map = new int[n/w+1][w];
        int y = map.length-1;
        int x = 0;
        int dir = 0;
        map[y][x]=1;
        int a = 2;
        while (a<=n) {
            int ny = y+dy[dir];
            int nx = x+dx[dir];
            
            if(nx<0 || nx>=w) {
                dir = (dir+1)%2;
                map[y-1][x]=a;
                y-=1;
                a++;
                continue;
            } else {
                map[ny][nx]=a;
            }
            a++;
            y=ny;
            x=nx;
        }
        // for (int i=0; i<map.length; i++) System.out.println(Arrays.toString(map[i]));
        
        nxt : for (int i=0; i<map.length; i++){
            for (int j=0; j<w; j++) {
                if (map[i][j]==num) {
                    while(i>=0 && map[i][j]!=0) {
                        i--;
                        answer++;
                    }
                    break nxt;
                }
                
            }
        }
        
        return answer;
    }
}