package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_01193_분수찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int X = Integer.parseInt(br.readLine());
		int n = 1;
		
		while(true) {
			if(n * (n+1) / 2 >= X) break;
			n++;
		}
		
		if(n % 2 == 1) {
			System.out.print(1 + n * (n+1) / 2 - X);
			System.out.print("/");
			System.out.print(n - n * (n+1) / 2 + X);
		}
		else {
			System.out.print(n - n * (n+1) / 2 + X);
			System.out.print("/");
			System.out.print(1 + n * (n+1) / 2 - X);
		}
	}
}
