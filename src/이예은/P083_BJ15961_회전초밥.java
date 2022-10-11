import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P083_BJ15961_회전초밥 {

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int max=0;
        
        st = new StringTokenizer(br.readLine());
 
        int N = Integer.parseInt(st.nextToken()); //접시수
        int d = Integer.parseInt(st.nextToken()); //초밥 수
        int k = Integer.parseInt(st.nextToken()); //연속해서 먹는 접시수
        int c = Integer.parseInt(st.nextToken()); //쿠폰번호
        
        int [] sushi = new int[N];
        for(int i=0;i<N;i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        
        int [] check = new int[d+1]; // 초밥을 먹었는지 확인
        int count=0;
        
        for(int i=0;i<k;i++) {
            if(check[sushi[i]] == 0) count++;
            check[sushi[i]]++;
        }
        
        max = count;
        int start=1, end=k;
        while(true) {
            
            if(check[sushi[start-1]]==1) count--;
            check[sushi[start-1]]--;
            
            if(check[sushi[end]]==0) count++;
            check[sushi[end]]++;
            
            if(check[c]==0) max = Math.max(max, count+1);  // max 를 사용해서 가장 많은 종류의 초밥을 먹었나 체크
            else max = Math.max(max, count);
            
            start++; end++;
            if(end==N) end=0;
            if(start==N) break;
        }
        System.out.println(max);
            
    }
}
