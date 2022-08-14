package boj.g4;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class G4_17142_연구소_3 {
	static Reader in = new Reader();
	static List<int[]> list = new ArrayList<>();
	static int[][] map, virus;
	static boolean[][] visited;
	static int N, M, virusCnt = 0, ans = Integer.MAX_VALUE, spaceCnt;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		map = new int[N][N];
		virus = new int[10][2];
		spaceCnt = N * N;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = in.nextInt();
				if(map[i][j] == 2) {
					virus[virusCnt][0] = i;
					virus[virusCnt][1] = j;
					virusCnt++;
					spaceCnt--;
				}
				else if(map[i][j] == 1) {
					spaceCnt--;
				}
			}
		}
		
		comb(0, 0, new int[M]);
		
		for(int[] c: list) {
			spread(c);
		}
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static void spread(int[] arr) {
		int space = spaceCnt;
		int maxTime = 0;
		Deque<int[]> deque = new LinkedList<>();
		visited = new boolean[N][N];
		
		for(int idx: arr) {
			int r = virus[idx][0];
			int c = virus[idx][1];
			
			deque.add(new int[] {r, c, 0});
			visited[r][c] = true;
		}
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			int time = cur[2];
			
			if(map[r][c] == 0) {
				space--;
				if(time > maxTime) {
					maxTime = time;
				}
			}
			
			if(space == 0) {
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != 1) {
					deque.add(new int[] {nr, nc, time+1});
					visited[nr][nc] = true;
				}
			}
		}
		
		if(space == 0) {
			if(maxTime < ans) {
				ans = maxTime;
			}
		}
	}
	
	static void comb(int nth, int start, int[] result) {
		if(nth == M) {
			list.add(result.clone());
			return;
		}
		
		for(int i = start; i < virusCnt; i++) {
			result[nth] = i;
			comb(nth+1, i+1, result);
		}
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
