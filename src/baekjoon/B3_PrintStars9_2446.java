package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_PrintStars9_2446 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= 2*N-1; i++) {
			for(int j = 0; j < N-1-Math.abs(N-i); j++) {
				sb.append(" ");
			}
			for(int j = 0; j < 2*Math.abs(N-i)+1; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}