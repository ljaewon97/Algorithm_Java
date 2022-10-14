package boj.g3;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class G3_16235_나무_재테크 {
	static Reader in = new Reader();
	static PriorityQueue<Integer>[][] map;
	static int[][] nut, A, dead;
	static int N, M, K;
	static int[] dr = {-1,1,0,0,-1,-1,1,1};
	static int[] dc = {0,0,-1,1,-1,1,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		
		map = new PriorityQueue[N+1][N+1];
		nut = new int[N+1][N+1];
		A = new int[N+1][N+1];
		dead = new int[N+1][N+1];
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				map[r][c] = new PriorityQueue<>();
				A[r][c] = in.nextInt();
				nut[r][c] = 5;
			}
		}
		
		for(int i = 0; i < M; i++) {
			int r = in.nextInt();
			int c = in.nextInt();
			int age = in.nextInt();
			
			map[r][c].add(age);
		}
		
		while(K-- > 0) {
			spring();
			summer();
			fall();
			winter();
		}
		
		System.out.println(aliveTree());
	}
	
	static void spring() {
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				if(!map[r][c].isEmpty()) {
					List<Integer> temp = new ArrayList<>();
					
					while(!map[r][c].isEmpty()) {
						int curTree = map[r][c].poll();
						
						if(curTree <= nut[r][c]) {
							nut[r][c] -= curTree;
							temp.add(curTree+1);
						}
						else {
							dead[r][c] += curTree / 2;
						}
					}
					
					for(int tree: temp) {
						map[r][c].add(tree);
					}
				}
			}
		}
	}
	
	static void summer() {
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				nut[r][c] += dead[r][c];
				dead[r][c] = 0;
			}
		}
	}
	
	static void fall() {
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				if(!map[r][c].isEmpty()) {
					int cnt = 0;
					
					for(int tree: map[r][c]) {
						if(tree % 5 == 0) cnt++;
					}
					
					if(cnt == 0) continue;
					
					for(int i = 0; i < 8; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if(isIn(nr, nc)) {
							for(int j = 0; j < cnt; j++) {
								map[nr][nc].add(1);
							}
						}
					}
				}
			}
		}
	}
	
	static void winter() {
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				nut[r][c] += A[r][c];
			}
		}
	}
	
	static int aliveTree() {
		int ret = 0;
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				ret += map[r][c].size();
			}
		}
		
		return ret;
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
