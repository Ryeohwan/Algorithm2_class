package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P076_SWEA3307_최장증가부분수열 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t ++) {
		
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			int[] LIS = new int[N];
			
			
			String[] inputs = br.readLine().split(" ");
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(inputs[i]);
			}
			
			/*------------------ 입력 완료 -------------------*/
			
			int max = 0;
			for(int i = 0; i < N; i++) {
				LIS[i] = 1;
				for(int j = 0; j < i; j++) {
					if(arr[j] < arr[i] && LIS[i] < LIS[j] + 1) { // dp테이블 채우기
						LIS[i] = LIS[j] + 1;
					}
				}
				max = Math.max(max, LIS[i]);
			}
			
			/*------------------ 출력 완료 -------------------*/
			sb.append("#").append(t).append(" ").append(max);
		}
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
}
