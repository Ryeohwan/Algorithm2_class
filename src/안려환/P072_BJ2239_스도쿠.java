package 과제;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class P072_BJ2239_스도쿠  {

	static int[][] arr =new int[9][9];
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		for(int i=0;i<9;i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0;j<9;j++) {
				arr[i][j] = c[j]-'0';
			}
		}  // 입력부 완
		
		dfs(0);
		
		for(int[] a : arr) {
			for(int b : a) {
				sb.append(b);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	private static void dfs(int d) {
		
		if(d==81) {
			flag = true;
			return;
		}
	
		int r = d/9;
		int c = d%9;
		
		if(arr[r][c]!=0)
			dfs(d+1);
		else {
			for(int i=1;i<10;i++) {
				if(!isValid(r,c,i))continue;
				arr[r][c] = i;
				dfs(d+1);
				
				// 종료 조건이 아니라면 더이상 선택할 수가 없다는 뜻 => 백트랙킹

				if(flag) return;
				arr[r][c]=0;
				
			}
		}
		
		
		
	}
	private static boolean isValid(int r, int c, int n) {
		
		for(int i=0;i<9;i++) {
			if(arr[i][c]==n || arr[r][i]==n) return false;
		}

		int sr = r/3 * 3;
		int sc = c - c % 3;
		for(int row=sr;row<sr+3;row++) {
			for(int col=sc;col<sc+3;col++) {
				if(arr[row][col]==n) return false;
			}
		}
		
		
		return true;
		
	}

}