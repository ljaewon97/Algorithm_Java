package boj.p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P1_14143_Skola {
	static PriorityQueue<Point> pq;
	static char[][] map;
	static int[][] left, right;
	static int[] fw;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][];
		left = new int[N][N];
		right = new int[N][N];
		fw = new int[N];
		
		for(int i = 0; i < N; ++i) {
			map[i] = br.readLine().toCharArray();
			
			for(int j = 1; j < N; ++j) {
				if(map[i][j-1] == '.') {
					left[i][j] = left[i][j-1]+1;
				}
			}
			
			for(int j = N-2; j >= 0; --j) {
				if(map[i][j+1] == '.') {
					right[i][j] = right[i][j+1]+1;
				}
			}
		}
		
		long ans = 0;
		
		for(int j = 0; j < N; ++j) {
			int prev = -1;
			
			for(int i = 0; i < N; ++i) {
				if(map[i][j] == 'x') {
					ans += count(j, prev+1, i-1);
					ans += count(j, prev+2, i-1);
					prev = i;
				}
			}
			
			ans += count(j, prev+1, N-1);
			ans += count(j, prev+2, N-1);
		}
		
		System.out.println(ans);
	}
	
	static int count(int c, int r1, int r2) {
		if(r2-r1 < 2) return 0;
		
		pq = new PriorityQueue<>();
		int ret = 0, pos = 0;
		
		for(int r = r1; r <= r2; r += 2) {
			while(!pq.isEmpty() && pq.peek().r < pos) {
				update(pq.poll().c, -1);
			}
			
			ret += query(pos-1) - query(pos-right[r][c]-1);
			pq.add(new Point(left[r][c]+pos, pos));
			update(pos, 1-query(pos)+query(pos-1));
			
			++pos;
		}
		
		return ret;
	}
	
	static void update(int x, int v) {
		for(++x; x < N; x += x&-x) {
			fw[x] += v;
		}
	}
	
	static int query(int x) {
		int ret = 0;
		
		for(++x; x > 0; x -= x&-x) {
			ret += fw[x];
		}
		
		return ret;
	}
	
	static class Point implements Comparable<Point> {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Point o) {
			if(this.r != o.r) return this.r - o.r;
			return this.c - o.c;
		}
	}
}
