package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class P080_BJ17472_다리만들기2 {
	
	static int N, M;
	static int[][] map;
	static int[] parent;
	
	public static class Edge implements Comparable<Edge> {
		int v, u, cost;
		public Edge(int v, int u, int cost) {
			this.v = v;
			this.u = u;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
		
	}

	public static void bfs(int y, int x, boolean[][] visited, int num) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		
		map[y][x] = num;
		queue.offer(new int[] {y, x});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			y = cur[0];
			x = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= M || map[ny][nx] != 1) continue;
				
				map[ny][nx] = num;
				queue.offer(new int[] {ny, nx});
				
			}
		}
	}
	public static boolean union(int u, int v) {
		u = find(u);
		v = find(v);
		
		if(u == v) {
			return true;
		} else {
			if(u < v) {
				parent[v] = u;
			} else {
				parent[u] = v;
			}
			return false;
		}
	}
	public static int find(int v) {
		if(v == parent[v]) {
			return v;
		}
		return parent[v] = find(parent[v]);
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] inputs = br.readLine().split(" ");
		
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			inputs = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		/*------------------입력끝-------------------*/
		
		//Step 1 :  BFS를 사용해서 섬들에 숫자를 표시한다 2 ~
		int num = 2;
		boolean[][] visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j, visited, num++);
				}
			}
		}
		
		
		// Step 2 : 우선순위 큐에 만들수 있는 모든 다리의 종류를 넣는다
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0) { // 섬이 있다면 4방향 탐색을 통해 놓을 수 있는 모든 다리를 우선순위 큐에 넣는다
				
					for(int k = 0; k < 4; k++) { // 4방향을 탐색한다 상하좌우
						int cost = 0;
						int y = i;
						int x = j;
						int n = map[i][j];
						
						while(true) {
							int ny = y + dy[k];
							int nx = x + dx[k];
							
							if(ny < 0 || ny >= N || nx < 0 || nx >= M || map[ny][nx] == n) break; // map의 범위를 벗어났거나 자신과 같은 섬에 닿았을 경우 반복을 멈춘다
							
							if(map[ny][nx] != 0 && map[ny][nx] != n) { // 다른 섬을 만났을 때
								if(cost > 1) { // 다리의 길이가 1보다 크다면 -> 우선순위큐에 넣는다
									pq.offer(new Edge(n, map[ny][nx], cost));
								}
								break;
							}
							
							// 다른 섬에 닿지 못하고 아직 바다인 경우 같은 방향으로 계속 다리를 만든다.
							y = ny;
							x = nx;
							cost++;
						}
					}
				}
			}
		}
		
		
		// Step 3: Kruskal MST 알고리즘에서 새로 추가할 다리의 양쪽 섬이 같은 집합에 속해 있는 지 확인한다.
		parent = new int[num + 2]; 
		for(int i = 2; i < num; i++ ) { // 부모노드를 초기화한다
			parent[i] = i;
		}
		
		int result = 0;
		int count = 0;
		while(!pq.isEmpty()) { // 다리 길이가 짧은 순서로 poll을 한다
			Edge edge = pq.poll();
			if(!union(edge.v, edge.u)) { // 다리의 양쪽 섬이 같은 집함에 속하지 앟으면 다리를 추가한다
				result += edge.cost;
				count++;
			}
			if(count == num-1) break; 
		}
        
		for(int i = 3; i < parent.length - 2; i++) { // 모든 섬들이 연결되어있는지 확인한다
			if(!union(2, i)) { 
				result = 0;
				break;
			}
		}

		result = result == 0? -1: result; // 모든 섬이 연결이 안됐다면 -1을 연결됐으면 전체 다리의 길이를 출력한다
		
		/*---------------출력----------------*/
		bw.write(result + "");
		bw.flush();
		br.close();
		bw.close();
	}
}
