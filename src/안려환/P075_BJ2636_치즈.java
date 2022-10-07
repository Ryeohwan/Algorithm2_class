package 안려환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P075_BJ2636_치즈 {
	private static int W,H,cnt = 0, check = 0, time = 0;;
	private static int [][]map;
	private static boolean [][] visited;
	private static int[] dx = {0,0,-1,1};  // 상하좌우
	private static int[] dy = {1,-1,0,0};
	private static Queue <Pos> que = new LinkedList<>();
	
	private static class Pos{
		int x;
		int y;
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}	
	
	// 구멍은 2 으로 둘까
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] arr = br.readLine().split(" ");
		H = Integer.parseInt(arr[0]);
		W = Integer.parseInt(arr[1]);
		map = new int[H][W];
		
		for (int i = 0; i < H; i++) {
			arr = br.readLine().split(" ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(arr[j]);
				if(map[i][j] == 1) cnt ++; 
			}
		}  // 입력부 완
		
		while(cnt > 0) {
			check = cnt;
			check_holl(0,0);
		}
		
		
		System.out.println(time);
		System.out.println(check);
	}
	
	
	
	
	private static void check_holl(int x , int y) {
		visited = new boolean[H][W];
		que.add(new Pos(x,y));
		
		while(!que.isEmpty()) {  // 한 겹 한 시간
			Pos p = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if(nx >= 0 && nx < W && ny >= 0 && ny < H && !visited[ny][nx]) {
					if(map[ny][nx] == 1) {
						visited[ny][nx] = true;
						map[ny][nx] = 0;
						cnt--;
					}else {
						visited[ny][nx] = true;
						que.add(new Pos(nx,ny));
					}
					
				}
			}
			
		}
		time ++ ;
		
		
	}

	
}
