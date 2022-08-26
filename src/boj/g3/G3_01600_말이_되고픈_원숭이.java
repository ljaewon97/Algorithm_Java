package boj.g3;

import java.util.LinkedList;
import java.util.Queue;

public class G3_01600_말이_되고픈_원숭이 {
	static Reader in = new Reader();
	static int[][] map;
	static int[][] visited;
	static int K, W, H, ans = -1;
	static int[] mdr = {-1,1,0,0};
	static int[] mdc = {0,0,-1,1};
	static int[] hdr = {-1,-2,-2,-1,1,2,2,1};
	static int[] hdc = {-2,-1,1,2,2,1,-1,-2};
	
	public static void main(String[] args) throws Exception {
		K = in.nextInt();
		W = in.nextInt();
		H = in.nextInt();
		
		map = new int[H][W];
		visited = new int[H][W];
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				map[i][j] = in.nextInt();
			}
		}
		
		bfs(0, 0);
		
		System.out.println(ans);
	}
	
	static void bfs(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr, sc, 0, 0});
		visited[sr][sc] = 1;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int jump = cur[2];
			int d = cur[3];
			int bit = 1 << jump;
			
			if(r == H-1 && c == W-1) {
				ans = d;
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = r + mdr[i];
				int nc = c + mdc[i];
				
				if(isIn(nr, nc) && map[nr][nc] != 1 && (bit & visited[nr][nc]) == 0) {
					visited[nr][nc] |= bit;
					queue.add(new int[] {nr, nc, jump, d+1});
				}
			}
			
			if(jump < K) {
				for(int i = 0; i < 8; i++) {
					int nr = r + hdr[i];
					int nc = c + hdc[i];
					
					if(isIn(nr, nc) && map[nr][nc] != 1 && ((bit << 1) & visited[nr][nc]) == 0) {
						visited[nr][nc] |= bit << 1;
						queue.add(new int[] {nr, nc, jump+1, d+1});
					}
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < H && 0 <= c && c < W;
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int idx, size;

		boolean isNumber(byte b) {
			return 47 < b && b < 58;
		}

		byte read() throws Exception {
			if (idx == size) {
				size = System.in.read(buffer, idx = 0, SIZE);
				if (size < 0) {
					buffer[0] = -1;
				}
			}
			return buffer[idx++];
		}

		int nextInt() throws Exception {
			int n = 0;
			byte b;
			boolean neg = false;
			while ((b = read()) <= 32)
				;
			if (b == '-') {
				neg = true;
				b = read();
			}
			do
				n = (n << 3) + (n << 1) + (b & 15);
			while (isNumber(b = read()));
			if (neg)
				return -n;
			return n;
		}
	}
}
