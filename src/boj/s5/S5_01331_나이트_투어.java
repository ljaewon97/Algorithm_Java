package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_01331_a {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[][] visited = new boolean[6][6];
		String ans = "Valid";
		
		String line = br.readLine();
		int sr = line.charAt(0) - 'A';
		int sc = line.charAt(1) - '1';
		visited[sr][sc] = true;
		
		int pr = sr;
		int pc = sc;
		
		for(int i = 0; i < 36; i++) {
			int cr = -1, cc = -1;
			
			if(i == 35) {
				cr = sr;
				cc = sc;
			}
			else {
				line = br.readLine();
				cr = line.charAt(0) - 'A';
				cc = line.charAt(1) - '1';
				
				if(visited[cr][cc]) {
					ans = "Invalid";
					break;
				}
			}
			
			int rdiff = Math.abs(cr - pr);
			int cdiff = Math.abs(cc - pc);
			
			if((rdiff == 2 && cdiff == 1) || (rdiff == 1 && cdiff == 2)) {
				visited[cr][cc] = true;
				pr = cr;
				pc = cc;
			}
			else {
				ans = "Invalid";
				break;
			}
		}
		
		System.out.println(ans);
	}
}
