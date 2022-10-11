import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 활주로_건설_교수님풀이 {
	private static int N,X;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String [] arr = br.readLine().split(" ");
			N = Integer.parseInt(arr[0]);
			X = Integer.parseInt(arr[1]);
			int [][] map = new int [N][N];
			int [][] col = new int [N][N];  // 가로
			for (int i = 0; i < N; i++) {
				arr = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					col[j][i] = map[i][j] = Integer.parseInt(arr[j]);
				}
			}  // 입력부 완
			
			int answer = 0;
			for (int i = 0; i < N; i++) {
				if(makeRoad(map[i])) answer++;
				if(makeRoad(col[i])) answer++;
			}
			// answer를 출력하면 된다.
			System.out.println("#"+tc+" "+answer);
		}
	}
	
	private static boolean makeRoad(int[] road) { // 가능하면 1을 리턴한다.
		int beforeHeight = road[0], size = 0;
		int j = 0;
		while(j<N) {
			if(beforeHeight == road[j]) {  // 동일높이라면
				size++;
				j++;
			}else if (beforeHeight + 1 == road[j]) {  // 오르막 경사로 설치 체크
				if(size < X) return false;  // X길이 미만이면 활주로 건설 불가
				beforeHeight++;
				size = 1;  // 길이는 새롭게 1차이나는 높이에 size 가 1인 상태로 다시 시작
				j++;
			}else if (beforeHeight -1 == road[j]) {
				int count = 0;
				for (int i = j; i < N; i++) {
					if(road[i] != beforeHeight -1) return false; // 내리막이니까 1차이가 아니면 경사로 아예 못둠
					if(++count == X) { // 그럼 count 올려보고 X길이 만족하면 반복문 빠져나온다.
						break;
					}
				}
				if(count < X) return false;// 끝까지 갔지만 연속이 아닌 것이니 안된다.
				beforeHeight -- ; // 내리막 경사로를 세운 것이니 높이를 줄여준 것이다.
				j += X; // 저 경사로를 뒀으니 이후로 뛰어 넘어야하기 때문이다.
				size = 0;  // 내려가면서 재는 것이니...
			}else {  // 높이가 2 이상의 차이가 날 때
				return false;
			}
		}
		
		return true;
	}

}
