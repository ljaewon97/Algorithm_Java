package boj.s2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_10997_별_찍기_22 {
	static char[][] star;
	static int N;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		star = new char[4*N-1][4*N-3];
		
		if(N == 1) {
			System.out.println('*');
		}
		else {
			printStar(2*N, 2*(N-1), 0, 3, 0);
			
			for(int i = 0; i < 4*N-1; i++) {
				if(i == 1) {
					sb.append("*");
				}
				else {
					for(int j = 0; j < 4*N-3; j++) {
						sb.append(star[i][j] == '*' ? "*" : " ");
					}
				}
				sb.append("\n");
			}
			System.out.println(sb);
		}
	}
	
	static void printStar(int r, int c, int dir, int dist, int n) {
		int nr = r;
		int nc = c;
		star[nr][nc] = '*';
		boolean isEnd = false;
		
		for(int i = 0; i < dist-1; i++) {
			nr += dr[dir];
			nc += dc[dir];
			if(0 <= nr && nr < 4*N-1 && 0 <= nc && nc < 4*N-3) {
				star[nr][nc] = '*';
			}
			else {
				isEnd = true;
				break;
			}
		}
		
		if(!isEnd) {
			if(n == 0) {
				printStar(nr, nc, (dir+1)%4, dist, 1);
			}
			else {
				printStar(nr, nc, (dir+1)%4, dist+2, 0);
			}
		}
	}
}