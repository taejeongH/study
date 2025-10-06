import java.io.*;
import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int N = cards.length;
        
        HashSet<Integer> pocket = new HashSet<>();
        for (int i=0; i<N/3; i++){
            pocket.add(cards[i]);
        }
        HashSet<Integer> pool = new HashSet<>();
        int round = 1;
        int idx = N/3;
        int coinSum = 0;
        while (idx<N) {
            boolean find = false;
            pool.add(cards[idx]);
            pool.add(cards[idx+1]);
            
            if (containPair(pocket, N)) {
                find = true;
            } else {
                //pool에서 카드 찾기
                if (coinSum+1 <= coin) {
                    for (int num : pool) {
                        if (pocket.contains(N+1-num)) {
                            //동전 한개 내기
                            find = true;
                            pocket.remove(N+1-num);
                            pool.remove(num);
                            coinSum++;
                            break;
                        }
                    }
                }
                
                if (!find && coinSum+2<=coin) {
                    for (int num : pool) {
                        if (pool.contains(N+1-num)) {
                            find = true;
                            pool.remove(N+1-num);
                            pool.remove(num);
                            coinSum += 2;
                            break;
                        }
                    }
                }
    
            }
            
            if(!find) break;
            round++;
            idx+=2;
        }
        
        
        return round;
    }
    
    public boolean containPair(HashSet<Integer> pocket, int N) {
        for (int num : pocket) {
            if(pocket.contains(N+1 - num)) {
                pocket.remove(num);
                pocket.remove(N+1-num);
                return true;
            }
        }
        return false;
    }

}

/*
    1~n의 카드뭉치, coin개의 동전 
    
    게임 진행
     - 카드 뭉치에서 n/3장을 뽑아 모두 가짐 (n은 6ㅡ이 배수), 교환 가능한 동전 coin개를 가지고 있음
     - 게임은 1라운드부터 시작되며, 각 라운드 시작 시 카드 2장 뽑음. 카드 뭉치에 남은 카드가 없다면 게임 종료. 
        뽑은 카드는 카드 한 장당 동전 하나를 소모해 가지거나, 동전을 소모하지 않고 버릴 수 있음
     - 카드에 적힌 수의 합이 n+1이 되도록 카드 두 장을 내고 다음 라운드로 진행, 카드 두 장을 낼 수 없다면 게임 종료
     
     0. 처음에 n/3장과 coin개의 동전을 가지고 게임 시작
     1. 각 라운드 시작 시 카드를 앞에서 부터 2장 뽑음.
     2. 동전을 소모해 카드를 가지거나, 동전을 소모하지 않고 카드를 버림
     3. 만약 카드에 적힌 수의 합이 n+1이 된다면 내고 다음 라운드 진행
     4. 만약 낼 수 없다면 게임 종료
     
     6 <= n카드의 길이 < 1,000, 카드의 원소 중복 x
     
     도달 가능한 최대 라운드 수를 return
     
     
     
*/