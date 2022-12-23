package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_26651_팬램그 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int X = Integer.parseInt(br.readLine());
		
		if(X == 0) {
			System.out.println("A");
			return;
		}
		
		if(X <= 99900) {
			for(int i = 1; i*i <= X; i++) {
				if(X % i == 0 && i + X/i + 24 <= 100000) {
					for(int j = 0; j < i; j++) {
						sb.append("A");
					}
					sb.append("BCDEFGHIJKLMNOPQRSTUVWXY");
					for(int j = 0; j < X/i; j++) {
						sb.append("Z");
					}
					break;
				}
			}
		}
		else {
			int a = 31622;
			int b = X/31622;
			int c = X%31622;
			
			for(int i = 0; i < a; i++) {
				sb.append("A");
			}
			sb.append("BCDEFGHIJKLMNOPQRSTUVWXY");
			for(int i = 0; i < b; i++) {
				sb.append("Z");
			}
			
			sb.append("ABCDEFGHIJKLMNOPQRSTUVWXY");
			for(int i = 0; i < c; i++) {
				sb.append("Z");
			}
		}
		
		System.out.println(sb);
	}
}
