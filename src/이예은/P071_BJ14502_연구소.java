package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class P071_BJ14502_연구소 {

	static int result;
	static ArrayList<Pos> blanks;
	static Queue<Pos> virus;
	static int N, M;
	static int min;

	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}
	
	static public int bfs(int[][] map) {
		Queue<Pos> queue = new ArrayDeque<>(virus);
		boolean[][] visited = new boolean[N][M];
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int count = 0;
		
		while(!queue.isEmpty()) {
			
			Pos p = queue.poll();
			int x = p.x;
			int y = p.y;
			
			for(int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] || map[ny][nx] != 0) continue;
				
				count++;
				visited[ny][nx] = true;
				queue.add(new Pos(ny, nx));
			}
			
			if(count > min) return min;
		}
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		int virusCount = 0;
		int blankCount = 0;

		int[][] map = new int[N][M];
		blanks = new ArrayList<>();
		virus = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			inputs = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);

				if(map[i][j] == 2) {
					virus.add(new Pos(i, j)); //바이러스 배열 저장
					virusCount++;
				} else if(map[i][j] == 0) {
					blanks.add(new Pos(i, j)); //빈칸 배열 저장
					blankCount++;
				}
			}
		}
		/*--------------------- 입력끝 ------------------------*/
		
		int y, x;
		min = Integer.MAX_VALUE;
		
		// 3개의 벽을 고른다
		for(int i = 0; i < blankCount-2; i++) {
			Pos p1 = blanks.get(i);
			map[p1.y][p1.x] = 1;
			
			for(int j = i + 1; j < blankCount-1; j++) {
				Pos p2 = blanks.get(j);
				map[p2.y][p2.x] = 1;
				
				for(int k = j + 1; k < blankCount; k++) {
					
					Pos p3 = blanks.get(k);
					map[p3.y][p3.x] = 1;
	
					int result = bfs(map); // 바이러스 퍼트리기
					min = min > result? result: min; // 가장적게 바이러스가 퍼진 것을 저장한다
					
					map[p3.y][p3.x] = 0;
				}
				map[p2.y][p2.x] = 0;
			}
			map[p1.y][p1.x] = 0;
		}
		/*--------------------- 출력  ------------------------*/
		
		bw.write(blankCount - 3 - min + ""); // 전체 빈칸 - 벽3개 - 최소한으로 퍼진 바이러스  = 최대 세이프존
		bw.flush();
		br.close();
		bw.close();
	}
}
