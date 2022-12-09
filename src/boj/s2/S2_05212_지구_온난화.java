package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_05212_지구_온난화 {
	static int R, C;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][];
		boolean[][] sea = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int sr = 10, sc = 10, er = -1, ec = -1;
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == '.') sea[r][c] = true;
				else {
					int cnt = 0;
					
					for(int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if(!isIn(nr, nc) || map[nr][nc] == '.') cnt++;
					}
					
					if(cnt >= 3) sea[r][c] = true;
					else {
						sr = Math.min(sr, r);
						sc = Math.min(sc, c);
						er = Math.max(er, r);
						ec = Math.max(ec, c);
					}
				}
			}
		}
		
		for(int r = sr; r <= er; r++) {
			for(int c = sc; c <= ec; c++) {
				sb.append(sea[r][c] ? '.' : 'X');
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
