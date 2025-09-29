//package BOJ.마법사상어와토네이도;

import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0}; // 좌, 하, 우, 상 (x 변화)
    static int[] dy = {0, 1, 0, -1}; // 좌, 하, 우, 상 (y 변화)

    static int[][] left = {
        {-1, 0, 7}, {1, 0, 7}, {-2, 0, 2}, {2, 0, 2},
        {-1, 1, 1}, {1, 1, 1}, {-1, -1, 10}, {1, -1, 10}, {0, -2, 5}
    };

    // <-- 수정된 rotate: (dy, dx) -> (-dx, dy)
    static int[][] rotate(int[][] base) {
        int[][] rotated = new int[base.length][3];
        for (int i = 0; i < base.length; i++) {
            int by = base[i][0];
            int bx = base[i][1];
            int per = base[i][2];
            rotated[i][0] = -bx; // new dy = -dx
            rotated[i][1] = by;  // new dx = dy
            rotated[i][2] = per;
        }
        return rotated;
    }

    static int[][] down = rotate(left);
    static int[][] right = rotate(down);
    static int[][] up = rotate(right);
    static int[][][] patterns = { left, down, right, up };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int y = N/2;
        int x = N/2;
        int dir = 0;
        int count = 0;   // 두 번 지나치면 step 증가
        int conti = 0;   // 현재 방향에서 이동한 칸 수
        int stan = 1;    // 현재 이동할 칸 수
        int res = 0;     // 격자 밖으로 나간 모래

        while (!(y==0 && x==0)) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if (map[ny][nx] == 0) {
                y = ny; x = nx;
                if (++conti == stan) {
                    dir = (dir+1)%4;
                    conti = 0;
                    if (++count == 2) {
                        count = 0;
                        stan++;
                    }
                }
                continue;
            }

            int org = map[ny][nx];
            int sum = 0;
            int[][] cur = patterns[dir];

            for (int i=0; i<cur.length; i++) {
                int nxty = ny + cur[i][0];
                int nxtx = nx + cur[i][1];
                int per = cur[i][2];

                int sand = (org * per) / 100; // 반드시 곱하고 나누기
                if (nxty < 0 || nxty >= N || nxtx < 0 || nxtx >= N) {
                    res += sand;
                } else {
                    map[nxty][nxtx] += sand;
                }
                sum += sand;
            }

            int alphaY = ny + dy[dir];
            int alphaX = nx + dx[dir];
            int remain = org - sum;
            if (alphaY < 0 || alphaY >= N || alphaX < 0 || alphaX >= N) {
                res += remain;
            } else {
                map[alphaY][alphaX] += remain;
            }

            map[ny][nx] = 0; // 흩날리고 난 칸 비우기

            y = ny; x = nx;
            if (++conti == stan) {
                dir = (dir+1)%4;
                conti = 0;
                if (++count == 2) {
                    count = 0;
                    stan++;
                }
            }
        }

        System.out.println(res);
    }
}
