package boj.g4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class G4_02573_빙산 {
	static Reader in = new Reader();
	static List<int[]> pend;
	static int[][] map;
	static boolean[][] visited;
	static int N, M;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = in.nextInt();
			}
		}
		
		int year = 1;
		
		while(true) {
			melt();
			int cnt = countIceberg();
			
			if(cnt > 1) {
				System.out.println(year);
				return;
			}
			else if(cnt == 0) {
				System.out.println(0);
				return;
			}
			
			year++;
		}
	}
	
	static void melt() {
		pend = new ArrayList<>();
		
		for(int i = 1; i < N-1; i++) {
			for(int j = 1; j < M-1; j++) {
				if(map[i][j] != 0) {
					pend.add(new int[] {i, j, countAround(i, j)});
				}
			}
		}
		
		for(int[] cur: pend) {
			int r = cur[0];
			int c = cur[1];
			
			map[r][c] = Math.max(map[r][c] - cur[2], 0);
		}
	}
	
	static int countIceberg() {
		visited = new boolean[N][M];
		int cnt = 0;
		
		for(int[] cur: pend) {
			int r = cur[0];
			int c = cur[1];
			
			if(map[r][c] != 0 && !visited[r][c]) {
				bfs(r, c);
				cnt++;
			}
		}
		
		pend.clear();
		
		return cnt;
	}
	
	static void bfs(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!visited[nr][nc] && map[nr][nc] != 0) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	static int countAround(int r, int c) {
		int cnt = 0;
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(map[nr][nc] == 0) cnt++;
		}
		
		return cnt;
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
