package boj.r3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class R3_18789_814_2 {
	static int[] dr = {-1,1,0,0,-1,-1,1,1};
	static int[] dc = {0,0,-1,1,-1,1,-1,1};
	
	public static void main(String[] args) throws IOException {
		String str = "10203344536473\r\n" + 
				"01020102010201\r\n" + 
				"00000000008390\r\n" + 
				"00000000000400\r\n" + 
				"00000000000000\r\n" + 
				"55600000000089\r\n" + 
				"78900066000089\r\n" + 
				"00000789000077";
		
		BufferedReader br = new BufferedReader(new StringReader(str));
		
		int[][] arr = new int[8][14];
		
		for(int i = 0; i < 8; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < 14; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}
		
		System.out.println(grade(arr));
	}
	
	// 채점 함수
	static int grade(int[][] arr) {
		int num = 1;
		
		while(true) {
			int target = num;
			List<Integer> digits = new ArrayList<>();
			boolean found = false;
			
			while(target > 0) {
				digits.add(target % 10);
				target /= 10;
			}
			
			int len = digits.size();
			int first = digits.get(len-1);
			
			outer: for(int r = 0; r < 8; r++) {
				for(int c = 0; c < 14; c++) {
					if(arr[r][c] == first) {
						if(dfs(arr, digits, len, r, c, 1)) {
							found = true;
							break outer;
						}
					}
				}
			}
			
			if(found) num++;
			else break;
		}
		
		return num - 1;
	}
	
	static boolean dfs(int[][] arr, List<Integer> digits, int len, int r, int c, int dist) {
		if(dist == len) return true;
		
		int next = digits.get(len-1-dist);
		
		for(int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isIn(nr, nc) && arr[nr][nc] == next) {
				if(dfs(arr, digits, len, nr, nc, dist+1)) return true;
			}
		}
		
		return false;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < 8 && 0 <= c && c < 14;
	}
}
