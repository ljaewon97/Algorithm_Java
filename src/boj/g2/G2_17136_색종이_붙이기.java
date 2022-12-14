package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G2_17136_색종이_붙이기 {
	static boolean[][] paper = new boolean[10][10];
	static int[] count = new int[6];
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 10; i++) {
			String line = br.readLine();
			for(int j = 0; j < 10; j++) {
				paper[i][j] = line.charAt(2*j) == '1' ? true : false;
			}
		}
		
		solve(0, 0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static void solve(int coord, int cnt) {
		if(coord >= 100) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if(cnt >= ans) return;
		
		int r = coord / 10;
		int c = coord % 10;
		
		if(paper[r][c]) {
			for(int s = 5; s >= 1; s--) {
				if(count[s] < 5 && check(r, c, s)) {
					count[s]++;
					visit(r, c, s, false);
					solve(coord+s, cnt+1);
					visit(r, c, s, true);
					count[s]--;
				}
			}
		}
		else {
			solve(coord+1, cnt);
		}
	}
	
	static boolean check(int r, int c, int s) {
		if(r+s > 10 || c+s > 10) return false;
		
		for(int i = r; i < r+s; i++) {
			for(int j = c; j < c+s; j++) {
				if(!paper[i][j]) return false;
			}
		}
		
		return true;
	}
	
	static void visit(int r, int c, int size, boolean x) {
		for(int i = r; i < r+size; i++) {
			for(int j = c; j < c+size; j++) {
				paper[i][j] = x;
			}
		}
	}
}
