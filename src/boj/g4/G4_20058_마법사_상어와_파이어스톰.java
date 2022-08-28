package boj.g4;

import java.util.LinkedList;
import java.util.Queue;

public class G4_20058_마법사_상어와_파이어스톰 {
	static Reader in = new Reader();
	static int[][] map;
	static boolean[][] visited;
	static int N, Q;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = (int) Math.pow(2, in.nextInt());
		Q = in.nextInt();
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = in.nextInt();
			}
		}
		
		for(int i = 0; i < Q; i++) {
			fireStorm(in.nextInt());
			melt();
		}
		
		printResult();
	}
	
	static void fireStorm(int L) {
		if(L == 0) return;
		
		L = (int) Math.pow(2, L);
		
		for(int r = 0; r < N; r += L) {
			for(int c = 0; c < N; c += L) {
				rotate(r, c, L);
			}
		}
	}
	
	static void rotate(int sr, int sc, int L) {
		int[][] res = new int[L][L];
		
		for(int r = 0; r < L; r++) {
			for(int c = 0; c < L; c++) {
				res[r][c] = map[sr+L-1-c][sc+r];
			}
		}
		
		for(int r = 0; r < L; r++) {
			for(int c = 0; c < L; c++) {
				map[sr+r][sc+c] = res[r][c];
			}
		}
	}
	
	static void melt() {
		Queue<Node> melted = new LinkedList<>();
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] > 0) {
					int cnt = 0;
					
					for(int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if(isIn(nr, nc) && map[nr][nc] > 0) cnt++;
					}
					
					if(cnt < 3) melted.add(new Node(r, c));
				}
			}
		}
		
		while(!melted.isEmpty()) {
			Node cur = melted.poll();
			map[cur.r][cur.c]--;
		}
	}
	
	static void printResult() {
		int sum = 0;
		int max = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				sum += map[r][c];
				if(map[r][c] > 0 && !visited[r][c]) {
					int chunk = bfs(r, c);
					max = Math.max(max, chunk);
				}
			}
		}
		
		System.out.println(sum);
		System.out.println(max);
	}
	
	static int bfs(int sr, int sc) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(sr, sc));
		visited[sr][sc] = true;
		int chunk = 0;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			chunk++;
			
			for(int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] > 0) {
					queue.add(new Node(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
		
		return chunk;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	static class Node {
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
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
