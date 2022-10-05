package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class P075_BJ2636_치즈 {

	static int result;
	static ArrayList<Pos> blanks;
	static Queue<Pos> virus;
	static int N, M;

	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

	public static int bfs(int y, int x, int[][] map) {
		
		Queue<Pos> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		int count = 0;
		queue.add(new Pos(y, x));
		
		int[] dy = {-1, 1, 0, 0 };
		int[] dx = {0, 0, -1, 1 };
		
		// BFS로 타고 들어가서 치즈라면 녹이고 공기라면 계속타고 들어간다
		while(!queue.isEmpty()) {
			
			Pos p = queue.poll();
			y = p.y;
			x = p.x;
			
			for(int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				// 범위를 넘어가거나 방문했다면 방문하지 않는다
				if(ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx] ) continue;
				
				if(map[ny][nx] == 1) { // 가장 가장자리에 있는 치즈라면 녹는다
					count++;
					map[ny][nx] = 0;
					visited[ny][nx] = true;
				} else if(map[ny][nx] == 0) { // 공기라면 타고 들어간다
					visited[ny][nx] = true;
					queue.add(new Pos(ny, nx));
				}
			}
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

		int cheese = 0;

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			inputs = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);

				if (map[i][j] == 1) {
					cheese++;
				}

			}
		}
		/*--------------------- 입력끝 ------------------------*/
		// 치즈가 남아 있을때까지 반복한다.
		int result = 0;
		int time = 0;
		while (cheese > 0) {
			result = cheese; // 녹기전 값을 저장한다
			cheese -= bfs(0, 0, map);
			time++;
		}

		/*--------------------- 출력  ------------------------*/
		sb.append(time).append("\n").append(result);
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
