package boj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B5_05341_Pyramids {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			long N = Long.parseLong(br.readLine());
			if(N == 0) break;
			sb.append(N*(N+1)/2).append("\n");
		}
		
		System.out.println(sb);
	}
}
