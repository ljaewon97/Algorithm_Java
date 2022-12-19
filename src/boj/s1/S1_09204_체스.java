package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_09204_체스 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int sr = st.nextToken().charAt(0) - 65;
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int er = st.nextToken().charAt(0) - 65;
			int ec = Integer.parseInt(st.nextToken()) - 1;
			
			if((sr+sc) % 2 != (er+ec) % 2) {
				sb.append("Impossible\n");
				continue;
			}
			
			if(sr == er && sc == ec) {
				sb.append(String.format("0 %c %d\n", sr+65, sc+1));
				continue;
			}
			
			if(check(sr, sc, er, ec)) {
				sb.append(String.format("1 %c %d %c %d\n", (char) (sr+65), sc+1, (char) (er+65), ec+1));
				continue;
			}
			
			int mr = -1, mc = -1, mod = (sr+sc) % 2;
			
			outer: for(int r = 0; r < 8; r++) {
				for(int c = 0; c < 8; c++) {
					if((r+c) % 2 == mod) {
						if(check(r, c, sr, sc) && check(r, c, er, ec)) {
							mr = r;
							mc = c;
							break outer;
						}
					}
				}
			}
			
			sb.append(String.format("2 %c %d %c %d %c %d\n", (char) (sr+65), sc+1, (char) (mr+65), mc+1, (char) (er+65), ec+1));
		}
		
		System.out.println(sb);
	}
	
	static boolean check(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2) == Math.abs(c1-c2);
	}
}
