package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_26646_알프스_케이블카 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		long ans = 0;
		
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		
		while(N-- > 1) {
			int b = Integer.parseInt(st.nextToken());
			ans += 2*a*a + 2*b*b;
			a = b;
		}
		
		System.out.println(ans);
	}
}
