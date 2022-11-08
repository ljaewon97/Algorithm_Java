package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4_25704_출석_이벤트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		int ans = P;
		
		if(N >= 5) {
			int price = Math.max(P-500, 0);
			ans = Math.min(ans, price);
		}
		
		if(N >= 10) {
			int price = 9*P/10;
			ans = Math.min(ans, price);
		}
		
		if(N >= 15) {
			int price = Math.max(P-2000, 0);
			ans = Math.min(ans, price);
		}
		
		if(N >= 20) {
			int price = 3*P/4;
			ans = Math.min(ans, price);
		}
		
		System.out.println(ans);
	}
}
