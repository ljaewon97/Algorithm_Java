package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_01074_Z {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		
		while(N > 0) {
			if(r >= Math.pow(2, N-1)) {
				r %= Math.pow(2, N-1);
				ans += 2 * Math.pow(Math.pow(2, N-1), 2);
			}
			
			if(c >= Math.pow(2, N-1)) {
				c %= Math.pow(2, N-1);
				ans += Math.pow(Math.pow(2, N-1), 2);
			}
			
			N -= 1;
		}
		
		System.out.println(ans);
	}
}
