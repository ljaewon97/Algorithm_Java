package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_02563_색종이 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		boolean[][] paper = new boolean[100][100];
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			for(int r = R; r < R+10; r++) {
				for(int c = C; c < C+10; c++) {
					if(!paper[r][c]) {
						paper[r][c] = true;
					}
				}
			}
		}
		
		int area = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(paper[i][j]) {
					area++;
				}
			}
		}
		
		System.out.println(area);
	}
}
