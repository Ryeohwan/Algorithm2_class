package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;

import sun.nio.cs.ArrayDecoder;

public class P066_BJ17070_파이프옮기기1 {
	static int[][] map;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
	
		for(int j = 0; j < N + 1; j++) {
			map[0][j] = 0;
		}
		
		for(int i = 1; i < N + 1; i++) {
			String[] inputs = br.readLine().split(" ");
			for(int j = 0; j < N + 1 ; j++) {
				if(j == 0) {
					map[i][j] = 0;
				} else {
					map[i][j] = Integer.parseInt(inputs[j - 1]);
				}
			}
		}
		
		int[][][] dp = new int[3][N + 1][N + 1];
		dp[0][1][2] = 1;

		for(int y = 1; y < N+1; y++) {
			for(int x = 3; x < N+1; x++) {
				
				if(map[y][x] == 1) continue;
				

				// 가로
				dp[0][y][x] = dp[0][y][x-1] + dp[1][y][x-1];
				
				// 대각선
				if(map[y-1][x] == 0 && map[y][x-1] == 0) {
					dp[1][y][x] = dp[0][y-1][x-1] + dp[1][y-1][x-1] + dp[2][y-1][x-1];
				}
				
				// 세로
				dp[2][y][x] = dp[1][y-1][x] + dp[2][y-1][x];
			
			}
		}
		

		//System.out.println(dp[0][1][2]);
		//System.out.println(Arrays.deepToString(dp));
		int result =  dp[0][N][N] + dp[1][N][N] + dp[2][N][N];
		bw.write(result + "");
		br.close();
		bw.close();
	}
}
