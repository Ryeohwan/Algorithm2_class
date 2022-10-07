package 안려환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class P069_SWEA5656_벽돌깨기_bfs {
	private static int N,W,H,min;
	static int[] dc= {-1,1,0,0};
	static int[] dr= {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String [] arr = br.readLine().split(" ");
			N = Integer.parseInt(arr[0]);
			W = Integer.parseInt(arr[1]);
			H = Integer.parseInt(arr[2]);
			int [][] map = new int [H][W];
			for (int i = 0; i < H; i++) {
				arr = br.readLine().split(" ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(arr[j]);
				}
			}  // 입력부 완
			
			min = Integer.MAX_VALUE;
			go(map,0);
			System.out.println("#"+tc+" "+min);
		}
	}
	
	// 구슬 던지기 게임
	static boolean go(int [][] map,int cnt) {  // map 은 직전 구슬까지 던진 map 의 상태
		int result = getRemain(map);
		if(result == 0 ) {
			min = result;
			return true;
		} 
		
		if(cnt == N) {  // 구슬을 다 던진 상태
			// 남은 벽돌 수 카운트 최소값 갱신
			
			if(min> result) min = result;
			return false;///////////
			
		}
		
		// 구슬 던지기 (중복 순열)
		int [][] newMap = new int[H][W];
		for (int i = 0; i < W; i++) {
			// 구슬에 맞는 시작벽돌 찾기
			int r = 0;
			while(r < H &&map[r][i] == 0) ++r; // 빈칸이면 행을 증가해서 가야 한다.
			if(r == H) {  // 맞는 시작 벽돌이 없는 상태
				continue;/////////////
			}else { // 맞는 시작 벽돌이 있는 상태
				/**
				 * 배열을 복사해서 써야 한다.
				 * 원본 배열을 두고 새 배열을 만들어서 쓰거나 한다.
				 * 
				 * **/
				copy(map,newMap);  // 새로운 배열을 만들어서 사용할 것이다. 그래서 boom 할 예정
				// 제거될 벽돌 연쇄 처리
				boom(newMap,r,i,newMap[r][i]);   // bfs, dfs 둘 다 구현이 가능하다.
				// 벽돌 중력 처리
				down(newMap);  // 여기서 map 은 직접 상태의 map 이다.
			    // 다음 구슬 던지기
				if(go(newMap,cnt+1)) return true;/////////////////
			}
		}
		return false;///////////////////////
	}

	private static int getRemain(int[][] map) {
		int result = 0;
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if(map[r][c] > 0) result++;
			}
		}
		return result;
	}

//	private static void down(int[][] map) {
//		for (int c = 0; c < W; c++) {  // 모든 벽돌에 대해서 다 하는 것
//			int r = H - 1;
//			while(r>0) {
//				if(map[r][c] == 0) {  // 빈칸이면 내릴 벽돌 찾기
//					int nr = r - 1;
//					while(nr>0 && map[nr][c] == 0) nr --; // 빈칸이면 위로 올라가야 하낟.
//					
//					map[r][c] = map[nr][c];
//					map[nr][c] = 0; // 내린 벽돌 자리는 0
//				}
//				--r; // 빈칸이 아니면 벽돌이면 내리면 안되니까 올라가야 한다.
//			}
//		}
//	}
	
	static Stack<Integer> stack = new Stack<Integer>();
	
	private static void down(int[][] map) {
	
		for (int c = 0; c < W; c++) {  // 모든 벽돌에 대해서 다 하는 것
			// 윗행부터 남은 벽돌 스택에 넣기
			for (int r = 0; r < H; r++) {
				if(map[r][c]>0) {
					stack.push(map[r][c]);
					map[r][c] = 0;
				}
			}  // 남은 벽돌은 스택에 들어있고 모든 칸은 빈 칸 상태
			int nr = H - 1;
			while(!stack.isEmpty()) {
				map[nr--][c] = stack.pop();
			}
		}
	}

	private static void boom(int[][] newMap, int r, int c, int cnt) {  // DFS 
		
		
		// 벽돌이 있던 자리를 0으로 바꿀 것이다. -> 빈칸으로 만들어서 방문처리하는 것 
		newMap[r][c] = 0;  // 방문처리 -> 제거처리
		if(cnt == 1) return;

			
		// 벽돌의 크기 -1 만큼 주변 벽돌 
		for (int i = 0; i < 4; i++) {
			int nr = r;  // 처음 출발을 현재 벽돌의 위치로 잡아두고 
			int nc = c;
				for (int j = 1; j < cnt; j++) {  // 현재 방향에서 cnt - 1 벽돌 보기
				nr += dr[i];
				nc += dc[i];
					if(nr >= 0 && nr < H && nc >= 0 && nc < W && newMap[nr][nc]>0) {
						boom(newMap,nr,nc,newMap[nr][nc]);
					}
				}
			}
		
	}

	private static void copy(int[][] map, int[][] newMap) {
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				newMap[r][c] = map[r][c];
			}
		}
		
	}
	
	static class Point{
		private int r,c,cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
	}

}
