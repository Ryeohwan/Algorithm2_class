package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class P081_SWEA5643_키순서 {
	
	static int N, M;
	static ArrayList<ArrayList<Integer>> list;
	
	public static void bfs(int start, int[] count) {

		boolean[] visited = new boolean[N+1];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start] = true;
		int result = 1; 
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			for(int next_node : list.get(node)) {
				if(!visited[next_node]) {
		 
					visited[next_node] = true; 
					queue.add(next_node);
					count[next_node]++; // 현재 노드에서 방문한 노드들의 카운트를 올려준다
					result++;
				}
			}
		}
		count[start] += result; // 현재노드가 방문 할 수 있는 노드들의 갯수를 더해준다
 	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			// 인접 리스트 만들기
			list = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < N + 1; i++) {
				list.add(new ArrayList<Integer>());
			}
			
			for (int i = 0; i < M; i++) {
				String[] inputs = br.readLine().split(" ");
				int a = Integer.parseInt(inputs[0]);
				int b = Integer.parseInt(inputs[1]);
				list.get(a).add(b);
			}
			
			int[] count = new int[N+1];
			for(int i = 1; i < N + 1; i++) {//BFS로 모든 노드를 방문해서 노드 i를 방문한 노드의 수 + 노드 i에서 방문할수 있는 노드의 수
				bfs(i, count);
			}
			
			int result = 0;
			for(int i = 1; i < N + 1; i++) { //다 더한 합이 N-1일때 순서를 정확하게 알 수 있다
				result += (count[i] == N ) ? 1 : 0;
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();

	}

}