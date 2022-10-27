package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4_01264_모음의_개수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			
			if(line.equals("#")) break;
			
			int cnt = 0;
			
			for(int i = 0; i < line.length(); i++) {
				if(check(line.charAt(i))) cnt++;
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static boolean check(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
	}
}
