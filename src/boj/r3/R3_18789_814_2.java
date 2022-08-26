package boj.r3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class R3_18789_814_2 {
	static int[] dr = {-1,1,0,0,-1,-1,1,1};
	static int[] dc = {0,0,-1,1,-1,1,-1,1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		
//		String str = "00000000000000\r\n" + 
//				"00000000000000\r\n" + 
//				"00000000000000\r\n" + 
//				"00000000000000\r\n" + 
//				"00000000000000\r\n" + 
//				"00000000000000\r\n" + 
//				"00000000000000\r\n" + 
//				"00000000000000";
//		
//		BufferedReader br = new BufferedReader(new StringReader(str));
//		
//		int[][] arr = new int[8][14];
//		
//		for(int i = 0; i < 8; i++) {
//			String line = br.readLine();
//			
//			for(int j = 0; j < 14; j++) {
//				arr[i][j] = line.charAt(j) - '0';
//			}
//		}
		
		SecureRandom rd = new SecureRandom();
		
		int[][] arr = new int[8][14];
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 14; j++) {
				arr[i][j] = rd.nextInt(10);
			}
		}
		
		System.out.println(grade(arr));
		
		
		double k = 1;
		double T = 100000;
		double fallingRate = 0.99995;
		int iter = 1;
		
		while(true) {
			int E1 = grade(arr);
			
//			int randR = -1, randC = -1;
//			
//			while(true) {
//				randR = rd.nextInt(8);
//				randC = rd.nextInt(14);
//				
//				if(arr[randR][randC] == 0) break;
//			}
			
			int randR = rd.nextInt(8);
			int randC = rd.nextInt(14);
			int randV = rd.nextInt(10);
			
			int[][] narr = new int[8][14];
			
			for(int r = 0; r < 8; r++) {
				for(int c = 0; c < 14; c++) {
					narr[r][c] = arr[r][c];
				}
			}
			
			narr[randR][randC] = randV;
			
			int E2 = grade(narr);
			
			System.out.printf("%d %d %d\n", iter, E1, E2);
			
			double p = Math.exp((E1-E2)/(k*T));
			
			if(p > rd.nextDouble()) arr = narr;
			
			T *= fallingRate;
			iter++;
			rd.setSeed(E2);
			
			if(E2 > 500 || T < k) {
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 14; j++) {
						sb.append(narr[i][j]);
					}
					sb.append("\n");
				}
				
				System.out.println(sb);
				break;
			}
		}
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


/*

505

32632694182951
27770657263874
13343850249252
29614781921041
03641801057803
35607633748298
96291849594537
99891829371529

*/