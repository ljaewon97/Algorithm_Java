package codeforces.div2.round830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_Ugu {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			String s = br.readLine();
			
			if(n == 1) {
				sb.append("0\n");
				continue;
			}
			
			int cnt = 0;
			
			for(int i = 1; i < n; i++) {
				if(s.charAt(i-1) != s.charAt(i)) cnt++;
			}
			
			if(cnt != 0 && s.charAt(0) == '0') cnt--;
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
}
