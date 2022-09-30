package 안려환;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
}
