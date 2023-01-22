package boj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B5_26766_Serca {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		String heart = " @@@   @@@  \n@   @ @   @ \n@    @    @ \n@         @ \n @       @  \n  @     @   \n   @   @    \n    @ @     \n     @      \n";
		
		while(N-- > 0) {
			sb.append(heart);
		}
		
		System.out.println(sb);
	}
}
