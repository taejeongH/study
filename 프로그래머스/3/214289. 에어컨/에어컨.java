import java.io.*;
import java.util.*;
class Solution {
    int N, temperature, a, b, t1, t2;
    int[] onboard;
    int INF = 100000000;
    int[][] dp;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        if (temperature<0 || t1<0) {
            temperature += 20;
            t1 += 20;
            t2 += 20;
        }
        N = onboard.length;
        this.onboard = onboard;
        this.temperature = temperature;
        this.a = a;
        this.b = b;
        this.t1 = t1;
        this.t2 = t2;
        
        
        dp = new int[N][100];
        for (int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        return dfs(0, temperature);
    }
    
    public int dfs(int depth, int temp) {
        if (depth == N) return 0;
        if (onboard[depth]==1 && (temp < t1 || temp > t2)) return INF;
        if(dp[depth][temp]!= -1) return dp[depth][temp];
        int res = INF;
        
        if (temperature > temp) {
            if (temp<99) res = dfs(depth+1, temp+1);
        } else if (temperature < temp) {
            if (temp>0) res = dfs(depth+1, temp-1);
        } else {
            res = dfs(depth+1, temp);
        }
        
        if(temp<99) res = Math.min(dfs(depth+1, temp+1)+a, res);
        if(temp>0) res = Math.min(dfs(depth+1, temp-1)+a, res);
        res = Math.min(dfs(depth+1, temp)+b, res);
        return dp[depth][temp]=res;
    }
    
}


/* 
    쾌적한 실내온도(t1~t2)를 유지할 수 있도록
    현재(0분) 실내온도 = 실외 온도
    
    에어컨을 켜 희망온도를 설정. 에어컨의 전원이 켜져있는 동안 원하는 값으로 변경 가능
    
    실내온도 != 희망온도 -> 1분 뒤 희망온도와 같아지는 방향으로 1도 상승or하강
    실내온도 == 희망온도 -> 실내온도 변화 x
    
    에어컨의 전원을 끄면 -> 실외온도와 같아지는 방향으로 1도 상승 or 하강 (실내온도==실외온도면 실내온도는 변하지 않음)
    
    에어컨의 소비전력은 현재 실내온도에 따라 달라짐. (실내온도!=희망온도 -> 전력 a만큼 소모, 실내온도==희망온도 -> 전력 b만큼 소모, 에어컨 꺼져있을 시 소모 x)
    
    실내온도를 (t1~t2) 사이로 유지하면서, 에어컨 소비 전력을 최소화
    
    실외 온도 temperature
    t1~t2
    a, b
    onboard 탑승 유무
    
    onboard가 1일 때에는 실내 온도가 t1~t2사이여야 함 나머지는 상관 없음
    희망온도를 몇 도로 설정하든, +1, -1, 0임 ->
    
    에어컨을 키거나 or 끄거나, 에어컨을 킨상태라면, 희망온도가 실내 온도보다 높은가 낮은가, 끈 상태라면 실내 온도가 실외 온도보다 높은가 낮은가?
    
*/