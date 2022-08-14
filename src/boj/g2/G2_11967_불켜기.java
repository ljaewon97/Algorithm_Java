package boj.g2;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class G2_11967_불켜기 {
	static Reader in = new Reader();
	static int[][] map;
	static boolean[][] on, visited, boundary;
	static List<int[]>[][] switches;
	static int N, M, ans = 1;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		map = new int[N+1][N+1];
		on = new boolean[N+1][N+1];
		boundary = new boolean[N+1][N+1];
		switches = new ArrayList[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				switches[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < M; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int a = in.nextInt();
			int b = in.nextInt();
			
			switches[x][y].add(new int[] {a, b});
		}
		
		bfs();
		System.out.println(ans);
	}
	
	static void bfs() {
		Deque<int[]> deque = new LinkedList<>();
		visited = new boolean[N+1][N+1];
		deque.add(new int[] {1, 1, 0});
		map[1][1] = 1;
		visited[1][1] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(!on[r][c] && !switches[r][c].isEmpty()) {
				for(int[] s: switches[r][c]) {
					if(map[s[0]][s[1]] != 1) {
						map[s[0]][s[1]] = 1;
						ans++;
						if(boundary[s[0]][s[1]]) {
							deque.add(new int[] {s[0], s[1]});
						}
					}
				}
				on[r][c] = true;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc]) {
					if(map[nr][nc] == 1) {
						visited[nr][nc] = true;
						deque.add(new int[] {nr, nc});
					}
					else {
						boundary[nr][nc] = true;
					}
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 1 <= r && r <= N && 1 <= c && c <= N;
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
