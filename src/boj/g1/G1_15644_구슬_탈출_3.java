package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_15644_구슬_탈출_3 {
	static char[][] map;
	static boolean[][][][] visited;
	static int N, M, ans, move;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {-1,1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		
		int rsr = -1, rsc = -1, bsr = -1, bsc = -1;
		
		for(int r = 0; r < N; r++) {
			String line = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c);
				if(map[r][c] == 'R') {
					map[r][c] = '.';
					rsr = r;
					rsc = c;
				}
				else if(map[r][c] == 'B') {
					map[r][c] = '.';
					bsr = r;
					bsc = c;
				}
			}
		}
		
		bfs(rsr, rsc, bsr, bsc);
		
		if(move == 0 || move == 11) {
			System.out.println(-1);
		}
		else {
			sb.append(move).append("\n");
			sb.append(findRoute());
			System.out.println(sb);
		}
	}
	
	static void bfs(int rsr, int rsc, int bsr, int bsc) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(rsr, rsc, bsr, bsc, 0, 0));
		visited[rsr][rsc][bsr][bsc] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.m == 11) {
				move = 11;
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				boolean rout = false, bout = false;
				
				int nrr = p.rr, nrc = p.rc;
				while(true) {
					if(map[nrr+dr[i]][nrc+dc[i]] == '.') {
						nrr += dr[i];
						nrc += dc[i];
					}
					else if(map[nrr+dr[i]][nrc+dc[i]] == 'O') {
						rout = true;
						break;
					}
					else break;
				}
				
				int nbr = p.br, nbc = p.bc;
				while(true) {
					if(map[nbr+dr[i]][nbc+dc[i]] == '.') {
						nbr += dr[i];
						nbc += dc[i];
					}
					else if(map[nbr+dr[i]][nbc+dc[i]] == 'O') {
						bout = true;
						break;
					}
					else break;
				}
				
				if(bout) continue;
				if(rout) {
					move = p.m + 1;
					ans = 5*p.route + i+1;
					return;
				}
				
				if(nrr == nbr && nrc == nbc) {
					if(i == 0) {
						if(p.rc < p.bc) nbc++;
						else nrc++;
					}
					else if(i == 1) {
						if(p.rc < p.bc) nrc--;
						else nbc--;
					}
					else if(i == 2) {
						if(p.rr < p.br) nbr++;
						else nrr++;
					}
					else {
						if(p.rr < p.br) nrr--;
						else nbr--;
					}
				}
				
				if(!visited[nrr][nrc][nbr][nbc]) {
					visited[nrr][nrc][nbr][nbc] = true;
					queue.add(new Point(nrr, nrc, nbr, nbc, p.m+1, 5*p.route + i+1));
				}
			}
		}
	}
	
	static String findRoute() {
		StringBuilder temp = new StringBuilder();
		
		while(ans > 0) {
			int mod = ans % 5;
			temp.append(mod == 1 ? 'L' : (mod == 2 ? 'R' : (mod == 3 ? 'U' : 'D')));
			ans /= 5;
		}
		
		return temp.reverse().toString();
	}
	
	static class Point {
		int rr, rc, br, bc, m, route;
		
		public Point(int rr, int rc, int br, int bc, int m, int route) {
			this.rr = rr;
			this.rc = rc;
			this.br = br;
			this.bc = bc;
			this.m = m;
			this.route = route;
		}
	}
}
