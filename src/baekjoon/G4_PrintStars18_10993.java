package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_PrintStars18_10993 {
	static char[][] star;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int size = (int) Math.pow(2, N) - 1;
		star = new char[size][2*size-1];
		if(N % 2 == 1) {
			printStar(size-1, 0, size-1, 2*size-2, 0, size-1, N);
		}
		else {
			printStar(0, 0, 0, 2*size-2, size-1, size-1, N);
		}
		for(int i = 0; i < star.length; i++) {
			if(N % 2 == 1) {
				for(int j = 0; j < size+i; j++) {
					sb.append(star[i][j]=='*'?'*':' ');
				}
			}
			else {
				for(int j = 0; j < 2*size-1-i; j++) {
					sb.append(star[i][j]=='*'?'*':' ');
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void printStar(int r1, int c1, int r2, int c2, int r3, int c3, int n) {
		drawLine(r1, c1, r2, c2);
		drawLine(r2, c2, r3, c3);
		drawLine(r3, c3, r1, c1);
		
		int dr = (int) Math.pow(-1, n);
		if(n == 1) {
			star[r1][c1] = '*';
		}
		else {
			printStar((r1+r3)/2, (c1+c3)/2+1, (r2+r3)/2, (c2+c3)/2-1, (r1+r2)/2+dr, (c1+c2)/2, n-1);
		}
	}
	
	static void drawLine(int r1, int c1, int r2, int c2) {
		int dr = r2 >= r1 ? (r2 > r1 ? 1 : 0) : -1;
		int dc = c2 >= c1 ? (c2 > c1 ? 1 : 0) : -1;
		for(int r = r1, c = c1, i = 0; i < Math.max(Math.abs(r2-r1), Math.abs(c2-c1)) ; r += dr, c += dc, i++) {
			star[r][c] = '*';
		}
	}
}