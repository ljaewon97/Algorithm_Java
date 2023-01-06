package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_15653_구슬_탈출_4 {
	static char[][] map;
	static boolean[][][][] visited;
	static int N, M, ans = Integer.MAX_VALUE;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {-1,1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		int srr = -1, src = -1, sbr = -1, sbc = -1;
		
		for(int r = 0; r < N; r++) {
			String line = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c);
				if(map[r][c] == 'R') {
					srr = r;
					src = c;
					map[r][c] = '.';
				}
				else if(map[r][c] == 'B') {
					sbr = r;
					sbc = c;
					map[r][c] = '.';
				}
			}
		}
		
		bfs(srr, src, sbr, sbc);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static void bfs(int srr, int src, int sbr, int sbc) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(srr, src, sbr, sbc, 0));
		visited[srr][src][sbr][sbc] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				boolean bout = false;
				int nbr = p.br, nbc = p.bc;
				
				while(true) {
					int tbr = nbr + dr[i];
					int tbc = nbc + dc[i];
					
					if(map[tbr][tbc] == '#') break;
					else if(map[tbr][tbc] == 'O') {
						bout = true;
						break;
					}
					
					nbr += dr[i];
					nbc += dc[i];
				}
				
				if(bout) continue;
				
				boolean rout = false;
				int nrr = p.rr, nrc = p.rc;
				
				while(true) {
					int trr = nrr + dr[i];
					int trc = nrc + dc[i];
					
					if(map[trr][trc] == '#') break;
					else if(map[trr][trc] == 'O') {
						rout = true;
						break;
					}
					
					nrr += dr[i];
					nrc += dc[i];
				}
				
				if(rout) {
					ans = p.d+1;
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
					queue.add(new Point(nrr, nrc, nbr, nbc, p.d+1));
					visited[nrr][nrc][nbr][nbc] = true;
				}
			}
		}
	}
	
	static class Point {
		int rr, rc, br, bc, d;
		
		public Point(int rr, int rc, int br, int bc, int d) {
			this.rr = rr;
			this.rc = rc;
			this.br = br;
			this.bc = bc;
			this.d = d;
		}
	}
}
