package boj.s4;

import java.io.IOException;

public class S4_01065_한수 {
	public static void main(String[] args) throws IOException {
		int N = 0, b = 0;
		do N = (N << 3) + (N << 1) + (b & 15);
		while((b = System.in.read()) > 32);
		
		if(N < 100) {
			System.out.println(N);
			return;
		}
		
		int ans = 99;
		
		for(int n = 100; n <= N; n++) {
			if(check(n)) ans++;
		}
		
		System.out.println(ans);
	}
	
	static boolean check(int n) {
		int diff = 10;
		
		while(n > 0) {
			int a = n % 10;
			n /= 10;
			
			if(n == 0) break;
			else if(diff == 10) {
				diff = a - n % 10;
			}
			else if(diff != a - n % 10) {
				return false;
			}
		}
		
		return true;
	}
}
