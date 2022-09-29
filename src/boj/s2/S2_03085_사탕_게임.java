package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_03085_사탕_게임 {
	static char[][] map;
	static int N, ans = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			checkRow(i);
			checkCol(i);
		}
		
		for(int r = 0; r < N-1; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] != map[r+1][c]) {
					char temp = map[r][c];
					map[r][c] = map[r+1][c];
					map[r+1][c] = temp;
					
					checkRow(r);
					checkRow(r+1);
					checkCol(c);
					
					temp = map[r][c];
					map[r][c] = map[r+1][c];
					map[r+1][c] = temp;
				}
			}
		}
		
		for(int c = 0; c < N-1; c++) {
			for(int r = 0; r < N; r++) {
				if(map[r][c] != map[r][c+1]) {
					char temp = map[r][c];
					map[r][c] = map[r][c+1];
					map[r][c+1] = temp;
					
					checkCol(c);
					checkCol(c+1);
					checkRow(r);
					
					temp = map[r][c];
					map[r][c] = map[r][c+1];
					map[r][c+1] = temp;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void checkRow(int r) {
		int con = 1;
		
		for(int i = 1; i < N; i++) {
			if(map[r][i] == map[r][i-1]) {
				con++;
				ans = Math.max(ans, con);
			}
			else con = 1;
		}
	}
	
	static void checkCol(int c) {
		int con = 1;
		
		for(int i = 1; i < N; i++) {
			if(map[i][c] == map[i-1][c]) {
				con++;
				ans = Math.max(ans, con);
			}
			else con = 1;
		}
	}
}
