package BaekJoon;

import java.util.Scanner;

public class BJ_1149_RGB거리 {

	/**
	 * 점화식을 생각하기 전에 해야할 것 
	 * 3가지의 색이 있고 비용이 주어졌다.
	 * 연달아 붙어있는 집의 색은 달라야 한다.
	 * 잘게 생각해 보자.
	 * **/
	
	static int [][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		dp = new int[N+1][3];  // 가장 적은 비용이 담길 배열
		for (int i = 1; i <= N; i++) {
			int R = sc.nextInt();
			int G = sc.nextInt();
			int B = sc.nextInt();
			
			// Red
			dp[i][0] = Math.min(dp[i-1][1] ,  dp[i-1][2]) + R;
			// Green
			dp[i][1] = Math.min(dp[i-1][0] ,  dp[i-1][2]) + G;
			// Blue
			dp[i][2] = Math.min(dp[i-1][0] ,  dp[i-1][1]) + B;
			
			
		
		}
		
		System.out.println(Math.min(dp[N][2],Math.min(dp[N][0], dp[N][1])));
	}
	

}
