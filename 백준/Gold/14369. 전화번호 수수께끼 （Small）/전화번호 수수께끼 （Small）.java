//package BOJ.전화번호수수께끼;

import java.io.*;
import java.util.*;

public class Main {
    static String[] words = {"ZERO", "ONE", "TWO", "THREE", "FOUR", 
                             "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
    
    static char[] unique = {'Z', 'W', 'U', 'X', 'G', 'O', 'H', 'F', 'S', 'I'};
    static int[] digitOrder = {0, 2, 4, 6, 8, 1, 3, 5, 7, 9};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testCase; test++) {
            String s = br.readLine();
            int[] cnt = new int[26];
            for (char c : s.toCharArray()) {
                cnt[c - 'A']++;
            }

            int[] res = new int[10];
            
            // 고유 문자를 순서대로 처리
            for (int i = 0; i < 10; i++) {
                char key = unique[i];
                int num = digitOrder[i];
                int freq = cnt[key - 'A']; // 해당 숫자가 몇 번 등장했는지

                if (freq > 0) {
                    res[num] += freq;
                    for (char ch : words[num].toCharArray()) {
                        cnt[ch - 'A'] -= freq;
                    }
                }
            }

            sb.append("Case #").append(test).append(": ");
            for (int i = 0; i <= 9; i++) {
                for (int j = 0; j < res[i]; j++) sb.append(i);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
