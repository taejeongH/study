class Solution {
    int N;
    int ans;
    public int solution(int n) {
        N = n;
        ans = 0;
        dfs(0, 0, 0);
        return ans;
    }
    
    public void dfs(int depth, int open, int close){
        if (depth == N*2) {
            if(open==close) ans++;
            return;
        }
        
        if (open <= close) {
            dfs(depth+1, open+1, close);
        } else {
            dfs(depth+1, open+1, close);
            dfs(depth+1, open, close+1);
        }
    }
}