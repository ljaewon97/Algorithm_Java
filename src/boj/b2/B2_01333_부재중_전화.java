package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2_01333_부재중_전화 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int songEnd = N*L + 5*(N-1);
		
		int ans = 0;
		int t = D;
		while(true) {
			if(t < songEnd) {
				if(t % (L+5) >= L) {
					ans = t;
					break;
				}
			}
			else {
				ans = t;
				break;
			}
			t += D;
		}
		
		System.out.println(ans);
	}
}
