import java.io.*;
import java.util.*;
class Solution {
    public String[] solution(String[] expressions) {
        
        int N = expressions.length;
        
        int[] can = new int[10];
        ArrayList<String> ans = new ArrayList<>();
        for (int i=0; i<N; i++) {
            String s = expressions[i].replace(" ", "");
            String res = s.split("=")[1];
            StringBuilder sb = new StringBuilder();
            String[] nums = null;
            int type = -1;
            for (int j=0; j<s.length(); j++) {
                if (s.charAt(j)=='=') break;
                sb.append(s.charAt(j));
            }
            if (s.contains("+")) {
                //더하기
                nums = sb.toString().split("\\+");
                type = 0;
            } else {
                //빼기
                nums = sb.toString().split("\\-");
                type = 1;
            }
            String num1 = nums[0];
            String num2 = nums[1];
            if (res.equals("X")) {
                ans.add(s);
                for (int j=2; j<=9; j++) {
                    if(change(num1, j)!=-1 && change(num2, j) != -1) can[j]++;
                }
                continue;
            }
            
            for (int j=2; j<=9; j++) {
                int a = change(num1, j);
                int b = change(num2, j);
                if(a==-1 || b==-1) continue;
                if (find(num1, num2, type, j)==change(res, j)) can[j]++;
            }
        }
        
        String[] answer = new String[ans.size()];
        for (int i=0; i<ans.size(); i++) {
            String s = ans.get(i);
            Set<Integer> corr = new HashSet<>();
            
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<s.length(); j++) {
                    if (s.charAt(j)=='=') break;
                    sb.append(s.charAt(j));
            }
            
            String[] nums = null;
            int type = -1;
            if (s.contains("+")) {
                nums = sb.toString().split("\\+");
                type = 0;
            } else {
                //빼기
                nums = sb.toString().split("\\-");
                type = 1;
            }
            String num1 = nums[0];
            String num2 = nums[1];
            for (int j=2; j<=9; j++) {
                if(can[j]!=N) continue;
                int a = change(num1, j);
                int b = change(num2, j);
                if(a==-1 || b==-1) continue;
                int res = find(num1, num2, type, j);
                if(res==-1) continue;
                sb = new StringBuilder();
                while (res!=0 && res!=1){
                    sb.insert(0, res%j);
                    res/=j;
                }
                sb.insert(0, res);

                corr.add(Integer.parseInt(sb.toString()));

            }
            
            String op = type==0?"+":"-";
            if(corr.size()==1) {
                for (int n : corr) {
                    answer[i] = num1 + " " + op + " " + num2 + " = " + n;
                }
            } else {
                answer[i] = num1 + " " + op + " " + num2 + " = ?";
            }
            
        }
        return answer;
    }
    
    public int find(String num1, String num2, int type, int form){
        if (type==0) {
            //더하기
            return change(num1, form) + change(num2, form);
        } else {
            return change(num1, form) - change(num2, form);
        }
        
    }
    
    public int change(String num, int n) {
        int res = 0;
        int idx = num.length()-1;
        for (int i=0; i<num.length(); i++) {
            int a = (int)(num.charAt(idx--)-'0');
            if(a>=n) return -1;
            res += Math.pow(n, i) * a;
        }
        
        return res;
    }
}


/*

    X가 아닌 수식들의 가능한 진법을 확인 -> 가능한 진법이라면.. 그 중 찾기?
*/