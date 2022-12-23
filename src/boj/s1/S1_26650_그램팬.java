package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_26650_그램팬 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] S = br.readLine().toCharArray();
		
		long ans = 0;
		int a = 0, z = 0, prev = -1;
		
		for(int i = 0; i < S.length; i++) {
			if(S[i] != prev && S[i] != prev+1) {
				ans += (long) a * z;
				a = z = 0;
			}
			
			if(S[i] == 'A') a++;
			else if(S[i] == 'Z') z++;
			prev = S[i];
		}
		
		ans += (long) a * z;
		
		System.out.println(ans);
	}
}
