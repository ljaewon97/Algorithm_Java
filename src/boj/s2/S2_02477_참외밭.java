package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_02477_참외밭 {
	static int[] dr = {0,0,0,1,-1};
	static int[] dc = {0,1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int K = Integer.parseInt(br.readLine());
		
		int[][] points = new int[6][2];
		
		int r = 0, c = 0;
		
		for(int i = 1; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			r += x * dr[d];
			c += x * dc[d];
			
			points[i][0] = r;
			points[i][1] = c;
		}
		
		br.readLine();
		
		int S = 0;
		
		for(int i = 0; i < 6; i++) {
			S += points[i][0] * points[(i+1)%6][1] - points[(i+1)%6][0] * points[i][1];
		}
		
		System.out.println(K * Math.abs(S) / 2);
	}
}
