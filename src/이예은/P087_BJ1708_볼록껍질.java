package 이예은;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class P087_BJ1708_볼록껍질 {
	
	static long MINY, MINX;
	
	static class Dot implements Comparable<Dot>{
		long y, x;
		
		public Dot(long x, long y) {
			this.y = y;
			this.x = x;

		}

		@Override
		public int compareTo(Dot o) {
			long ccw = ((MINX*o.y) + (x*o.y) + (o.x * MINY)) - ((MINY*x) + (y*o.x) + (o.y*MINX));
			if(ccw < 0) {
				return 1;
			} else if(ccw > 0) {
				return -1;
			}
			
			long distance1 = Math.abs(MINX - x) + Math.abs(MINY - y);
			long distance2 = Math.abs(MINX - o.x) + Math.abs(MINY - o.y);
			if(distance1 > distance2) {
				return 1;
			} 
			return -1;
		} 
		

	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[][] dots = new int[N][2];
//		PriorityQueue<Dot> pq = new PriorityQueue<>();
		ArrayList<Dot> list = new ArrayList<>();
		Stack<Dot> stack = new Stack<>();
		
		MINX = Integer.MAX_VALUE;
		MINY = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			String[] inputs = br.readLine().split(" ");
			long x= Integer.parseInt(inputs[0]);
			long y= Integer.parseInt(inputs[1]);
			list.add(new Dot(x, y));
			if (y < MINY){
				MINY = y;
				MINX = x;
				 
			} else if (y == MINY){
				if (x < MINX){
					MINY = y;
					MINX = x;
				}
			}
		}
		Collections.sort(list);
		
		// 가장 왼쪽 아래 있는 점 2개를 stack에 넣는다.
		stack.add(list.get(0));
		stack.add(list.get(1));
		
		Dot p1 = null, p2 = null, p3 = null;
		for(int i = 2; i < N; i++) { // 모든 점을 돌때 까지 
			while(stack.size() >= 2) {
				p3 = list.get(i);
				p2 = stack.pop();
				p1 = stack.peek();
				
				long ccw = ((p1.x*p2.y) + (p2.x*p3.y) + (p3.x * p1.y)) - ((p1.y*p2.x) + (p2.y*p3.x) + (p3.y*p1.x));
			
				if(ccw > 0) {
					stack.push(p2);
					break;
				}
				
			}
			stack.push(p3);
		}
		
//		while(!stack.isEmpty()) {
//			Dot p = stack.pop();
//			System.out.println(p.x + " " + p.y);
//		}
		
		bw.write(stack.size() + "");
		bw.flush();
		br.close();
		bw.close();
		
		
	}
}

