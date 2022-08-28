package boj.r3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class R3_18789_814_2 {
	static int[] dr = {-1,1,0,0,-1,-1,1,1};
	static int[] dc = {0,0,-1,1,-1,1,-1,1};
	static boolean[][] visited;
	static SecureRandom rd = new SecureRandom();
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		String str = "85846645930360\r\n" + 
				"04847289830843\r\n" + 
				"17976520711465\r\n" + 
				"83493253342569\r\n" + 
				"41610189035473\r\n" + 
				"70756798291120\r\n" + 
				"20357656176901\r\n" + 
				"86304092628445";
		
		BufferedReader br = new BufferedReader(new StringReader(str));
		
		int[][] arr = new int[8][14];
		
		for(int i = 0; i < 8; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < 14; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}
		
//		int[][] arr = new int[8][14];
//		
//		for(int i = 0; i < 8; i++) {
//			for(int j = 0; j < 14; j++) {
//				arr[i][j] = rd.nextInt(10);
//			}
//		}
		
//		visited = new boolean[8][14];
//		System.out.println(grade(arr));
//		for(boolean[] row: visited) {
//			System.out.println(Arrays.toString(row));
//		}
//		for(int i = 0; i < 8; i++) {
//			for(int j = 0; j < 14; j++) {
//				sb.append(arr[i][j]);
//			}
//			sb.append("\n");
//		}
//		System.out.println(sb);
		
		//bruteforce(arr);
		
		SA(arr, 1105);
	}
	
	static void bruteforce(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		int original = grade(arr);
		int max = original;
		int[][] maxArr = new int[8][14];
		
		for(int r = 0; r < 8; r++) {
			for(int c = 0; c < 14; c++) {
				int temp = arr[r][c];
				
				for(int i = 0; i < 10; i++) {
					arr[r][c] = i;
					int tempScore = grade(arr);
					
					if(tempScore > max) {
						max = tempScore;
						for(int row = 0; row < 8; row++) {
							maxArr[row] = arr[row].clone();
						}
					}
				}
				
				arr[r][c] = temp;
			}
		}
		
		System.out.println(max);
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 14; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void SA(int[][] arr, int lim) {
		StringBuilder sb = new StringBuilder();
		
		double k = 10;
		double T = 1000;
		double fallingRate = 0.9999999;
		int iter = 1;
		
		while(true) {
			int E1 = grade(arr);
			
			int unused = countUnused();
			int randR = 0;
			int randC = 0;
			
			if(unused == 0) {
				randR = rd.nextInt(8);
				randC = rd.nextInt(14);
			}
			else {
				while(true) {
					randR = rd.nextInt(8);
					randC = rd.nextInt(14);
					
					if(!visited[randR][randC]) break;
				}
			}

			int randV = 0;
			
			while(true) {
				randV = rd.nextInt(10);
				
				int cnt = 0;
				for(int i = 0; i < 8; i++) {
					int nr = randR + dr[i];
					int nc = randC + dc[i];
					
					if(isIn(nr, nc) && arr[nr][nc] == randV) cnt++;
				}
				
				if(cnt < 2) break;
			}
			
			
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
			
			if(E2 > lim || T < k) {
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
	
	static int countUnused() {
		int cnt = 0;
		
		for(int r = 0; r < 8; r++) {
			for(int c = 0; c < 14; c++) {
				if(!visited[r][c]) cnt++;
			}
		}
		
		return cnt;
	}
	
	// 채점 함수
	static int grade(int[][] arr) {
		int num = 1;
		visited = new boolean[8][14];
		
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
				visited[nr][nc] = true;
				if(dfs(arr, digits, len, nr, nc, dist+1)) return true;
				visited[nr][nc] = false;
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

1105

85846645930360
04847289830843
17976520711465
83493253342569
41610189035473
70756798291120
20357656176901
86304092628445


*/