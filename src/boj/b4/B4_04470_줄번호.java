package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4_04470_줄번호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= N; i++) {
			sb.append(String.format("%d. %s\n", i, br.readLine()));
		}
		
		System.out.println(sb);
	}
}
