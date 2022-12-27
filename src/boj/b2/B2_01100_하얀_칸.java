package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2_01100_하얀_칸 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int ans = 0;
		
		for(int i = 0; i < 8; i++) {
			String line = br.readLine();
			for(int j = 0; j < 8; j++) {
				if((i+j) % 2 == 0 && line.charAt(j) == 'F') ans++;
			}
		}
		
		System.out.println(ans);
	}
}
