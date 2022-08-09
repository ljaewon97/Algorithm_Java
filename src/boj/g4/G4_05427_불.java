package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G4_05427_불 {
	static char[][] map;
	static boolean[][] visited, visitedF;
	static int W, H, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new char[H][];
			visited = new boolean[H][W];
			visitedF = new boolean[H][W];
			ans = -1;
			
			int r = 0, c = 0;
			for(int i = 0; i < H; i++) {
				String line = br.readLine();
				if(line.indexOf('@') != -1) {
					r = i;
					c = line.indexOf('@');
				}
				map[i] = line.toCharArray();
			}
			
			escape(r, c);
			
			if(ans == -1) {
				sb.append("IMPOSSIBLE\n");
			}
			else {
				sb.append(ans).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void escape(int sr, int sc) {
		Deque<int[]> deque = new LinkedList<>();
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(map[i][j] == '*') {
					deque.add(new int[] {1, i, j, 0});
					visitedF[i][j] = true;
				}
			}
		}
		
		deque.add(new int[] {0, sr, sc, 0});
		visited[sr][sc] = true;
		
		outer: while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int x = cur[0];
			int r = cur[1];
			int c = cur[2];
			int dist = cur[3];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(x == 0) {
					if(!isIn(nr, nc)) {
						ans = dist + 1;
						break outer;
					}
					if(isIn(nr, nc) && !visited[nr][nc] && !visitedF[nr][nc] && map[nr][nc] == '.') {
						visited[nr][nc] = true;
						deque.add(new int[] {0, nr, nc, dist+1});
					}
				}
				else {
					if(isIn(nr, nc) && !visitedF[nr][nc] && map[nr][nc] != '#') {
						visitedF[nr][nc] = true;
						deque.add(new int[] {1, nr, nc, dist+1});
					}
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < H && 0 <= c && c < W;
	}
}
