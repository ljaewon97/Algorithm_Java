package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P5_14939_불_끄기 {
	static int[] map, temp, bit;
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,-1,1};
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[10];
		temp = new int[10];
		bit = new int[11];
		
		for(int i = 0; i < 10; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < 10; j++) {
				if(line.charAt(j) == 'O') map[i] |= 1 << j;
			}
			
			bit[i] = 1 << i;
		}
		
		bit[10] = 1 << 10;
		
		for(int sw = 0; sw < bit[10]; sw++) {
			int cnt = 0;
			
			for(int i = 0; i < 10; i++) {
				temp[i] = map[i];
			}
			
			for(int c = 0; c < 10; c++) {
				if((sw & bit[c]) != 0) {
					cnt++;
					toggle(0, c);
					if(cnt > ans) break;
				}
			}
			
			if(cnt > ans) continue;
			
			outer: for(int r = 1; r < 10; r++) {
				for(int c = 0; c < 10; c++) {
					if((temp[r-1] & bit[c]) != 0) {
						cnt++;
						toggle(r, c);
						if(cnt > ans) break outer;
					}
				}
			}
			
			if(cnt > ans) continue;
			
			boolean flag = false;
			
			for(int c = 0; c < 10; c++) {
				if((temp[9] & bit[c]) != 0) {
					flag = true;
					break;
				}
			}
			
			ans = !flag ? Math.min(ans, cnt) : ans;
		}
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static void toggle(int r, int c) {
		for(int i = 0; i < 5; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 0 || nr >= 10 || nc < 0 || nc >= 10) continue;
			
			if((temp[nr] & bit[nc]) == 0) {
				temp[nr] |= bit[nc];
			}
			else {
				temp[nr] &= ~bit[nc];
			}
		}
	}
}
