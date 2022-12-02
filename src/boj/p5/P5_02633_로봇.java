package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5_02633_로봇 {
	static int[][][] visited;
	static boolean[][][] move;
	static final int INF = 1000000;
	static int sr, sc, er, ec, N, M, K;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken());
		N = Math.max(N, sr);
		sc = Integer.parseInt(st.nextToken());
		M = Math.max(M, sc);
		
		st = new StringTokenizer(br.readLine());
		er = Integer.parseInt(st.nextToken());
		N = Math.max(N, er);
		ec = Integer.parseInt(st.nextToken());
		M = Math.max(M, ec);
		
		K = Integer.parseInt(br.readLine());
		
		int[][][] obs = new int[K][6][2];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				if(j == 3) j++;
				for(int k = 0; k < 2; k++) {
					obs[i][j][k] = Integer.parseInt(st.nextToken());
					if(k == 0) N = Math.max(N, obs[i][j][k]);
					else M = Math.max(M, obs[i][j][k]);
				}
			}
		}
		
		N++; M++;
		
		visited = new int[N+1][M+1][4];
		move = new boolean[N+1][M+1][4];
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= M; c++) {
				for(int i = 0; i < 4; i++) {
					visited[r][c][i] = INF;
					move[r][c][i] = true;
				}
			}
		}
		
		for(int i = 0; i < K; i++) {
			boundary(obs[i]);
		}
		
		System.out.println(bfs());
	}
	
	static void boundary(int[][] ob) {
		if(ob[1][0] == ob[2][0]) {
			ob[3][0] = ob[4][0];
			ob[3][1] = ob[2][1];
		}
		else {
			ob[3][0] = ob[2][0];
			ob[3][1] = ob[4][1];
		}
		
		if(ob[0][0] == ob[1][0]) {
			ob[5][0] = ob[4][0];
			ob[5][1] = ob[0][1];
		}
		else {
			ob[5][0] = ob[0][0];
			ob[5][1] = ob[4][1];
		}
		
		for(int i = 0; i < 6; i++) {
			int a = i;
			int b = i == 5 ? 0 : i+1;
			
			check(ob, a, b);
		}
	}
	
	static void check(int[][] ob, int a, int b) {
		int rgap = ob[b][0] - ob[a][0];
		int cgap = ob[b][1] - ob[a][1];
		int dir = -1;
		
		if(rgap == 0) {
			if(cgap > 0) dir = 1;
			else dir = 3;
		}
		else {
			if(rgap > 0) dir = 2;
			else dir = 0;
		}
		
		if(b == 4) {
			move[ob[b][0]][ob[b][1]][(dir+3)%4] = false;
			move[ob[b][0]][ob[b][1]][dir] = false;
		}
		
		int r = ob[a][0];
		int c = ob[a][1];
		
		while(true) {
			r += dr[dir];
			c += dc[dir];
			
			if(r == ob[b][0] && c == ob[b][1]) break;
			
			move[r][c][(dir+3)%4] = false;
		}
	}
	
	static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		
		for(int i = 0; i < 4; i++) {
			visited[sr][sc][i] = 0;
			
			int nr = sr + dr[i];
			int nc = sc + dc[i];
			
			if(isIn(nr, nc)) {
				visited[nr][nc][i] = 0;
				queue.add(new Point(nr, nc, 0, i));
			}
		}
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				if(!move[p.r][p.c][i]) continue;
				
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				int ncnt = p.cnt + (p.pd != i ? 1 : 0);
				
				if(isIn(nr, nc) && ncnt < visited[nr][nc][i]) {
					visited[nr][nc][i] = ncnt;
					queue.add(new Point(nr, nc, ncnt, i));
				}
			}
		}
		
		int ret = INF;
		
		for(int i = 0; i < 4; i++) {
			ret = Math.min(ret, visited[er][ec][i]);
		}
		
		return ret;
	}
	
	static boolean isIn(int r, int c) {
		return 1 <= r && r <= N && 1 <= c && c <= M;
	}
	
	static class Point {
		int r, c, cnt, pd;
		
		public Point(int r, int c, int cnt, int pd) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.pd = pd;
		}
	}
}
