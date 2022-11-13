package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G1_01019_책_페이지 {
	static int[] ans = new int[10];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int s = 1;
		int p = 1;
		
		while(s <= N) {
			while(s <= N && N % 10 != 9) {
				addDigits(N, p);
				N--;
			}
			
			while(s <= N && s % 10 != 0) {
				addDigits(s, p);
				s++;
			}
			
			if(s > N) break;
			
			s /= 10;
			N /= 10;
			
			for(int i = 0; i < 10; i++) {
				ans[i] += (N-s+1) * p;
			}
			
			p *= 10;
		}
		
		for(int i = 0; i < 10; i++) {
			sb.append(ans[i]).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static void addDigits(int n, int p) {
		while(n > 0) {
			ans[n%10] += p;
			n /= 10;
		}
	}
}
