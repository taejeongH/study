import java.util.*;

class Solution {
    int[] dx = {0, -1, 1, 0}; // d, l, r, u
    int[] dy = {1, 0, 0, -1};
    char[] word = {'d', 'l', 'r', 'u'};
    int N, M, K, endY, endX;
    String answer = "";
    boolean found = false;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n; M = m; K = k;
        endX = c - 1;
        endY = r - 1;

        int dist = Math.abs(x - r) + Math.abs(y - c);
        if ((k - dist) % 2 == 1 || dist > k) return "impossible";

        dfs(x - 1, y - 1, 0, new StringBuilder());
        return found ? answer : "impossible";
    }

    void dfs(int y, int x, int depth, StringBuilder sb) {
        if (found) return;
        int remain = K - depth;
        int dist = Math.abs(y - endY) + Math.abs(x - endX);
        if (dist > remain) return; // 남은 거리로 도달 불가능
        if ((remain - dist) % 2 == 1) return; // 짝/홀 불일치

        if (depth == K) {
            if (y == endY && x == endX) {
                answer = sb.toString();
                found = true;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
            sb.append(word[i]);
            dfs(ny, nx, depth + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
