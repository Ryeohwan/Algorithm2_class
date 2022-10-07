package 안려환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class P072_BJ2239_스도쿠  {

	static int[][] map =new int[9][9];
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		for(int i=0;i<9;i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0;j<9;j++) {
				map[i][j] = c[j]-'0';
			}
		}  // 입력부 완
		
		dfs(0);
		
		for(int[] a : map) {
			for(int b : a) {
				sb.append(b);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	private static void dfs(int d) {
		
		if(d==81) {  // 맵의 끝에 도달했다면 
			flag = true;
			return;
		}
	
		int r = d/9;
		int c = d%9;
		
		if(map[r][c]!=0)  // map 에 이미 적혀있는 경우라면
			dfs(d+1);  // 다음 수로 이동한다.
		else {
			for(int i=1;i<10;i++) {
				if(!isValid(r,c,i))continue;  // 가능한 수가 아니라면 
				map[r][c] = i;  // 가능한 수이기에 arr 에 넣어준다.
				dfs(d+1);
				
				// 종료 조건이 아니라면 더이상 선택할 수가 없다는 뜻 => 백트랙킹

				if(flag) return;
				map[r][c]=0;
				
			}
		}
		
		
		
	}
	private static boolean isValid(int r, int c, int n) {
		
		for(int i=0;i<9;i++) {
			if(map[i][c] == n || map[r][i]==n) return false;  // 유효성 검사
		}
		

		int sr = r/3 * 3;  // 속해있는 3x3 의 칸 안을 체크해 주기 위한 범위
		int sc = c - c % 3;
		for(int row=sr;row<sr+3;row++) {
			for(int col=sc;col<sc+3;col++) {
				if(map[row][col]==n) return false;
			}
		}
		
		
		return true;
		
	}

}