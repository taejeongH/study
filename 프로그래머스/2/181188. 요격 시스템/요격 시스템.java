import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        // 끝나는 지점 기준 오름차순 정렬
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));

        int answer = 0;
        int lastEnd = -1; // 마지막으로 선택된 구간의 끝 위치

        for (int[] t : targets) {
            int start = t[0];
            int end = t[1];

            // 겹치지 않으면 선택
            if (start >= lastEnd) {
                answer++;
                lastEnd = end;
            }
        }
        return answer;
    }
}

/*
    구간이 안 겹치는 개수의 최대 값?
*/