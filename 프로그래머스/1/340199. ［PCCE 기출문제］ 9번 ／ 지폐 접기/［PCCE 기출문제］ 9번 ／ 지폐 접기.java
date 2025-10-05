class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        
        while (true)  {
            if((bill[0] <= wallet[0] && bill[1]<=wallet[1]) ||
              (bill[1] <= wallet[0] && bill[0]<=wallet[1]))  {
                break;
            }
            
            if (bill[0]<bill[1]){
                bill[1] /= 2;
            } else {
                bill[0] /= 2;
            }
            
            // System.out.println(bill[0] + " " + bill[1]);
            answer++;
            
        }       
        
        
        return answer;
    }
}

/*
    지폐를 접을 때는 항상 길이가 긴쪽을 반 접음
    
*/