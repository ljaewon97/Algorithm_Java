

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_PrintStars23_13015 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= 2*N-1; i++) {
			if(i == 1 || i == 2*N-1) {
				for(int j = 0; j < N; j++) {
					sb.append("*");
				}
				for(int j = 0; j < 2*N-3; j++) {
					sb.append(" ");
				}
				for(int j = 0; j < N; j++) {
					sb.append("*");
				}
			}
			else {
				for(int j = 1; j <= 3*N-2+Math.abs(N-i); j++) {
					if(Math.abs(2*N-1-j) == Math.abs(N-i) || Math.abs(2*N-1-j) == Math.abs(N-i)+N-1) {
						sb.append("*");
					}
					else {
						sb.append(" ");
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}