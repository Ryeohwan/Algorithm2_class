package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class P082_SWEA4014_활주로건설 {
	static int N, X;
	
	public static int makeAirstrip(int[][] map) {
		int count = 0;
		
		boolean[][] visited = new boolean[N][N];
		for (int y = 0; y < N; y++) {

			int start = map[y][0];
			boolean isOk = true;
			for (int x = 0; x < N; x++) {
				if (start == map[y][x])
					continue;

				if (start - map[y][x] < -1 || start - map[y][x] > 1) {
					isOk = false;
					break;
				}

				if (start - map[y][x] < 0) { // 왼쪽으로 활주로
					isOk = check(visited, map, y, x - 1, -1);
					
				} else { // 오른쪽으로 활주로
					isOk = check(visited, map, y, x, 1);
					x += (isOk) ? X - 1 : 0; // 오른쪽 활주로가 생긴경우 다음 탐색 위치가 활주로 이후이기 때문에 더해준다
				}
			
				if(!isOk) break;
				start = map[y][x]; // 비교해야할 땅의 높이를 갱신해준다
			}

			count += (isOk) ? 1 : 0; // 활주로를 만들수 있다면 count를 올려준다
		}
		return count;
		
	}
	public static boolean check(boolean[][] visited, int[][] map, int y, int x, int dx) {
		// X길이 만큼의 활주로를 만들 수 있도록 같은 
		int start = map[y][x];
		visited[y][x] = true;

		int nx = x;
		for (int i = 0; i < X - 1; i++) {
			nx += dx;
			if (nx < 0 || nx >= N || start != map[y][nx] || visited[y][nx]) return false;
			visited[y][nx] = true;
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			String[] inputs = br.readLine().split(" ");
			N = Integer.parseInt(inputs[0]);
			X = Integer.parseInt(inputs[1]);
			int[][] map_yx = new int[N][N]; // X축 기준으로  저장한다
			int[][] map_xy = new int[N][N]; // 90도 돌려서 Y축 기준으로 저장한다

			for (int i = 0; i < N; i++) {
				inputs = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map_yx[i][j] = Integer.parseInt(inputs[j]);
					map_xy[j][i] = Integer.parseInt(inputs[j]);
				}
			}

			int result = 0;
			// X축 체크
			result += makeAirstrip(map_yx);
			// Y축 체크
			result += makeAirstrip(map_xy);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();

	}

}