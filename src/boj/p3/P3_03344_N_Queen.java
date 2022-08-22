package boj.p3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P3_03344_N_Queen {
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		putQueens(N);
		
		System.out.println(sb);
	}
	
	static void putQueens(int n) {
		if(n % 6 == 2) {
			for(int i = 2; i <= n; i += 2) {
				sb.append(i).append("\n");
			}
			
			sb.append(3).append("\n");
			sb.append(1).append("\n");
			
			for(int i = 7; i <= n; i += 2) {
				sb.append(i).append("\n");
			}
			
			sb.append(5).append("\n");
		}
		else if(n % 6 == 3) {
			for(int i = 4; i <= n; i += 2) {
				sb.append(i).append("\n");
			}
			
			sb.append(2).append("\n");
			
			for(int i = 5; i <= n; i += 2) {
				sb.append(i).append("\n");
			}
			
			sb.append(1).append("\n");
			sb.append(3).append("\n");
		}
		else {
			for(int i = 2; i <= n; i += 2) {
				sb.append(i).append("\n");
			}
			
			for(int i = 1; i <= n; i += 2) {
				sb.append(i).append("\n");
			}
		}
	}
}
