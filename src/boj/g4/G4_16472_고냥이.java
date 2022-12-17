package boj.g4;

import java.io.IOException;

public class G4_16472_고냥이 {
	public static void main(String[] args) throws IOException {
		int N = 0, c = 0;
		
		while((c = System.in.read()) > 32) {
			N = (N << 3) + (N << 1) + (c & 15);
		}
		
		while((c = System.in.read()) <= 32);
		
		int[] S = new int[100000];
		int[] count = new int[26];
		int alpha = 0, i = 0, j = 0, ans = 0;
		
		do {
			if(++count[(S[j]=c)-97] == 1) alpha++;
			
			if(alpha <= N) ans = Math.max(ans, j-i+1);
			else {
				while(i < j && alpha > N) {
					if(--count[S[i]-97] == 0) alpha--;
					i++;
				}
				
				ans = Math.max(ans, j-i+1);
			}
			
			j++;
		} while((c = System.in.read()) > 32);
		
		System.out.println(ans);
	}
}
