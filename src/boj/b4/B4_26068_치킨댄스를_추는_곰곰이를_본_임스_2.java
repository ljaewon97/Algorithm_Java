package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4_26068_치킨댄스를_추는_곰곰이를_본_임스_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		
		while(N-- > 0) {
			int d = Integer.parseInt(br.readLine().substring(2));
			
			if(d <= 90) ans++;
		}
		
		System.out.println(ans);
	}
}
