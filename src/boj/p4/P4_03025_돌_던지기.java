package boj.p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P4_03025_돌_던지기 {
	static Reader in = new Reader();
	static char[][] map;
	static int[][] dp;
	static int[] fall;
	static int R, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		dp = new int[R][C];
		fall = new int[C];
		
		Arrays.fill(fall, R+1);
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			System.out.println(line);
			
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == 'X') fall[j] = Math.min(fall[j], i);
			}
		}
		
		System.out.println(Arrays.toString(fall));
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int col = Integer.parseInt(br.readLine());
			
			
		}
		
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
