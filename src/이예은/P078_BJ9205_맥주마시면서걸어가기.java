package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P078_BJ9205_맥주마시면서걸어가기 {
	static final int INF = 9999999;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {

			int N = Integer.parseInt(br.readLine());
			int[][] nodes = new int[N + 2][2];
			int[][] distance = new int[N + 2][N + 2];

			for (int i = 0; i < N + 2; i++) {
				String[] inputs = br.readLine().split(" ");
				nodes[i][0] = Integer.parseInt(inputs[1]);
				nodes[i][1] = Integer.parseInt(inputs[0]);
			}

			// 인접리스트 만들기 갈수 있으면 1 갈수 없으면 INF을 입력한다
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					if (i == j)
						distance[i][j] = INF;

					int y = nodes[i][0];
					int x = nodes[i][1];

					int ny = nodes[j][0];
					int nx = nodes[j][1];

					int d = Math.abs(ny - y) + Math.abs(nx - x);
					distance[i][j] = d <= 1000 ? 1 : INF;
				}
			}
			
			// 플로이드 와샬 알고리즘
			for (int k = 1; k < N + 1; ++k) {
				for (int i = 0; i < N + 2; ++i) {
					if (i == k)
						continue; // 출발지와 경유지가 같다면 다음 출발지
					for (int j = 0; j < N + 2; ++j) {
						if (i == j || k == j)
							continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
						if (distance[i][j] > distance[i][k] + distance[k][j]) {
							distance[i][j] = distance[i][k] + distance[k][j];
						}
					}
				}
			}

			if (distance[0][N + 1] == INF) { // 집에서 페스티벌까지 가는 길이 없다면
				sb.append("sad").append("\n");
			} else {
				sb.append("happy").append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}