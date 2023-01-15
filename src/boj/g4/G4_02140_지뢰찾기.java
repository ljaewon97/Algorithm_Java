package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class G4_02140_지뢰찾기 {
	static Queue<Integer> temp = new LinkedList<>();
	static char[][] map;
	static byte[][] mine;
	static int N, ans;
	static int[] dr = {-1,1,0,0,-1,-1,1,1};
	static int[] dc = {0,0,-1,1,-1,1,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][];
		mine = new byte[N][N];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		if(N <= 2) {
			System.out.println(0);
			return;
		}
		else if(N == 3) {
			System.out.println(map[0][0] == '1' ? 1 : 0);
			return;
		}
		
		ans = (N-4) * (N-4);
		
		if(map[0][0] == '1') { mine[1][1] = 2; ans++; }
		else mine[1][1] = 1;
		
		if(map[N-1][0] == '1') { mine[N-2][1] = 2; ans++; }
		else mine[N-2][1] = 1;
		
		if(map[0][N-1] == '1') { mine[1][N-2] = 2; ans++; }
		else mine[1][N-2] = 1;
		
		if(map[N-1][N-1] == '1') { mine[N-2][N-2] = 2; ans++; }
		else mine[N-2][N-2] = 1;
		
		for(int r = 1; r < N-1; r++) {
			check(r, 0);
			check(r, N-1);
		}
		
		for(int c = 1; c < N-1; c++) {
			check(0, c);
			check(N-1, c);
		}
		
		System.out.println(ans);
	}
	
	static void check(int r, int c) {
		int n = map[r][c]-'0';
		int m = 0;
		
		for(int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isIn(nr, nc) && map[nr][nc] == '#') {
				if(mine[nr][nc] == 2) m++;
				else if(mine[nr][nc] == 0) temp.add(nr*N+nc);
			}
		}
		
		if(n == m) {
			while(!temp.isEmpty()) {
				int p = temp.poll();
				mine[p/N][p%N] = 1;
			}
		}
		else if(n-m == temp.size()) {
			while(!temp.isEmpty()) {
				int p = temp.poll();
				mine[p/N][p%N] = 2;
				ans++;
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
