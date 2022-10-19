package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_14600_샤워실_바닥_깔기_Small {
	static int[][] floor;
	static int[] pow;
	static int K, n = 1, hr, hc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		pow = new int[K+1];
		pow[0] = 1;
		
		for(int i = 1; i <= K; i++) {
			pow[i] = 2 * pow[i-1];
		}
		
		floor = new int[pow[K]][pow[K]];
		
		st = new StringTokenizer(br.readLine());
		hr = Integer.parseInt(st.nextToken()) - 1;
		hc = Integer.parseInt(st.nextToken()) - 1;
		
		floor[hr][hc] = -1;
		
		fill(K, 0, 0);
		
		for(int c = pow[K]-1; c >= 0; c--) {
			for(int r = 0; r < pow[K]; r++) {
				sb.append(floor[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void fill(int k, int r, int c) {
		if(k == 0) return;
		
		if(hr-r < pow[k-1]) {
			if(hc-c < pow[k-1]) {
				recur(k, r+pow[k]-1, c+pow[k]-1, 0);
				fill(k-1, r, c);
			}
			else {
				recur(k, r+pow[k]-1, c, 1);
				fill(k-1, r, c+pow[k-1]);
			}
		}
		else {
			if(hc-c < pow[k-1]) {
				recur(k, r, c+pow[k]-1, 3);
				fill(k-1, r+pow[k-1], c);
			}
			else {
				recur(k, r, c, 2);
				fill(k-1, r+pow[k-1], c+pow[k-1]);
			}
		}
	}
	
	static void recur(int k, int r, int c, int d) {
		if(k == 1) {
			if(d == 0) floor[r][c-1] = floor[r-1][c] = floor[r][c] = n++;
			else if(d == 1) floor[r][c+1] = floor[r-1][c] = floor[r][c] = n++;
			else if(d == 2) floor[r][c+1] = floor[r+1][c] = floor[r][c] = n++;
			else if(d == 3) floor[r][c-1] = floor[r+1][c] = floor[r][c] = n++;
			return;
		}
		
		if(d == 0) {
			recur(k-1, r, c-pow[k]+1, 1);
			recur(k-1, r-pow[k-2], c-pow[k-2], 0);
			recur(k-1, r, c, 0);
			recur(k-1, r-pow[k]+1, c, 3);
		}
		else if(d == 1) {
			recur(k-1, r, c+pow[k]-1, 0);
			recur(k-1, r-pow[k-2], c+pow[k-2], 1);
			recur(k-1, r, c, 1);
			recur(k-1, r-pow[k]+1, c, 2);
		}
		else if(d == 2) {
			recur(k-1, r, c+pow[k]-1, 3);
			recur(k-1, r+pow[k-2], c+pow[k-2], 2);
			recur(k-1, r, c, 2);
			recur(k-1, r+pow[k]-1, c, 1);
		}
		else if(d == 3) {
			recur(k-1, r, c-pow[k]+1, 2);
			recur(k-1, r+pow[k-2], c-pow[k-2], 3);
			recur(k-1, r, c, 3);
			recur(k-1, r+pow[k]-1, c, 0);
		}
	}
}
