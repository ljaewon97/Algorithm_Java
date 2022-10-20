package boj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B5_11718_그대로_출력하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = null;
		
		while((line = br.readLine()) != null) {
			sb.append(line).append("\n");
		}
		
		System.out.println(sb);
	}
}
