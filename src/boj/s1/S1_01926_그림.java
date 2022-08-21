package boj.s1;

import java.util.LinkedList;
import java.util.Queue;

public class S1_01926_그림 {
	static Reader in = new Reader();
	static int[][] map;
	static boolean[][] visited;
	static int N, M, ans, cnt;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = in.nextInt();
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					ans = Math.max(ans, bfs(i, j));
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(ans);
	}
	
	static int bfs(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			cnt++;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					queue.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
		
		return cnt;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}
