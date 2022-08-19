package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_07682_틱택토 {
	static char[][] map = new char[3][3];
	static boolean[] finished;
	static boolean flag;
	static int finisho, finishx;
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			finished = new boolean[8];
			flag = false;
			
			if(line.equals("end")) break;
			
			int cnto = 0, cntx = 0;
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					map[i][j] = line.charAt(3*i + j);
					if(map[i][j] == 'O') cnto++;
					if(map[i][j] == 'X') cntx++;
				}
			}
			
			if(cnto > cntx || cntx > cnto + 1) {
				sb.append("invalid\n");
				continue;
			}
			
			finisho = 0; finishx = 0;
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(map[i][j] != '.') check(i, j);
				}
			}
			
			if(finisho == 1 && finishx == 0 && cnto == cntx) {
				flag = true;
			}
			else if(finisho == 0 && finishx == 1 && cnto == cntx - 1) {
				flag = true;
			}
			else if(cnto + cntx == 9 && finisho + finishx == 0) {
				flag = true;
			}
			else if(cnto + cntx == 9 && finishx == 2) {
				flag = true;
			}
			
			if(flag) sb.append("valid\n");
			else sb.append("invalid\n");
		}
		
		System.out.println(sb);
	}
	
	static void check(int r, int c) {
		char ch = map[r][c];
		
		for(int i = 0; i < 8; i++) {
			int step = 1;
			int cnt = 1;
			
			while(true) {
				int nr = r + dr[i] * step;
				int nc = c + dc[i] * step;
				
				if(!isIn(nr, nc)) break;
				
				if(map[nr][nc] == ch) {
					cnt++;
				}
				
				step++;
			}
			
			if(cnt == 3) {
				if((i == 2 || i == 6) && !finished[r]) {
					finished[r] = true;
					addFinish(ch);
				}
				else if((i == 0 || i == 4) && !finished[3+c]) {
					finished[3+c] = true;
					addFinish(ch);
				}
				else if((i == 3 || i == 7) && !finished[6]) {
					finished[6] = true;
					addFinish(ch);
				}
				else if((i == 1 || i == 5) && !finished[7]) {
					finished[7] = true;
					addFinish(ch);
				}
			}
		}
	}
	
	static void addFinish(char c) {
		if(c == 'O') finisho++;
		if(c == 'X') finishx++;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < 3 && 0 <= c && c < 3;
	}
}
