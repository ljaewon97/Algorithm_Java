

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_PrintStars6_2443 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int i = N-1; i >= 0; i--) {
			for(int j = 0; j < N-i-1; j++) {
				sb.append(" ");
			}
			for(int j = 0; j < 2*i+1; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}