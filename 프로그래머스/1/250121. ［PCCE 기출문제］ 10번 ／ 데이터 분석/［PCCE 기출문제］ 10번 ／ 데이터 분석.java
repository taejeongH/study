import java.io.*;
import java.util.*;
class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        Map<String, Integer> map = new HashMap<>();
        map.put("code", 0);
        map.put("date", 1);
        map.put("maximum", 2);
        map.put("remain", 3);
        
        int type = map.get(ext);
        
        
        ArrayList<int[]> res = new ArrayList<>();
        for (int i=0; i<data.length; i++) {
            if (data[i][type] < val_ext){
                res.add(data[i]);
            }
        }
        
        int sortType = map.get(sort_by);
        
        int[][] answer = new int[res.size()][4];
        for (int i=0; i<res.size(); i++) {
            for (int j=0; j<4; j++) {
                answer[i][j] = res.get(i)[j];
            }
        }
        Arrays.sort(answer, (o1, o2) -> Integer.compare(o1[sortType], o2[sortType]));
        return answer;
    }
}