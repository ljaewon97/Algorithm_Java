package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G2_01445_일요일_아침의_데이트 {
	static char[][] input;
	static int[][] map;
	static int N, M, sr, sc, er, ec;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		input = new char[N][M];
		map = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			String line = br.readLine();
			for(int c = 0; c < M; c++) {
				input[r][c] = line.charAt(c);
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(input[r][c] == 'S') {
					map[r][c] = 0;
					sr = r;
					sc = c;
				}
				else if(input[r][c] == 'F') {
					map[r][c] = 0;
					er = r;
					ec = c;
				}
				else if(input[r][c] == 'g') {
					map[r][c] = 1;
				}
				else {
					int cnt = 0;
					
					for(int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if(isIn(nr, nc) && input[nr][nc] == 'g') cnt++;
					}
					
					if(cnt > 0) map[r][c] = 2;
					else map[r][c] = 0;
				}
			}
		}
		
		int[] res = dijkstra();
		
		System.out.println(res[0] + " " + res[1]);
	}
	
	static int[] dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[][][] dist = new int[N][M][2];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				dist[r][c][0] = dist[r][c][1] = Integer.MAX_VALUE;
			}
		}
		
		pq.add(new Node(sr, sc, 0, 0));
		dist[sr][sc][0] = dist[sr][sc][1] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.t > dist[cur.r][cur.c][0]) continue;
			if(cur.t == dist[cur.r][cur.c][0] && cur.n > dist[cur.r][cur.c][1]) continue;
			
			for(int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(!isIn(nr, nc)) continue;
				
				int nt = cur.t;
				int nn = cur.n;
				
				if(map[nr][nc] == 1) nt++;
				else if(map[nr][nc] == 2) nn++;
				
				if(nt < dist[nr][nc][0]) {
					dist[nr][nc][0] = nt;
					dist[nr][nc][1] = nn;
					pq.add(new Node(nr, nc, nt, nn));
				}
				else if(nt == dist[nr][nc][0] && nn < dist[nr][nc][1]) {
					dist[nr][nc][1] = nn;
					pq.add(new Node(nr, nc, nt, nn));
				}
			}
		}
		
		return new int[] {dist[er][ec][0], dist[er][ec][1]};
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static class Node implements Comparable<Node> {
		int r, c, t, n;
		
		public Node(int r, int c, int t, int n) {
			this.r = r;
			this.c = c;
			this.t = t;
			this.n = n;
		}

		public int compareTo(Node o) {
			if(this.t != o.t) return this.t - o.t;
			return this.n - o.n;
		}
	}
}
