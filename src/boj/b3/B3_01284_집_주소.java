package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_01284_집_주소 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			
			if(N == 0) break;
			
			int ans = 1;
			
			while(N > 0) {
				int digit = N % 10;
				ans += (digit == 1 ? 2 : digit == 0 ? 4 : 3) + 1;
				N /= 10;
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
}
