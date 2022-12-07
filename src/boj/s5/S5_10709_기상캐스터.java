package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_10709_기상캐스터 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[][] cast = new int[H][W];
		
		for(int i = 0; i < H; i++) {
			String line = br.readLine();
			boolean c = false;
			int x = 0;
			
			for(int j = 0; j < W; j++) {
				if(line.charAt(j) == 'c') {
					cast[i][j] = x = 0;
					c = true;
				}
				else {
					if(c) cast[i][j] = ++x;
					else cast[i][j] = -1;
				}
				
				sb.append(cast[i][j]).append(" ");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
