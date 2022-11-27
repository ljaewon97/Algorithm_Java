package codeforces.div3.round828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			
			String line = br.readLine();
			
			if(c == 'g') {
				sb.append("0\n");
				continue;
			}
			
			char[] s = new char[2*n];
			
			for(int i = 0; i < n; i++) {
				s[i] = s[n+i] = line.charAt(i);
			}
			
			int ans = 0;
			boolean flag = false;
			int len = 0;
			
			for(int i = 2*n-1; i >= 0; i--) {
				if(!flag && s[i] == 'g') flag = true;
				if(!flag) continue;
				
				if(s[i] != 'g') {
					len++;
					if(s[i] == c) ans = Math.max(ans, len);
				}
				else {
					len = 0;
				}
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
}
