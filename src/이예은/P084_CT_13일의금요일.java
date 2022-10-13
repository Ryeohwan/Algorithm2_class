package 이예은;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class P084_CT_13일의금요일 {
	public static boolean isLeapyear(int year) {
		if(year % 400 == 0) return true;
		if(year % 100 == 0) return false;
		if(year % 4 == 0) return true;
		return false;	
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//만약 모든달이 28일 이라면 모든 금요일이 13일일것다
		//하지만 모든달이 7로 나누어 떨어지지 않기 때문에 그 간격만큼 요일이 달라진다
		//다음배열은 윤년과 아닐때의 일수를 7로 나눈 나머지를 저장했다
		// 31일 -> 3
		// 29일 -> 1
		int[] leap = {3, 3, 1, 3, 2, 3, 2, 3, 3, 2, 3, 2};
		int[] normal = {3, 3, 0, 3, 2, 3, 2, 3, 3, 2, 3, 2};
		int[] count = new int[7];
		
		int n = 1;
		//2022년 10월 13일 은 금요일이다
		//2022년 11월 13일은 일요일이다
		//2022년 12월 13일은 화요일이다
		int answer = 0;
		int year = 2023;
		int month = 0;
		boolean isFinished = false;
		
		while(true){
			
			if(isLeapyear(year)) { // 윤년일때
				for(month = 0; month < 12; month++) {// 1 ~ 12월
					
					int d = leap[month];
					n = (n + d) % 7;
					if(n == 4) {
						isFinished = true;
						break;
					}
				}
			} else { // 윤년이 아닐때
				for(month = 0; month < 12; month++) {// 1 ~ 12월
					
					int d = normal[month];
					n = (n + d) % 7;
					if(n == 4) {
						isFinished = true;
						break;
					}
				}
			}
			if(isFinished) break;
			year++;
		}
		
		bw.write( month + 1 + "월");
		bw.flush();
		bw.close();
	}
}