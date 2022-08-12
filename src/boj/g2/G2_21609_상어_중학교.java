package boj.g2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class G2_21609_상어_중학교 {
	static Reader in = new Reader();
	static int[][] map;
	static boolean[][] visited;
	static int N, M, score = 0;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = in.nextInt();
			}
		}
		
		while(true) {
			int[] blockgroup = findBlockGroup();
			
			if(blockgroup == null) break;
			
			removeBlockGroup(blockgroup);
			
			gravity();
			rotate();
			gravity();
		}
		
		System.out.println(score);
	}
	
	static int[] findBlockGroup() {
		List<int[]> blockgroup = new ArrayList<>();
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(0 < map[i][j] && map[i][j] <= M && !visited[i][j]) {
					int[] temp = bfs(i, j);
					if(temp != null) {
						blockgroup.add(temp);
					}
				}
			}
		}
		
		Collections.sort(blockgroup, (o1, o2) -> o1[2] != o2[2] ? o2[2] - o1[2] : (o1[3] != o2[3] ? o2[3] - o1[3] : (o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1])));
		
		if(!blockgroup.isEmpty()) {
			return blockgroup.get(0);
		}
		else {
			return null;
		}
	}
	
	static int[] bfs(int sr, int sc) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		int cnt = 0, rainbow = 0;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			cnt++;
			if(map[r][c] == 0) rainbow++;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && (map[nr][nc] == map[sr][sc] || map[nr][nc] == 0)) {
					visited[nr][nc] = true;
					deque.add(new int[] {nr, nc});
				}
			}
		}
		
		unvisitRainbow();
		
		if(cnt >= 2) {
			return new int[] {sr, sc, cnt, rainbow};
		}
		else {
			return null;
		}
	}
	
	static void unvisitRainbow() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] == 0 && visited[r][c]) {
					visited[r][c] = false;
				}
			}
		}
	}
	
	static void removeBlockGroup(int[] blockgroup) {
		int sr = blockgroup[0];
		int sc = blockgroup[1];
		int cnt = blockgroup[2];
		int val = map[sr][sc];
		score += cnt * cnt;
		
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {sr, sc});
		visited = new boolean[N][N];
		visited[sr][sc] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			map[r][c] = M+1;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] == val)) {
					visited[nr][nc] = true;
					deque.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	static void gravity() {
		for(int c = 0; c < N; c++) {
			for(int r = 1; r < N; r++) {
				for(int i = N-1; i >= r; i--) {
					if(map[i][c] == M+1) {
						if(map[i-1][c] == -1) continue;
						map[i][c] = map[i-1][c];
						map[i-1][c] = M+1;
					}
				}
			}
		}
	}
	
	static void rotate() {
		int[][] temp = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				temp[r][c] = map[c][N-1-r];
			}
		}
		
		map = temp;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			if (neg)
				return -n;
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
