package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G1_13460_구슬_탈출_2 {
	static char[][] map;
	static boolean[][][][] visited;
	static int N, M, ans = -1;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		int redr = 0, redc = 0, bluer = 0, bluec = 0;
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'R') {
					map[i][j] = '.';
					redr = i; redc = j;
				}
				else if(map[i][j] == 'B') {
					map[i][j] = '.';
					bluer = i; bluec = j;
				}
			}
		}
		
		bfs(redr, redc, bluer, bluec);
		
		System.out.println(ans);
	}
	
	static void bfs(int redr, int redc, int bluer, int bluec) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {redr, redc, bluer, bluec, 0});
		visited[redr][redc][bluer][bluec] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int rr = cur[0];
			int rc = cur[1];
			int br = cur[2];
			int bc = cur[3];
			int dist = cur[4];
			
			if(dist == 10) {
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				boolean stopr = false, stopb = false, finr = false, finb = false;
				int nrr = rr, nrc = rc, nbr = br, nbc = bc;
				
				while(true) {
					if(!stopr) {
						nrr += dr[i];
						nrc += dc[i];
					}
					
					if(!stopb) {
						nbr += dr[i];
						nbc += dc[i];
					}
					
					if(!finr && map[nrr][nrc] == 'O') {
						nrr = N; nrc = M;
						finr = true;
						stopr = true;
					}
					
					if(!finb && map[nbr][nbc] == 'O') {
						nbr = N+1; nbc = M+1;
						finb = true;
						stopb = true;
					}
					
					if(!stopr && map[nrr][nrc] == '#') {
						nrr -= dr[i];
						nrc -= dc[i];
						stopr = true;
					}
					
					if(!stopb && map[nbr][nbc] == '#') {
						nbr -= dr[i];
						nbc -= dc[i];
						stopb = true;
					}
					
					if(stopr && nrr == nbr && nrc == nbc) {
						nbr -= dr[i];
						nbc -= dc[i];
						stopb = true;
					}
					
					if(stopb && nrr == nbr && nrc == nbc) {
						nrr -= dr[i];
						nrc -= dc[i];
						stopr = true;
					}
					
					if(stopr && stopb) {
						if(finb) break;
						
						if(finr) {
							ans = dist + 1;
							return;
						}
						
						if(!visited[nrr][nrc][nbr][nbc]) {
							visited[nrr][nrc][nbr][nbc] = true;
							deque.add(new int[] {nrr, nrc, nbr, nbc, dist+1});
						}
						
						break;
					}
				}
			}
		}
	}
}
