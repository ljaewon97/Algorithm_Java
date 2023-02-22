package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2_27494_2023년은_검은_토끼의_해 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		
		for(int i = 2023; i <= N; ++i) {
			boolean f1 = false, f2 = false, f3 = false, f4 = false;
			int x = i;
			
			while(x > 0) {
				int d = x % 10;
				if(!f4 && d == 3) f4 = true;
				else if(f4 && !f3 && d == 2) f3 = true;
				else if(f3 && !f2 && d == 0) f2 = true;
				else if(f2 && !f1 && d == 2) f1 = true;
				x /= 10;
			}
			
			if(f1 && f2 && f3 && f4) ++ans;
		}
		
		System.out.println(ans);
	}
}
