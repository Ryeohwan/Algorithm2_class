package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class P067_BJ1600_말이되고픈원숭이 {
	
	static boolean[][][] visited;
	static int[][] map;
	static int result;
	static int H, W, K;
	
	static class Pos {
		int y, x, k, total;

		public Pos(int y, int x, int k, int total) {
			this.y = y;
			this.x = x;
			this.k = k;
			this.total = total;
		}
	}
	
	public static int bfs(int x, int y, int k) {
		
		int[] hdx = {-2, -2, -1, -1, 1, 1, 2, 2}; //말로 이동할수 있는 경우
		int[] hdy = {-1, 1, -2, 2, -2, 2, -1, 1};
		int[] dx = {0, 1, 0 ,-1}; // 원숭이이로 이동할 수 있는 경우
		int[] dy = {1, 0, -1, 0};
		
		ArrayDeque<Pos> queue = new ArrayDeque<Pos>();
		queue.offer(new Pos(y, x, 0, k));
		visited[x][y][k] = true;
		
		while(!queue.isEmpty()) {
			Pos p = queue.poll();
			
			y = p.y;
			x = p.x;
			k = p.k;
			int total = p.total;
			
			if(y == H-1 && x == W-1) return p.total; // BFS 이기 때문에 가장 먼저 도착했을때가 가장 작은 비용을 가진다
			
			for(int i = 0; i < 4; i++) { // 원숭이로 갈 수 있다면 간다!
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if(ny < 0 || ny >= H || nx < 0 || nx >= W || map[ny][nx] == 1|| visited[ny][nx][k]) continue; // 범위를 벗어나거나 벽이 있거나 방문한 적이 있다면 방문하지 않는다
				visited[ny][nx][k] = true;
				queue.add(new Pos(ny, nx, k, total + 1));
			}
			
			if(k < K) { // 말로 갈 수 있다면
				for(int i = 0; i < 8; i++) { 
					int ny = y + hdy[i];
					int nx = x + hdx[i];
					
					if(ny < 0 || ny >= H || nx < 0 || nx >= W || map[ny][nx] == 1|| visited[ny][nx][k+1]) continue;// 범위를 벗어나거나 벽이 있거나 방문한 적이 있다면 방문하지 않는다
					visited[ny][nx][k+1] = true;
					queue.add(new Pos(ny, nx, k + 1, total + 1));
				}
				
			}
		}
		return -1;
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		K = Integer.parseInt(br.readLine());
		String[] inputs = br.readLine().split(" ");
		
		W = Integer.parseInt(inputs[0]);
		H = Integer.parseInt(inputs[1]);
		
		map = new int[H][W];
		visited = new boolean[H][W][K + 1]; // 3차원 방문체크를 해서 말이 된 횟수에 따라 모두 체크판다
		
		for(int i = 0; i < H; i++) {
			inputs = br.readLine().split(" ");
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		int result = bfs(0, 0, 0);


		bw.write(result + "");
		br.close();
		bw.close();
	}
}
