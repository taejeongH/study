class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = 0;
       for (int size : mats) {
             s : for (int i=0; i<=park.length-size; i++) {
                for (int j=0; j<=park[0].length-size; j++) {
                    
                    boolean can = true;
                    nxt : for (int k=i; k<i+size; k++) {
                        for (int n=j; n<j+size; n++){
                            if (!park[k][n].equals("-1")) {
                                can=false;
                                break nxt;
                            }
                        }
                    }
                    if(can){
                        answer=Math.max(size, answer);
                        break s;
                    } 
                }
            }
            
        }
        
        
        return answer==0?-1:answer;
    }
}