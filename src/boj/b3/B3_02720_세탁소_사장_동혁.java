package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_02720_세탁소_사장_동혁 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int C = Integer.parseInt(br.readLine());
			
			sb.append(C / 25).append(" ");
			C %= 25;
			
			sb.append(C / 10).append(" ");
			C %= 10;
			
			sb.append(C / 5).append(" ");
			C %= 5;
			
			sb.append(C).append("\n");
		}
		
		System.out.println(sb);
	}
}
