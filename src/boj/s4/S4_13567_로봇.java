package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_13567_로봇 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int r = 0, c = 0, d = 0;
		boolean valid = true;
		
		int[] dr = {0,1,0,-1};
		int[] dc = {1,0,-1,0};
		
		while(n-- > 0) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int x = Integer.parseInt(st.nextToken());
			
			if(!valid) continue;
			
			if(cmd.equals("MOVE")) {
				int nr = r + x * dr[d];
				int nc = c + x * dc[d];
				
				if(nr < 0 || nr >= M || nc < 0 || nc >= M) {
					valid = false;
				}
				else {
					r = nr;
					c = nc;
				}
			}
			else {
				if(x == 0) d = (d + 1) % 4;
				else d = (d + 3) % 4;
			}
		}
		
		if(valid) {
			System.out.println(c + " " + r);
		}
		else {
			System.out.println(-1);
		}
	}
}
