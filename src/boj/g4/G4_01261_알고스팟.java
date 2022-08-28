package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G4_01261_알고스팟 {
	static char[][] map;
	static int N, M, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		dijkstra();
		
		System.out.println(ans);
	}
	
	static void dijkstra() {
		int[][] dist = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[0][0] = 0;
		pq.add(new Node(0, 0, dist[0][0]));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(dist[cur.r][cur.c] < cur.w) continue;
			
			for(int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(!isIn(nr, nc)) continue;
				
				int temp = cur.w + map[nr][nc] - '0';
				if(temp < dist[nr][nc]) {
					dist[nr][nc] = temp;
					pq.add(new Node(nr, nc, dist[nr][nc]));
				}
			}
		}

		ans = dist[N-1][M-1];
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static class Node implements Comparable<Node> {
		int r;
		int c;
		int w;
		
		public Node(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}		

		public int compareTo(Node o) {
			return this.w - o.w;
		}	
	}
}
