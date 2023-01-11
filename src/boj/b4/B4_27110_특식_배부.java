package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4_27110_특식_배부 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 3; i++) {
			int x = Integer.parseInt(st.nextToken());
			if(x <= N) ans += x;
			else ans += N;
		}
		
		System.out.println(ans);
	}
}
