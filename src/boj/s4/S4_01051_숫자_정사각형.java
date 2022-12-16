package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_01051_숫자_정사각형 {
	static char[][] map;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		
		for(int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		int max = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				for(int s = Math.min(N-r, M-c); s >= 1; s--) {
					if(check(r, c, s)) {
						max = Math.max(max, s);
						break;
					}
				}
			}
		}
		
		System.out.println(max * max);
	}
	
	static boolean check(int r, int c, int s) {
		return map[r][c] == map[r+s-1][c] && map[r+s-1][c] == map[r][c+s-1] && map[r][c+s-1] == map[r+s-1][c+s-1];
	}
}
