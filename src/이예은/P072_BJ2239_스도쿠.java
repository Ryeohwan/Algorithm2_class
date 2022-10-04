package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class P072_BJ2239_스도쿠 {
	static boolean isFinished = false;
	static class Pos {
		int y,  x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
	
	public static boolean check(int y, int x, int num, int[][] map) {
		
		// 가로 세로 체크
		for(int i = 0; i < 9; i++) {
			if(map[y][i] == num || map[i][x] == num)
				return false;
		}
		
		// 박스 체크
		int sy = (y / 3) * 3;
		int sx = (x / 3) * 3;	
		for(int  i = sy; i < sy + 3; i++) {
			for(int j = sx; j < sx + 3; j++) {
				if(map[i][j] == num)
					return false;
			}
		}
		return true;
	}

	
	public static boolean makeSudoku(int[][] map, ArrayList<Pos> list, int cnt, int N) {
		
		if(cnt == N) { // 모든 빈칸이 다 차게 되면 리턴한다
			return true;
		}
		
		Pos p = list.get(cnt);
		int y = p.y;
		int x = p.x;
		boolean result = false;
		
		for(int num = 1; num < 10; num++) {
			
			// map에 1부터 9 까지 숫자를 넣고
			// 그 숫자가 들어갈 수 있는지 체크한다		
			if(check(y, x, num, map)) {
				map[y][x] = num; 
				
				result = makeSudoku(map, list, cnt+1, N); // result == true이면 모든 빈칸에 숫자가 채워졌음을 의미하기 때문에 바로 리턴한다
				if(result) {
					return result;
				}
			}
		}
		
		// BackTracking 하기전에  map값을 초기화한다
		map[y][x] = 0;
		return result;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		
		ArrayList<Pos> list = new ArrayList<>();
		
		int[][] map = new int[9][9];
		for(int i = 0; i < 9; i++) {
			String inputs = br.readLine();
			for(int j = 0; j < 9; j++) {
				map[i][j] = inputs.charAt(j) - '0';
				
				if(map[i][j] == 0) { // 0인 부분의 좌표를 리스트로 저장한다
					list.add(new Pos(i, j));
				}
			}
		}
		/*--------------------- 입력끝 ------------------------*/
		
		makeSudoku(map, list, 0, list.size());
		
		/*--------------------- 출력  ------------------------*/
		
		for(int i = 0; i < 9 ; i++) {
			for(int j = 0; j < 9; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
