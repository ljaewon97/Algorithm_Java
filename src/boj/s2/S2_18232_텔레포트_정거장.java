package boj.s2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class S2_18232_텔레포트_정거장 {
	static Reader in = new Reader();
	static List<Integer>[] teleport;
	static int N, M, S, E;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		S = in.nextInt();
		E = in.nextInt();
		
		teleport = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			teleport[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			int x = in.nextInt();
			int y = in.nextInt();
			
			teleport[x].add(y);
			teleport[y].add(x);
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		int ret = 0;
		
		Queue<Point> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		queue.add(new Point(S, 0));
		visited[S] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.x == E) {
				ret = p.t;
				break;
			}
			
			int nx = p.x - 1;
			if(nx > 0 && !visited[nx]) {
				queue.add(new Point(nx, p.t+1));
				visited[nx] = true;
			}
			
			nx = p.x + 1;
			if(nx <= N && !visited[nx]) {
				queue.add(new Point(nx, p.t+1));
				visited[nx] = true;
			}
			
			for(int next: teleport[p.x]) {
				if(!visited[next]) {
					queue.add(new Point(next, p.t+1));
					visited[next] = true;
				}
			}
		}
		
		return ret;
	}
	
	static class Point {
		int x, t;
		
		public Point(int x, int t) {
			this.x = x;
			this.t = t;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
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
