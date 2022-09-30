package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SWEA_D4_1249_보급로 {
	static int[][] map, dist;
	static final int INF = Integer.MAX_VALUE;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				for(int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}

			sb.append(String.format("#%d %d\n", t, dijkstra()));
		}
		
		System.out.println(sb);
	}
	
	static int dijkstra() {
		dist = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INF);
		}
		
		dist[0][0] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		pq.add(new int[] {0, 0, 0});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int r = cur[0];
			int c = cur[1];
			int cur_dist = cur[2];
			
			if(dist[r][c] < cur_dist) continue;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc)) {
					int temp = cur_dist + map[nr][nc];
					if(temp < dist[nr][nc]) {
						dist[nr][nc] = temp;
						pq.add(new int[] {nr, nc, temp});
					}
				}
			}
		}

		return dist[N-1][N-1];
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
