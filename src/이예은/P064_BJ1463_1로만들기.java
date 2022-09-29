package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P064_BJ1463_1로만들기 {
	static int d[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		d= new int[n+1];
		
		System.out.println(seperate(n));
	}
	public static int seperate(int n) {
		if(n == 1) {
			return 0;
		}
		if(d[n] > 0) return d[n]; // 처음엔 다 0으로 초기화 되어있으니까 값이 있으면 리턴
		
		d[n] = seperate(n-1) + 1 ;
		
		if(n % 3 == 0) {
			int temp = seperate(n/3) +1;
			if(d[n] > temp) d[n] = temp;
		}
		if(n % 2 == 0) {
			int temp = seperate(n/2) +1;
			if(d[n] > temp) d[n] = temp;
		}
		
		return d[n];
		
	}
	
//	public static void main(String[] args) throws NumberFormatException, IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//		int N = Integer.parseInt(br.readLine());
//		int[] dp = new int[N+1]; //N에서 숫자 i로 갈수 있는 최소 연산 수
//		int INF = 1000000;
//		for(int i = 0; i < N + 1; i++) { // dp배열 초기화
//			dp[i] = INF;
//		}
//		
//		dp[N] = 0;
//		for(int i = N; i >= 1; i--) {
//			if(i % 3 == 0) { // 3으로 나눠지면 3으로 나눠라
//				dp[i/3] = dp[i/3] < dp[i] + 1? dp[i/3] : dp[i] + 1;
//			} 
//			if(i % 2 == 0) { // 2로 나눠지면 2로 나눠라
//				dp[i/2] = dp[i/2] < dp[i] + 1? dp[i/2] : dp[i] + 1;
//			}
//			dp[i-1] = dp[i-1] < dp[i] + 1? dp[i-1] : dp[i] + 1; // 1 빼기
//		}
//		
//		bw.write(dp[1] +"");
//		br.close();
//		bw.close();
//	}
}
