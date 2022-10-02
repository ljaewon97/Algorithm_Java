package codeforces.round820_div3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_Decode_String {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int q = Integer.parseInt(br.readLine());
		
		while(q-- > 0) {
			int n = Integer.parseInt(br.readLine());
			String t = br.readLine();
			
			for(int i = 0; i < n; i++) {
				if(i+2 < n && t.charAt(i+2) == '0' && !(i+3 < n && t.charAt(i+3) == '0')) {
					int temp = Integer.parseInt(t.substring(i, i+2));
					sb.append((char) (96+temp));
					i += 2;
				}
				else {
					sb.append((char) (96+(t.charAt(i)-'0')));
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
