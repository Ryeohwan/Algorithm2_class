package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P077_SWEA1263_사람네트워크2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		final int INF = 999999;

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			String[] inputs = br.readLine().split(" ");
			int N = Integer.parseInt(inputs[0]);

			int[][] distance = new int[N][N];
			
			// 인접리스트 만들기 0이면 INF를 저장한다
			int index = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					distance[i][j] = Integer.parseInt(inputs[index++]);
					distance[i][j] = distance[i][j] == 0 ? INF : 1;
				}
			}

			// 플로이드 와샬
			int min = Integer.MAX_VALUE;
			for (int k = 0; k < N; ++k) {
				for (int i = 0; i < N; ++i) {
					if (i == k)
						continue; // 출발지와 경유지가 같다면 다음 출발지
					for (int j = 0; j < N; ++j) {
						if (i == j || k == j)
							continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스

						if (distance[i][j] > distance[i][k] + distance[k][j]) {
							distance[i][j] = distance[i][k] + distance[k][j];
						}
					}
				}
			}

			// 사람들의 CC 값들 중에서 최솟값을 찾는다
			for (int i = 0; i < N; i++) {
				int temp = 0;
				for (int j = 0; j < N; j++) { // i에서 갈수 있는 모든 연결요소를 더한다
					if (distance[i][j] == INF)
						continue;
					temp += distance[i][j];
				}
				min = min > temp ? temp : min; // min값을 갱신한다
			}
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();

	}

}