package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P5_05373_큐빙 {
	static int T, N;
	static char[][][] cube = new char[6][3][3];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			initialize();
			
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				char[] command = st.nextToken().toCharArray();
				char side = command[0];
				char dir = command[1];
				
				if(dir == '+') {
					rotate(side);
				}
				else {
					for(int j = 0; j < 3; j++) {
						rotate(side);
					}
				}
			}
			
			for(int r = 0; r < 3; r++) {
				for(int c = 0; c < 3; c++) {
					sb.append(cube[0][r][c]);
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void initialize() {
		char[] color = {'w', 'y', 'r', 'o', 'g', 'b'};
		
		for(int s = 0; s < 6; s++) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					cube[s][i][j] = color[s];
				}
			}
		}
	}
	
	static void rotateSide(char side) {
		int idx = 0;
		switch(side) {
		case 'U': idx = 0; break;
		case 'D': idx = 1; break;
		case 'F': idx = 2; break;
		case 'B': idx = 3; break;
		case 'L': idx = 4; break;
		case 'R': idx = 5; break;
		}
		
		char[][] temp = new char[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				temp[i][j] = cube[idx][2-j][i];
			}
		}
		
		cube[idx] = temp;
	}
	
	static void rotate(char side) {
		if(side == 'L') {
			char[] temp = {cube[0][0][0], cube[0][1][0], cube[0][2][0]};
			
			cube[0][0][0] = cube[3][0][0];
			cube[0][1][0] = cube[3][1][0];
			cube[0][2][0] = cube[3][2][0];
			
			cube[3][0][0] = cube[1][0][0];
			cube[3][1][0] = cube[1][1][0];
			cube[3][2][0] = cube[1][2][0];
			
			cube[1][0][0] = cube[2][0][0];
			cube[1][1][0] = cube[2][1][0];
			cube[1][2][0] = cube[2][2][0];
			
			cube[2][0][0] = temp[0];
			cube[2][1][0] = temp[1];
			cube[2][2][0] = temp[2];
			
			rotateSide(side);
		}
		else if(side == 'R') {
			char[] temp = {cube[0][0][2], cube[0][1][2], cube[0][2][2]};
			
			cube[0][0][2] = cube[2][0][2];
			cube[0][1][2] = cube[2][1][2];
			cube[0][2][2] = cube[2][2][2];
			
			cube[2][0][2] = cube[1][0][2];
			cube[2][1][2] = cube[1][1][2];
			cube[2][2][2] = cube[1][2][2];
			
			cube[1][0][2] = cube[3][0][2];
			cube[1][1][2] = cube[3][1][2];
			cube[1][2][2] = cube[3][2][2];
			
			cube[3][0][2] = temp[0];
			cube[3][1][2] = temp[1];
			cube[3][2][2] = temp[2];
			
			rotateSide(side);
		}
		else if(side == 'U') {
			char[] temp = {cube[2][0][0], cube[2][0][1], cube[2][0][2]};
			
			cube[2][0][0] = cube[5][0][0];
			cube[2][0][1] = cube[5][0][1];
			cube[2][0][2] = cube[5][0][2];
			
			cube[5][0][0] = cube[3][2][2];
			cube[5][0][1] = cube[3][2][1];
			cube[5][0][2] = cube[3][2][0];
			
			cube[3][2][2] = cube[4][0][0];
			cube[3][2][1] = cube[4][0][1];
			cube[3][2][0] = cube[4][0][2];
			
			cube[4][0][0] = temp[0];
			cube[4][0][1] = temp[1];
			cube[4][0][2] = temp[2];
			
			rotateSide(side);
		}
		else if(side == 'D') {
			char[] temp = {cube[2][2][0], cube[2][2][1], cube[2][2][2]};
			
			cube[2][2][0] = cube[4][2][0];
			cube[2][2][1] = cube[4][2][1];
			cube[2][2][2] = cube[4][2][2];
			
			cube[4][2][0] = cube[3][0][2];
			cube[4][2][1] = cube[3][0][1];
			cube[4][2][2] = cube[3][0][0];
			
			cube[3][0][2] = cube[5][2][0];
			cube[3][0][1] = cube[5][2][1];
			cube[3][0][0] = cube[5][2][2];
			
			cube[5][2][0] = temp[0];
			cube[5][2][1] = temp[1];
			cube[5][2][2] = temp[2];
			
			rotateSide(side);
		}
		else if(side == 'F') {
			char[] temp = {cube[0][2][0], cube[0][2][1], cube[0][2][2]};
			
			cube[0][2][0] = cube[4][2][2];
			cube[0][2][1] = cube[4][1][2];
			cube[0][2][2] = cube[4][0][2];
			
			cube[4][2][2] = cube[1][0][2];
			cube[4][1][2] = cube[1][0][1];
			cube[4][0][2] = cube[1][0][0];
			
			cube[1][0][2] = cube[5][0][0];
			cube[1][0][1] = cube[5][1][0];
			cube[1][0][0] = cube[5][2][0];
			
			cube[5][0][0] = temp[0];
			cube[5][1][0] = temp[1];
			cube[5][2][0] = temp[2];
			
			rotateSide(side);
		}
		else if(side == 'B') {
			char[] temp = {cube[0][0][0], cube[0][0][1], cube[0][0][2]};
			
			cube[0][0][0] = cube[5][0][2];
			cube[0][0][1] = cube[5][1][2];
			cube[0][0][2] = cube[5][2][2];
			
			cube[5][0][2] = cube[1][2][2];
			cube[5][1][2] = cube[1][2][1];
			cube[5][2][2] = cube[1][2][0];
			
			cube[1][2][2] = cube[4][2][0];
			cube[1][2][1] = cube[4][1][0];
			cube[1][2][0] = cube[4][0][0];
			
			cube[4][2][0] = temp[0];
			cube[4][1][0] = temp[1];
			cube[4][0][0] = temp[2];
			
			rotateSide(side);
		}
	}
}
