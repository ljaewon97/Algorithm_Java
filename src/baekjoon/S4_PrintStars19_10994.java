package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S4_PrintStars19_10994 {
	static char[][] star;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		star = new char[4*N-3][4*N-3];
		printStar(0, 0, N);
		for(int i = 0; i < 4*N-3; i++) {
			for(int j = 0; j < 4*N-3; j++) {
				sb.append(star[i][j] == '*' ? "*" : " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void printStar(int r, int c, int n) {
		for(int i = 0; i < 4*n-3; i++) {
			if(i == 0 || i == 4*(n-1)) {
				for(int j = 0; j < 4*n-3; j++) {
					star[r+i][c+j] = '*';
				}
			}
			else {
				star[r+i][c] = '*';
				star[r+i][c+4*(n-1)] = '*';
			}
		}
		
		if(n > 1) {
			printStar(r+2, c+2, n-1);
		}
	}
}