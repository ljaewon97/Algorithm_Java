package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_D2_1954_달팽이_숫자 {
	static int N;
	static int[][] arr;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
	
			fillNumber(0, 0, 1, 0);
			
			sb.append(String.format("#%d\n", t));
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					sb.append(arr[i][j] + " ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void fillNumber(int r, int c, int n, int dir) {
		if(n == N*N+1) {
			return;
		}
		
		arr[r][c] = n;
		
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		if(nr < 0 || nr >= N || nc < 0 || nc >= N || arr[nr][nc] != 0) {
			int ndir = (dir + 1) % 4;
			fillNumber(r + dr[ndir], c + dc[ndir], n+1, ndir);
		}
		else {
			fillNumber(nr, nc, n+1, dir);
		}
	}
}
