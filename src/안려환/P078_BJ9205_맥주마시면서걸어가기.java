package 수업과제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P078_BJ9205_맥주마시면서걸어가기 {
	
	static final int INF = 9999999;
    static int N,distance[][],T;
    private static List<Pos> list ;
    private static class Pos{
    	int x , y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
    	
    }
    
    public static void main(String[] args) throws Exception{

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {  // test case 넣어준다.
        	N = Integer.parseInt(br.readLine());
        	distance = new int[N+2][N+2];
        	list = new ArrayList<>();
        	
        	for(int i=0; i<N+2; i++) {
                String [] a = br.readLine().split(" ");
                int x = Integer.parseInt(a[0]);
                int y = Integer.parseInt(a[1]);
                list.add(new Pos(x,y));
            }
        	
            for(int i=0; i<N+2; ++i) {
                for(int j=0; j<N+2; ++j) {
                    if(i != j && distance[i][j]==0) { //자기자신으로의 인접 정보가 아니고 인접해있지 않다면 INF로 채우기
                        distance[i][j]=INF;
                    }
                    
                    Pos current = list.get(i);
                    Pos next = list.get(j);
                    
                    int dist = Math.abs(current.x-next.x)+Math.abs(current.y-next.y);
                    if(dist <= 1000) distance[i][j]= 1;
                }
            }
            
            // 출발지-->경유지-->목적지로 3중 루프 돌리면 오답
            // 경유지-->출발지-->목적지로 3중 루프 돌려야 정답
            // k 는 경유하는 노드
            for(int k=0; k<N+2; ++k) {
            	// i는 출발하는 노드
                for(int i=0; i<N+2; ++i) {
                    if(i==k) continue; // 출발지와 경유지가 같다면 다음 출발지
                    // j는 도착 노드
                    for(int j=0; j<N+2; ++j) {
                        if(i==j || k==j) continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
                        if(distance[i][j] > distance[i][k]+distance[k][j]) {
                            distance[i][j] = distance[i][k]+distance[k][j];  // 비용이 더 적다면 갱신
                        }
                    }
                }
            }
            
            if(0<distance[0][N+1] && distance[0][N+1]<999999)
                System.out.println("happy");
            else
                System.out.println("sad");
            
		}
        
    
    }


}
