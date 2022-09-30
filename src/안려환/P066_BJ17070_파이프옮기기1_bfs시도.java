package 안려환;

import java.util.Scanner;

public class P066_BJ17070_파이프옮기기1_dp {
		private static int map[][];
		private static int dp[][][]; // y,x,dir
		private static int ans;
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		map = new int [N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		} // 입력부 완
		
		dp = new int[N+1][N+1][3];
		
		// garo = 0 sero = 1 cross = 2 
        // 점화식 (넣기 전에 조건 검사 - > 맵 밖으로 나가는 경우와 벽을 만나는 경우)
        // dp[y][x][0] = dp[y][x-1][0] + dp[y-1][x-1][2];
        // dp[y][x][1] = dp[y-1][x][1] + dp[y-1][x-1][2];
        // dp[y][x][2] = dp[y-1][x][1] + dp[y-1][x-1][2] + dp[y][x-1][0];
        
        // ans = dp[y][x][1] + dp[y][x][0] + dp[y][x][3]

		
	

		for (int i = 1; i <= N; i++) {
			for (int j = 1 ; j <= N; j++) {
				 if(map[i][j] != 1) {
					 dp[1][2][0] = 1;
					 dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
					 dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
					 if(map[i][j-1] == 0 && map[i-1][j] == 0)dp[i][j][2] = dp[i-1][j-1][1] + dp[i-1][j-1][2] + dp[i-1][j-1][0];
				 }
			}
		}
		ans = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
		System.out.println(ans);
	}

}
