package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_1873_상호의_배틀필드 {
	static char[][] map;
	static char[] comms;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int H, W, r, c, dir;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			
			for(int i = 0; i < H; i++) {
				String line = br.readLine();
				for(int j = 0; j < W; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						r = i;
						c = j;
						switch(map[i][j]) {
						case '^':
							dir = 0; break;
						case 'v':
							dir = 1; break;
						case '<':
							dir = 2; break;
						case '>':
							dir = 3; break;
						}
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			comms = br.readLine().toCharArray();
			
			for(int i = 0; i < N; i++) {
				if(comms[i] == 'S') {
					shoot();
				}
				else {
					changeDir(comms[i]);
				}
			}
			
			sb.append(String.format("#%d ", t));
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static void shoot() {
		int range = 1;
		int nr = 0, nc = 0;
		while(true) {
			nr = r + dr[dir] * range;
			nc = c + dc[dir] * range;
			
			if(!isIn(nr, nc) || map[nr][nc] == '#') {
				break;
			}
			else if(map[nr][nc] == '*') {
				map[nr][nc] = '.';
				break;
			}
			
			range++;
		}
	}
	
	static void changeDir(char d) {
		int ndir = 0;
		char nchar = ' ';
		switch(d) {
		case 'U':
			ndir = 0; nchar = '^'; break;
		case 'D':
			ndir = 1; nchar = 'v'; break;
		case 'L':
			ndir = 2; nchar = '<'; break;
		case 'R':
			ndir = 3; nchar = '>'; break;
		}
		
		int nr = r + dr[ndir];
		int nc = c + dc[ndir];
		
		if(isIn(nr, nc) && map[nr][nc] == '.') {
			map[r][c] = '.';
			r = nr;
			c = nc;
			dir = ndir;
			map[r][c] = nchar;
		}
		else {
			dir = ndir;
			map[r][c] = nchar;
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < H && 0 <= c && c < W;
	}
}
