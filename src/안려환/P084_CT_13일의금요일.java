package 안려환;

import java.util.ArrayList;
import java.util.List;

public class P084_CT_13일의금요일 {

	public static void main(String[] args) {
		int [] years = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		// 이렇게 되는데 윤년을 따져야 함. -> 4의 배수이면서 100의 배수 아니고 400의 배수
		int year,month,day, yoin;
		year = 2022;
		month = 10;
		day = 13;
		yoin = 3;
		
		
		while(true) {
			if((year % 4 == 0 && year % 100 != 0 )|| year % 400 == 0) {	// 윤년 체크
				years[1] = 29;
			}else {
				years[1] = 28;
			}
			day += years[month-1];
			int temp = day % 7;
			yoin  += temp;
			if(yoin >= 7) {
				yoin = yoin - 7;
			}
			day -= years[month-1];
			month = month+1;
			if(month > 12) {
				month -= 12;
				year += 1;
			}
			yoin += 1;
			if(yoin == 4) {
				break;
			}
		}
		
		System.out.println("2022년 10월 13일 후에 가장 빠른 13일의 금요일은");
		System.out.println(year+"년 "+month+"월 "+day+"일입니다.");


	}
	
	
}
