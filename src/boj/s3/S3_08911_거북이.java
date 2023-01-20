package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_08911_거북이 {
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int minr, maxr, minc, maxc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			String move = br.readLine();
			int r = 0, c = 0, d = 0;
			minr = minc = maxr = maxc = 0;
			
			for(int i = 0; i < move.length(); i++) {
				char cmd = move.charAt(i);
				
				if(cmd == 'F') {
					r += dr[d];
					c += dc[d];
					setMinMax(r, c);
				}
				else if(cmd == 'B') {
					r -= dr[d];
					c -= dc[d];
					setMinMax(r, c);
				}
				else if(cmd == 'L') {
					d = (d+3)%4;
				}
				else if(cmd == 'R') {
					d = (d+1)%4;
				}
			}
			
			sb.append((maxr-minr)*(maxc-minc)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void setMinMax(int r, int c) {
		minr = Math.min(minr, r);
		maxr = Math.max(maxr, r);
		minc = Math.min(minc, c);
		maxc = Math.max(maxc, c);
	}
}
