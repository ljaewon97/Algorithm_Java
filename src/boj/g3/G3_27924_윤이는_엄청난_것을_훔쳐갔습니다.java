package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_27924_윤이는_엄청난_것을_훔쳐갔습니다 {
	static List<Integer>[] graph;
	static int[][] visited;
	static boolean[] caught;
	static int N, a, b, c;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1];
		visited = new int[N+1][3];
		caught = new boolean[N+1];
		
		for(int i = 1; i <= N; ++i) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			graph[p].add(q);
			graph[q].add(p);
		}
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		boolean res = bfs();
		
		System.out.println(res ? "YES" : "NO");
	}
	
	static boolean bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, a, 1));
		queue.add(new Point(1, b, 1));
		queue.add(new Point(2, c, 1));
		visited[a][0] = 1;
		visited[b][1] = 1;
		visited[c][2] = 1;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.n == 0 && graph[p.v].size() == 1) return true;
			if(p.n == 0 && caught[p.v]) continue;
			
			for(int next: graph[p.v]) {
				if(visited[next][p.n] == 0) {
					if(p.n == 0 && (visited[next][1] == p.d || visited[next][2] == p.d)) continue;
					queue.add(new Point(p.n, next, p.d+1));
					visited[next][p.n] = p.d+1;
					if(p.n >= 1 && visited[next][0] == visited[next][p.n]) {
						caught[next] = true;
					}
				}
			}
		}
		
		return false;
	}
	
	static class Point {
		int n, v, d;
		
		public Point(int n, int v, int d) {
			this.n = n;
			this.v = v;
			this.d = d;
		}
	}
}
