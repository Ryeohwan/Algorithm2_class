package 이예은;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Node implements Comparable<Node>{
	int cost;
	int x;
	int y;
	
	public Node(int cost, int x, int y) {
		this.cost = cost;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int compareTo(Node other) {
		return this.cost - other.cost;
	}
}

public class P068_SWEA1249_보급로 {
	
	static int[][] graph;
	static int N;
	static final int INF = (int) 1e9;
	
	public static int dijkstra() {
		
		int[] dy = {0, -1, 0, 1};
		int[] dx = {1, 0, -1, 0};
		int[][] result =  new int[N][N];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				result[i][j] = INF;
		result[0][0] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(0, 0, 0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int y = node.y;
			int x = node.x;
			int cost = node.cost;
			
			if(result[y][x] < cost)
				continue;
			
			for(int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if( ny < 0 || ny >= N || nx < 0|| nx >= N )
					continue;
				
				int ncost = cost + graph[ny][nx];
				if(ny == N-1 && nx == N-1)// 가장 적은 비용으로  N-1, N-1을 방문했다면 바로 리턴한다
					return ncost;
				
				if(ncost < result[ny][nx]) { // 방문하려는 곳이 더 적은 비용으로 방문했다면 방문하지 않는다
					result[ny][nx] = ncost;
					//System.out.println(ncost);
					pq.offer(new Node(ncost, nx, ny));
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws Exception 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T;
		T=Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			graph = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				String inputs = br.readLine();
				for(int j = 0; j < N; j ++) {
					graph[i][j] = inputs.charAt(j) - '0';
				}
			}
			
			int result = dijkstra();
			System.out.println("#" + test_case + " " + result);
		}

	}

}
