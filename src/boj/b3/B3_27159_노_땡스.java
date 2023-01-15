package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3_27159_노_땡스 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		int prev = 0;
		int min = 0;
		
		st = new StringTokenizer(br.readLine());
		while(N-- > 0) {
			int cur = Integer.parseInt(st.nextToken());
			
			if(cur == prev+1) {
				prev = cur;
			}
			else {
				ans += min;
				min = prev = cur;
			}
		}
		
		ans += min;
		
		System.out.println(ans);
	}
}
