package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_01194_달이_차오른다_가자_ver2 {
	static char[][] map;
	static boolean[][][] visited;
	static int N, M, ans = -1;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int sr = -1, sc = -1;
		
		map = new char[N][M];
		visited = new boolean[64][N][M];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == '0') {
					sr = i;
					sc = j;
				}
			}
		}
		
		bfs(sr, sc);
		
		System.out.println(ans);
	}
	
	static void bfs(int sr, int sc) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(sr, sc, 0, 0));
		visited[0][sr][sc] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr, nc) || visited[p.key][nr][nc]) continue;
				
				if(map[nr][nc] == '.' || map[nr][nc] == '0') {
					visited[p.key][nr][nc] = true;
					queue.add(new Point(nr, nc, p.d+1, p.key));
				}
				else if('a' <= map[nr][nc] && map[nr][nc] <= 'f') {
					visited[p.key][nr][nc] = true;
					int nkey = p.key | (1 << (map[nr][nc] - 'a'));
					visited[nkey][nr][nc] = true;
					queue.add(new Point(nr, nc, p.d+1, nkey));
				}
				else if('A' <= map[nr][nc] && map[nr][nc] <= 'F') {
					if((p.key & 1 << (map[nr][nc] - 'A')) != 0) {
						visited[p.key][nr][nc] = true;
						queue.add(new Point(nr, nc, p.d+1, p.key));
					}
				}
				else if(map[nr][nc] == '1') {
					ans = p.d + 1;
					return;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static class Point {
		int r, c, d, key;
		
		public Point(int r, int c, int d, int key) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.key = key;
		}
	}
}
