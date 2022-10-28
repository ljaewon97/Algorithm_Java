package boj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B5_25372_성택이의_은밀한_비밀번호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			int len = br.readLine().length();
			sb.append(6 <= len && len <= 9 ? "yes\n" : "no\n");
		}
		
		System.out.println(sb);
	}
}
