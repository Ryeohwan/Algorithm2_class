import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class P081_SWEA5643_키순서 {
	private static List <Pos> list = new ArrayList<Pos>();
	private static int[] list2;
	private static int ans,cnt, INF = 99999999;
	private static int [][] distance;
	private static StringBuilder sb = new StringBuilder();
	
	private static class Pos{
		int x,y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());  // 노드의 수
			int M = Integer.parseInt(br.readLine());  // 비교횟수
			distance= new int[N+1][N+1]; // 
			list2 = new int [N+1];
			
			
			for (int i = 0; i < M; i++) {
				String [] arr = br.readLine().split(" ");
				int a = Integer.parseInt(arr[0]);
				int b = Integer.parseInt(arr[1]);
				distance[a][b] = 1;
				list.add(new Pos(a,b));
			}
			
			for (int i = 0; i < N+1; i++) {
				for (int j = 0; j < N+1; j++) {
					if(i == j || distance[i][j] != 1) {
						distance[i][j] = INF;
					}
				}
			}
			
			// k 는 경유하는 노드
			for(int k=1; k<N+1; ++k) {
            	// i는 출발하는 노드
                for(int i=1; i<N+1; ++i) {
                    if(i==k) continue; // 출발지와 경유지가 같다면 다음 출발지
                    // j는 도착 노드
                    for(int j=1; j<N+1; ++j) {
                        if(i==j || k==j) continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
                        if(distance[i][j] > distance[i][k]+distance[k][j]) {
                            distance[i][j] = distance[i][k]+distance[k][j];  // 비용이 더 적다면 갱신
                        }
                    }
                }
            }  // 최단 거리가 저장이 됨.
			
			
			
			for (int i = 1; i < N+1; i++) {
				for (int j = 1; j < N+1; j++) {
					if(distance[j][i] != INF) list2[i] ++;
						
					if(distance[i][j] != INF) list2[i] ++;
				}
			}
			
			for (int i = 0; i < N+1; i++) {
				if(list2[i] == N-1) ans++;
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
			ans = 0;
		}
		System.out.println(sb);
	}

}
