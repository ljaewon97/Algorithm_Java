package boj.g3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class G3_16236_아기_상어 {
	static Reader in = new Reader();
	static int[][] map;
	static boolean[][] visited;
	static int N, sr, sc, size = 2, eaten, time;
	static int[] dr = {-1,0,0,1};
	static int[] dc = {0,-1,1,0};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = in.nextInt();
				
				if(map[i][j] == 9) {
					sr = i; sc = j;
					map[i][j] = 0;
				}
			}
		}
		
		while(true) {
			List<int[]> fishes = search();
			
			if(fishes.isEmpty()) {
				System.out.println(time);
				break;
			}
			
			Collections.sort(fishes, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
			
			int[] fish = fishes.get(0);
			int fr = fish[0];
			int fc = fish[1];
			int fd = fish[2];
			
			eaten++;
			map[fr][fc] = 0;
			if(eaten == size) {
				eaten = 0;
				size++;
			}
			time += fd;
			sr = fr; sc = fc;
		}
	}
	
	static List<int[]> search() {
		List<int[]> fishes = new ArrayList<>();
		visited = new boolean[N][N];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr, sc, 0});
		visited[sr][sc] = true;
		boolean found = false;
		int dist = -1;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int d = cur[2];
			
			if(d == dist) continue;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] > 0 && map[nr][nc] < size) {
					visited[nr][nc] = true;
					fishes.add(new int[] {nr, nc, d+1});
					if(!found) {
						found = true;
						dist = d+1;
					}
				}
				else if(isIn(nr, nc) && !visited[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] == size)) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc, d+1});
				}
			}
		}

		return fishes;
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
