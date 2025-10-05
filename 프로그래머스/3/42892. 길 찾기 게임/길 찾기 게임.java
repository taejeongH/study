import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int id;
    int y;
    int x;
    Node left;
    Node right;
    
    public Node(int id, int y, int x){
        this.id = id;
        this.y = y;
        this.x = x;
    }
    
    @Override
    public int compareTo(Node o1) {
        if (o1.y == this.y) {
            return this.x-o1.x;
        }
        return o1.y-this.y;
    }
    
    @Override
    public String toString(){
        int leftid = left==null?-1:left.id;
        int rightid = right==null?-1:right.id;
        return "parent: " + id + ", left : " + leftid + ", right : " + rightid +"\n";
    }
}



class Solution {
    public int[][] solution(int[][] nodeinfo) {
        
        int N = nodeinfo.length;
        
        Node[] nodes = new Node[N];
        for (int i=0; i<N; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            
            nodes[i] = new Node(i+1, y, x);
        }
        
        Arrays.sort(nodes);
        Node root = nodes[0];
        for(int i=1; i<N; i++) {
            insert(root, nodes[i]);
        }
        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> post = new ArrayList<>();
        preOrder(root, pre);
        postOrder(root, post);

        int[][] answer = new int[2][N];
        for (int i=0; i<N; i++) {
            answer[0][i] = pre.get(i);
        }
        
        for (int i=0; i<N; i++) {
            answer[1][i] = post.get(i);
        }
        
        return answer;
    }
    
    void insert(Node parent, Node child){
        if (child.x < parent.x) {
            if (parent.left == null) parent.left = child;
            else insert(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else insert(parent.right, child);
        }
    }
    
    void preOrder(Node node, ArrayList<Integer> pre) {
        pre.add(node.id);
        if(node.left!=null) preOrder(node.left, pre);
        if(node.right!=null) preOrder(node.right, pre);
        
    }
    
    void postOrder(Node node, ArrayList<Integer> post) {
        if(node.left!=null) postOrder(node.left, post);
        if(node.right!=null) postOrder(node.right, post);
        post.add(node.id);
    }
}

/*
    카카오 프렌즈를 두 팀으로 나누고, 각 팀이 같은 곳을 다른 순서로 방문하도록 해서 먼저 순회를 마친 팀이 승리하는 게임
    
    트리 구성 y좌표로 내림차순 -> x 오름차순 정렬 하면 트리 형태를 가진 배열을 만들 수 있는데,
    [7, 4, 3, 6, 1, 0, 3, 0, 9, 0, 8, ]
    
*/