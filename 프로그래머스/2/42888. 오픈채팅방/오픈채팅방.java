import java.io.*;
import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        Map<String, String> map = new HashMap<>();
        ArrayList<String[]> orders = new ArrayList<>();
        for (int i=0; i<record.length; i++) {
            String[] recordSplit = record[i].split(" ");
            String order = recordSplit[0];
            String id = recordSplit[1];
            if (!order.equals("Leave")) {
                String nickname = recordSplit[2];
            
                map.put(id, nickname);
            }
            
            
            if(order.equals("Change")) continue;
            orders.add(new String[] {order, id});
        }
        // System.out.println(orders.toString());
        String[] answer = new String[orders.size()];
        
        for (int i=0; i<answer.length; i++){
            String order = orders.get(i)[0];
            String id = orders.get(i)[1];
            
            String nickname = map.get(id);
            if(order.equals("Enter")) {
                answer[i] = nickname + "님이 들어왔습니다.";
            } else {
                answer[i] = nickname + "님이 나갔습니다.";
            }
        }
        
        
        return answer;
    }
}

