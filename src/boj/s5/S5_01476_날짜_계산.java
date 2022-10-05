package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_01476_날짜_계산 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int E = Integer.parseInt(st.nextToken()); 
		int S = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken());
		
		int y = 1, e = 1, s = 1, m = 1;
		
		while(true) {
			if(e == E && s == S && m == M) break;
			
			y++;
			e = e == 15 ? 1 : e + 1;
			s = s == 28 ? 1 : s + 1;
			m = m == 19 ? 1 : m + 1;
		}
		
		System.out.println(y);
	}
}
