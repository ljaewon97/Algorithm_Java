package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_26594_ZOAC_5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		char f = str.charAt(0);
		
		int ans = 0;
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == f) ans++;
			else break;
		}
		
		System.out.println(ans);
	}
}
