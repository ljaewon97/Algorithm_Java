package boj.g4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_02448_별_찍기_11 {
	static char[][] star;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		star = new char[N][2*N-1];
		printStar(0, N-1, N);
		for(int i = 0; i < star.length; i++) {
			for(int j = 0; j < star[0].length; j++) {
				sb.append(star[i][j]=='*'?'*':' ');
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void printStar(int r, int c, int n) {
		if(n == 3) {
			star[r][c] = '*';
			star[r+1][c-1] = '*';
			star[r+1][c+1] = '*';
			for(int i = -2; i < 3; i++) star[r+2][c+i] = '*';
		}
		else {
			printStar(r, c, n/2);
			printStar(r+n/2, c-n/2, n/2);
			printStar(r+n/2, c+n/2, n/2);
		}
	}
}