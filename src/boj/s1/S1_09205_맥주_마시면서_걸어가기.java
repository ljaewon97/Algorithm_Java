package boj.s1;

import java.util.Deque;
import java.util.LinkedList;

public class S1_09205_맥주_마시면서_걸어가기 {
	static Reader in = new Reader();
	static int[][] map;
	static boolean[] visited;
	static int N;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		for(int t = 0; t < T; t++) {
			N = in.nextInt();
			
			map = new int[N+2][2];
			visited = new boolean[N+2];
			
			for(int i = 0; i < N+2; i++) {
				map[i][0] = in.nextInt();
				map[i][1] = in.nextInt();

			}
			
			if(bfs()) {
				sb.append("happy\n");
			}
			else {
				sb.append("sad\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static boolean bfs() {
		Deque<Integer> deque = new LinkedList<>();
		deque.add(0);
		visited[0] = true;
		
		while(!deque.isEmpty()) {
			int cur = deque.poll();
			
			if(cur == N+1) {
				return true;
			}
			
			for(int i = 0; i < N+2; i++) {
				if(!visited[i] && calcDist(cur, i) <= 1000) {
					visited[i] = true;
					deque.add(i);
				}
			}
		}
		
		return false;
	}
	
	static int calcDist(int c1, int c2) {
		return Math.abs(map[c1][0] - map[c2][0]) + Math.abs(map[c1][1] - map[c2][1]);
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
