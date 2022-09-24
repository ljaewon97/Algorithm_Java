package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_17614_369 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int ans = 0, num = 0, mod = 0;
		
		for(int i = 1; i <= N; i++) {
			num = i;
			
			while(num > 0) {
				mod = num % 10;
				if(mod != 0 && mod % 3 == 0) ans++;
				num /= 10;
			}
		}
		
		System.out.println(ans);
	}
}
