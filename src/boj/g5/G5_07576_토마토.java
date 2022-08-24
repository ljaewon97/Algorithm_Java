package boj.g5;

import java.util.LinkedList;
import java.util.Queue;

public class G5_07576_토마토 {
	static Reader in = new Reader();
	static Queue<int[]> queue = new LinkedList<>();
	static int[][] map;
	static int N, M, cnt, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		map = new int[M][N];
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = in.nextInt();
				
				if(map[i][j] == 0) cnt++;
				else if(map[i][j] == 1) {
					queue.add(new int[] {i, j, 1});
				}
			}
		}
		
		bfs();
		
		if(cnt == 0) System.out.println(ans-1);
		else System.out.println(-1);
	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int d = cur[2];
			
			ans = Math.max(ans, d);
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && map[nr][nc] == 0) {
					map[nr][nc] = d+1;
					queue.add(new int[] {nr, nc, d+1});
					cnt--;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < M && 0 <= c && c < N;
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
