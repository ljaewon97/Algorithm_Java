package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S4_20125_쿠키의_신체_측정 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		boolean flag = false, be = false;
		int c = -1, ar = -1, hr = -1, hc = -1, l = 0, r = 0, bodyend = 0, lf = 0, rf = 0;
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				if(!flag && line.charAt(j) == '*') {
					flag = true;
					c = j;
					ar = i+1;
					hr = i+2;
					hc = j+1;
				}
				else if(i == ar) {
					if(line.charAt(j) == '*' && j < c) l++;
					else if(line.charAt(j) == '*' && j > c) r++;
				}
				else if(flag && !be && j == c && line.charAt(j) == '_') {
					bodyend = i;
					be = true;
				}
				else if(flag && j == c-1 && line.charAt(j) == '*') lf = Math.max(lf, i);
				else if(flag && j == c+1 && line.charAt(j) == '*') rf = Math.max(rf, i);
			}
		}
		
		sb.append(hr).append(" ").append(hc).append("\n");
		sb.append(l).append(" ");
		sb.append(r).append(" ");
		sb.append(bodyend-ar-1).append(" ");
		sb.append(lf-bodyend+1).append(" ");
		sb.append(rf-bodyend+1).append(" ");
		
		System.out.println(sb);
	}
}
