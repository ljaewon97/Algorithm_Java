package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5_10218_Maze {
	static int[][][][] dest;
	static char[][] map;
	static int N, M, er, ec, spaceCnt;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {-1,1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			spaceCnt = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			dest = new int[N][M][4][2];
			map = new char[N][];
			int[][] arr = new int[N][M];
			
			for(int r = 0; r < N; r++) {
				map[r] = br.readLine().toCharArray();
			}
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(map[r][c] == 'O') {
						er = r;
						ec = c;
					}
					else if(map[r][c] == '.') {
						spaceCnt++;
						arr[r][c] = 1;
						
						for(int i = 0; i < 4; i++) {
							int nr = r, nc = c;
							
							while(true) {
								int tr = nr + dr[i];
								int tc = nc + dc[i];
								
								if(map[tr][tc] == '#') {
									break;
								}
								else if(map[tr][tc] == 'O') {
									nr += dr[i];
									nc += dc[i];
									break;
								}
								
								nr += dr[i];
								nc += dc[i];
							}
							
							dest[r][c][i][0] = nr;
							dest[r][c][i][1] = nc;
						}
					}
				}
			}
			
			for(int i = 0; i < 4; i++) {
				dest[er][ec][i][0] = er;
				dest[er][ec][i][1] = ec;
			}
			
			int ans = bfs(arr);
			
			if(ans == -1) sb.append("XHAE\n");
			else {
				StringBuilder route = new StringBuilder();
				
				while(ans > 0) {
					int mod = ans % 5;
					if(mod == 1) route.append("L");
					else if(mod == 2) route.append("R");
					else if(mod == 3) route.append("U");
					else if(mod == 4) route.append("D");
					
					ans /= 5;
				}
				
				sb.append(route.reverse()).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static int bfs(int[][] arr) {
		Queue<Case> queue = new LinkedList<>();
		queue.add(new Case(arr, -1, 0, 0));
		
		while(!queue.isEmpty()) {
			Case cur = queue.poll();
			
			if(cur.m == 10) continue;
			
			for(int i = 0; i < 4; i++) {
				if(i == cur.p) continue;
				
				int[][] cpd = new int[N][M];
				
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < M; c++) {
						cpd[dest[r][c][i][0]][dest[r][c][i][1]] += cur.arr[r][c];
					}
				}
				
				if(cpd[er][ec] == spaceCnt) return 5*cur.route+i+1;
				
				queue.add(new Case(cpd, i, cur.m+1, 5*cur.route+i+1));
			}
		}
		
		return -1;
	}
	
	static class Case {
		int[][] arr;
		int p, m, route;
		
		public Case(int[][] arr, int p, int m, int route) {
			this.arr = arr;
			this.p = p;
			this.m = m;
			this.route = route;
		}
	}
}
