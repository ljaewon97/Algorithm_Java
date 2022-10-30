package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_05525_IOIOI {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String S = br.readLine();
		
		int i = 0, n = 0, ans = 0;
		
		while(i < M) {
			if(i+2 < M && S.charAt(i) == 'I' && S.charAt(i+1) == 'O' && S.charAt(i+2) == 'I') {
				n++;
				i += 2;
			}
			else {
				if(n >= N) ans += n-N+1;
				n = 0;
				i++;
			}
		}
		
		System.out.println(ans);
	}
}
