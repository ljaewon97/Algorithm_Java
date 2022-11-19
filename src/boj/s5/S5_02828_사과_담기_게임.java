package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_02828_사과_담기_게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int J = Integer.parseInt(br.readLine());
		
		int l = 1;
		int r = M;
		int ans = 0;
		
		while(J-- > 0) {
			int x = Integer.parseInt(br.readLine());
			
			if(x < l) {
				int t = l-x;
				ans += t;
				l -= t;
				r -= t;
			}
			else if(r < x) {
				int t = x-r;
				ans += t;
				l += t;
				r += t;
			}
		}
		
		System.out.println(ans);
	}
}
