package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_26071_오락실에_간_총총이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int minr = N-1;
		int maxr = 0;
		int minc = N-1;
		int maxc = 0;
		
		for(int r = 0; r < N; r++) {
			String line = br.readLine();
			for(int c = 0; c < N; c++) {
				if(line.charAt(c) == 'G') {
					minr = Math.min(minr, r);
					maxr = Math.max(maxr, r);
					minc = Math.min(minc, c);
					maxc = Math.max(maxc, c);
				}
			}
		}
		
		int R = minr != maxr ? Math.min(minr, N-1-maxr) : 0;
		int C = minc != maxc ? Math.min(minc, N-1-maxc) : 0;
		
		System.out.println(maxr-minr+maxc-minc+R+C);
	}
}
