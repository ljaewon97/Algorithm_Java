package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class G3_08972_미친_아두이노 {
	static Queue<Integer> queue = new LinkedList<>();
	static char[][] board;
	static int R, C, ir, ic;
	static int[] dr = {0,1,1,1,0,0,0,-1,-1,-1};
	static int[] dc = {0,-1,0,1,-1,0,1,-1,0,1};
	static boolean end;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		
		for(int r = 0; r < R; r++) {
			String line = br.readLine();
			for(int c = 0; c < C; c++) {
				board[r][c] = line.charAt(c);
				if(board[r][c] == 'I') {
					ir = r;
					ic = c;
				}
				else if(board[r][c] == 'R') {
					queue.add(101*r+c);
				}
			}
		}
		
		String dirs = br.readLine();
		int ans = -1;
		
		for(int i = 0; i < dirs.length(); i++) {
			int dir = dirs.charAt(i) - '0';
			
			moveI(dir);
			
			if(end) {
				ans = i+1;
				break;
			}
			
			moveR();
			
			if(end) {
				ans = i+1;
				break;
			}
		}
		
		if(ans != -1) {
			sb.append("kraj ").append(ans);
		}
		else {
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					sb.append(board[r][c]);
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void moveI(int dir) {
		board[ir][ic] = '.';
		ir += dr[dir];
		ic += dc[dir];
		
		if(board[ir][ic] == 'R') {
			end = true;
			return;
		}
		
		board[ir][ic] = 'I';
	}
	
	static void moveR() {
		Set<Integer> temp = new HashSet<>();
		Set<Integer> exploded = new HashSet<>();
		
		while(!queue.isEmpty()) {
			int p = queue.poll();
			int r = p/101;
			int c = p%101;
			
			board[r][c] = '.';
			int min = 100000, d = -1;
			
			for(int i = 1; i <= 9; i++) {
				if(i == 5) continue;
				
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!isIn(nr, nc)) continue;
				
				int td = dist(nr, nc);
				if(td < min) {
					min = td;
					d = i;
				}
			}
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr == ir && nc == ic) {
				end = true;
				return;
			}
			
			int np = 101*nr+nc;
			
			if(exploded.contains(np)) continue;
			else if(temp.contains(np)) {
				temp.remove(np);
				exploded.add(np);
			}
			else temp.add(np);
		}
		
		for(int p: temp) {
			board[p/101][p%101] = 'R';
			queue.add(p);
		}
	}
	
	static int dist(int r, int c) {
		return Math.abs(ir-r) + Math.abs(ic-c);
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
