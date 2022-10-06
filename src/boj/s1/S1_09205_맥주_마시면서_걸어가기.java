package boj.s1;

import java.util.LinkedList;
import java.util.Queue;

public class S1_09205_맥주_마시면서_걸어가기 {
	static Reader in = new Reader();
	static Point[] map;
	static boolean[] visited;
	static int N;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		while(T-- > 0) {
			N = in.nextInt();
			
			map = new Point[N+2];
			visited = new boolean[N+2];
			
			for(int i = 0; i < N+2; i++) {
				map[i] = new Point(in.nextInt(), in.nextInt());
			}
			
			if(bfs()) sb.append("happy\n");
			else sb.append("sad\n");
		}
		
		System.out.println(sb);
	}
	
	static boolean bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		visited[0] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if(cur == N+1) return true;
			
			for(int i = 1; i < N+2; i++) {
				if(!visited[i] && canMove(cur, i)) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		
		return false;
	}
	
	static boolean canMove(int c1, int c2) {
		return Math.abs(map[c1].x - map[c2].x) + Math.abs(map[c1].y - map[c2].y) <= 1000;
	}
	
	static class Point {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
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
