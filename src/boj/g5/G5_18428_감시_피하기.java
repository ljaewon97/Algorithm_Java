package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class G5_18428_감시_피하기 {
	static char[][] map;
	static List<int[]> teachers = new ArrayList<>();
	static int N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static String ans = "NO";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(2*j);
				
				if(map[i][j] == 'T') teachers.add(new int[] {i, j});
			}
		}
		
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	static void comb(int nth, int start) {
		if(ans.equals("YES")) return;
		
		if(nth == 3) {
			if(check()) ans = "YES";
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] != 'X' || i*N+j < start) continue;
				
				map[i][j] = 'O';
				comb(nth+1, i*N+j+1);
				map[i][j] = 'X';
			}
		}
	}
	
	static boolean check() {
		for(int[] teacher: teachers) {
			int r = teacher[0];
			int c = teacher[1];
			
			for(int i = 0; i < 4; i++) {
				int step = 1;
				
				while(true) {
					int nr = r + dr[i] * step;
					int nc = c + dc[i] * step;
					
					if(!isIn(nr, nc) || map[nr][nc] == 'O') break;
					
					if(map[nr][nc] == 'S') return false;
					
					step++;
				}
			}
		}
		
		return true;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
