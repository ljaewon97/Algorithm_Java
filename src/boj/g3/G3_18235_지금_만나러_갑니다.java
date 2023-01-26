package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_18235_지금_만나러_갑니다 {
	static int[] dx = {-1,1};
	static int N, A, B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		int[] visited = new int[N+1];
		Arrays.fill(visited, -1);
		queue.add(new Point(5, A, 0));
		queue.add(new Point(6, B, 0));
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 2; i++) {
				int nx = p.x + dx[i] * (1<<p.t);
				
				if(!isIn(nx)) continue;
				
				if(p.no == 5) {
					queue.add(new Point(5, nx, p.t+1));
					visited[nx] = p.t+1;
				}
				else {
					if(visited[nx] == p.t+1) return p.t+1;
					queue.add(new Point(6, nx, p.t+1));
				}
			}
		}
		
		return -1;
	}
	
	static boolean isIn(int x) {
		return 1 <= x && x <= N;
	}
	
	static class Point {
		int no, x, t;
		
		public Point(int no, int x, int t) {
			this.no = no;
			this.x = x;
			this.t = t;
		}
	}
}
