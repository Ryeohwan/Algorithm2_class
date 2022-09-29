package 이예은;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P065_BJ1149_RGB거리 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int[][] cost = new int[N][3]; // i번째 집이 j색일때 최저 비용 
		
		for(int i = 0; i < N; i++) {
			String[] inputs = br.readLine().split(" ");
			for(int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		/*-------------입력끝------------*/
		
		for(int i = 1; i < N; i++) { // dp table 채우기 
			// cost[i][0] = i번째 집이 R색일때 i-1번째 집이 G or B일때 최소비용 + i번째 집을 R로 칠할때 최소비용
			// cost[i][1] = i번째 집이 G색일때 i-1번째 집이 R or B일때 최소비용 + i번째 집을 G로 칠할때 최소비용
			// cost[i][2] = i번째 집이 B색일때 i-1번째 집이 R or G일때 최소비용 + i번째 집을 B로 칠할때 최소비용
			cost[i][0] += cost[i-1][1] < cost[i-1][2] ? cost[i-1][1] : cost[i-1][2];
			cost[i][1] += cost[i-1][0] < cost[i-1][2] ? cost[i-1][0] : cost[i-1][2];
			cost[i][2] += cost[i-1][0] < cost[i-1][1] ? cost[i-1][0] : cost[i-1][1];
		}
		
		// n-1번째 까지 왔을때 집이 R, G, B색중 가장 적은 비용이 답이다
		int result =  (cost[N-1][0] < cost[N-1][1]) ? cost[N-1][0] : cost[N-1][1];
		result = result > cost[N-1][2] ? cost[N-1][2]: result;
		
		/*------------출력------------*/
		bw.write(result + "");
		br.close();
		bw.close();
	}
}
