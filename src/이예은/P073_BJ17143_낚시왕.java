package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class P073_BJ17143_낚시왕 {
	
	static class Shark { // 상어의 위치와 상태를 저장한다
		int r, c, s, d, z;
		boolean isArrived;

		public Shark(int r, int c, int s, int d, int z, boolean isArrived) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.isArrived = isArrived;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] inputs = br.readLine().split(" ");
		
		int R = Integer.parseInt(inputs[0]);
		int C = Integer.parseInt(inputs[1]);
		int M = Integer.parseInt(inputs[2]);
		
		int[] dy = {0, -1, 1, 0, 0};
		int[] dx = {0, 0, 0, 1, -1};
		
		ArrayList<Shark> list = new ArrayList<>();
		int[][] map = new int[R + 1][C + 1];
		int index = 1;
		list.add(new Shark(0, 0, 0, 0, 0, false));
		for(int i = 0; i < M; i++) {
			
			inputs = br.readLine().split(" ");
			int r = Integer.parseInt(inputs[0]);
			int c = Integer.parseInt(inputs[1]);
			int s = Integer.parseInt(inputs[2]);
			int d = Integer.parseInt(inputs[3]);
			int z = Integer.parseInt(inputs[4]);
			
			list.add(new Shark(r, c, s, d, z, true));
			map[r][c] = index++;
		}
		
		/*----------------------- 입력 끝 ---------------------------*/
		
		int total = 0;
		int sharks = M; // 상어의 총 갯수
		
		for(int c = 1; c <= C; c++) {
			
			if(sharks == 0) break;
			
			// STEP 1: 상어 잡기
			for(int r = 1; r <= R; r++) {
				if(map[r][c] != 0) { // c열에 가장 위에 있는 상어를 잡는다
					
					Shark s = list.get(map[r][c]);
					total += s.z;
					map[r][c] = 0;
					s.isArrived = false;
					sharks--;
					break;
				}
			}
			
			// STEP 2: 상어 이동
			for(int i = 1; i <= M; i++) { // 살아있는 상어를 이동시킨다
				Shark s = list.get(i);
				if(map[s.r][s.c] == i) map[s.r][s.c] = 0;
				if(!s.isArrived) continue; // 상어가 살아있는지 확인한다
				
				
				// 다음 위치 찾기
				int nr = s.r;
				int nc = s.c;
				int nd = s.d;
				
				if(s.d <= 2) { // 위아래 -> nr만 바뀐다
			
					for(int j = 0; j < s.s; j++) {
						nr = nr + dy[nd];
						
						if(nr == 0 || nr == R + 1) { // 움직였을때 범위를 넘어가면 방향을 바꿔서 2번 진행한다
							nd += (nd %2 == 0)? -1 : 1;
							nr = nr + (dy[nd] * 2);
						}
					}
				} else { // 좌우 -> nc만 바뀐다
					
					for(int j = 0; j < s.s; j++) {
						nc = nc + dx[nd];
						
						if(nc == 0 || nc == C + 1) { // 움직였을때 범위를 넘어가면 방향을 바꿔서 2번 진행한다
							nd += (nd %2 == 0)? -1 : 1;
							nc = nc + (dx[nd] * 2);
						}
					}
				}

				
				// 상어 놓기
				if(map[nr][nc]!= 0 && map[nr][nc] < i) { // 그 자리에 새로 움직인 상어가 있다면 크기를 비교해서 상어를 둔다
					Shark cs = list.get(map[nr][nc]);
					
					sharks--;
					if(cs.z > s.z) { // 원래있던 상어의 크기가 지금의 상어의 크기보다 크다면
						s.isArrived = false; // 지금 상어를 죽인다
					} else { // 원래있던 상어의 크기가 지금의 상어의 크기보다 작다면
						cs.isArrived = false; // 있었던 상어를 죽인다
						map[nr][nc] = i; // 상어의 위치 갱신
						s.r = nr; // 상어 상태 갱신
						s.c = nc;
						s.d = nd;
					}
				} else {
					map[nr][nc] = i; // 상어의 위치 갱신
					s.r = nr; // 상어의 위치 갱신
					s.c = nc;
					s.d = nd;
				}
			}
		}
		
		bw.write(total + "");
		br.close();
		bw.close();
	}
}
