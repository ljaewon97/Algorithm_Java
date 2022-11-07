package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_05533_유니크 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] score = new int[N][3];
		boolean[][] used = new boolean[3][101];
		boolean[][] zero = new boolean[3][101];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
				if(used[j][score[i][j]]) zero[j][score[i][j]] = true;
				used[j][score[i][j]] = true;
			}
		}
		
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < 3; j++) {
				if(!zero[j][score[i][j]]) sum += score[i][j];
			}
			sb.append(sum).append("\n");
		}
		
		System.out.println(sb);
	}
}
