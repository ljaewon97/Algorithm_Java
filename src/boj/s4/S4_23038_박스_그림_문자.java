package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_23038_박스_그림_문자 {
	static char[][] pic;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pic = new char[3*N][];
		
		for(int i = 0; i < 3*N; i++) {
			pic[i] = br.readLine().toCharArray();
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if((r+c) % 2 == 1) {
					pic[3*r+1][3*c+1] = '#';
					
					if(r > 0 && pic[3*r-1][3*c+1] == '#') pic[3*r][3*c+1] = '#';
					if(c > 0 && pic[3*r+1][3*c-1] == '#') pic[3*r+1][3*c] = '#';
					if(r < N-1 && pic[3*r+3][3*c+1] == '#') pic[3*r+2][3*c+1] = '#';
					if(c < M-1 && pic[3*r+1][3*c+3] == '#') pic[3*r+1][3*c+2] = '#';
				}
			}
		}
		
		for(int r = 0; r < 3*N; r++) {
			for(int c = 0; c < 3*M; c++) {
				sb.append(pic[r][c]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
