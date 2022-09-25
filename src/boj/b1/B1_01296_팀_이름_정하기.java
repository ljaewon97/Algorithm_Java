package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_01296_팀_이름_정하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String y = br.readLine();
		int len = y.length();
		int yl = 0, yo = 0, yv = 0, ye = 0;
		for(int i = 0; i < len; i++) {
			char c = y.charAt(i);
			
			if(c == 'L') yl++;
			else if(c == 'O') yo++;
			else if(c == 'V') yv++;
			else if(c == 'E') ye++;
		}
		
		
		int N = Integer.parseInt(br.readLine());
		int max = -1;
		String winner = null;
		
		while(N-- > 0) {
			String name = br.readLine();
			len = name.length();
			
			int l = yl, o = yo, v = yv, e = ye;
			for(int i = 0; i < len; i++) {
				char c = name.charAt(i);
				
				if(c == 'L') l++;
				else if(c == 'O') o++;
				else if(c == 'V') v++;
				else if(c == 'E') e++;
			}
			
			int prob = (int) ((long) (l+o) * (l+v) * (l+e) * (o+v) * (o+e) * (v+e)) % 100;
			
			if(prob > max) {
				max = prob;
				winner = name;
			}
			else if(prob == max) {
				if(name.compareTo(winner) < 0) winner = name;
			}
		}
		
		System.out.println(winner);
	}
}
