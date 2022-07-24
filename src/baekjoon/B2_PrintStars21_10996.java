package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2_PrintStars21_10996 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= 2*N; i++) {
			sb.append(i % 2 == 1 ? "" : " ");
			for(int j = 0; j < (i % 2 == 1 ? 2*((N+1)/2)-1 : 2*(N/2)-1); j++) {
				sb.append(j % 2 == 0 ? "*" : " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}